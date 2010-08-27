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

	public void stop(Integer stopType, String reason) {
		if (stopType != null && (stopType == 1 || stopType == 2)) {
			if (reason != null && reason.length() > 0) {
				if (stopType == 1) {
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
