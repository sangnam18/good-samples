package com.googlecode.goodsamples.refactoring.servicestop;

import java.util.Date;

import com.googlecode.goodsamples.refactoring.servicestop.support.FailedReadOnlyNotificationException;
import com.googlecode.goodsamples.refactoring.servicestop.support.FailedStopException;
import com.googlecode.goodsamples.refactoring.servicestop.support.ReadOnlyNotifier;
import com.googlecode.goodsamples.refactoring.servicestop.support.Stop;
import com.googlecode.goodsamples.refactoring.servicestop.support.StopMessage;

public class ServiceStopper {
	Stop completeStop;
	Stop readOnlyStop;
	ReadOnlyNotifier readOnlyNotifier;
	
	public enum StopType {
		COMPLETE_STOP,
		READONLY_STOP;
	}

	public void stop(StopType stopType, String reason) {
		if (stopType != null) {
			if (reason != null && reason.length() > 0) {
				if (stopType == StopType.COMPLETE_STOP) {
					if (completeStop.stopNow() == false) {
						throw new FailedStopException("Cannot stop the service.");
					}
				} else {
					if (readOnlyStop.stopNow()) {
						StopMessage stopMessage = new StopMessage();
						stopMessage.setReason(reason);
						stopMessage.setDate(new Date());
						
						if (readOnlyNotifier.getRelatedServices().size() > 0) {
							if (readOnlyNotifier.notifyToRelatedServices(stopMessage) == false)  {
								throw new FailedReadOnlyNotificationException();
							}
						}
					} else {
						throw new FailedStopException("Cannot stop the service.");
					}
				}
				
				return;
			}
		}
		
		throw new IllegalArgumentException("Type or reason cannot be empty.");
	}
}