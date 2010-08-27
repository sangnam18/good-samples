package com.googlecode.goodsamples.refactoring.servicestop;

import java.util.Date;

import com.googlecode.goodsamples.refactoring.stop.support.FailedReadOnlyNotificationException;
import com.googlecode.goodsamples.refactoring.stop.support.FailedStopException;
import com.googlecode.goodsamples.refactoring.stop.support.ReadOnlyNotifier;
import com.googlecode.goodsamples.refactoring.stop.support.Stop;
import com.googlecode.goodsamples.refactoring.stop.support.StopMessage;

public class ServiceStopper {
	Stop completeStop;
	Stop readOnlyStop;
	ReadOnlyNotifier readOnlyNotifier;

	public void stop(Integer type, String reason) {
		if (type != null && (type == 0 || type == 1)) {
			if (reason != null && reason.length() > 0) {
				if (type == 1) {
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
