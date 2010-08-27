package com.googlecode.goodsamples.refactoring.servicestop;

import java.util.Date;
import static org.apache.commons.lang.StringUtils.isEmpty;

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
		if (stopType == null) {
			throw new IllegalArgumentException("StopType cannot be empty.");
		}
		
		if (isEmpty(reason)) {
			throw new IllegalArgumentException("Reason cannot be empty.");
		}
		
		if (stopType == StopType.COMPLETE_STOP) {
			stopNow(completeStop);
		} else {
			stopNow(readOnlyStop);
			StopMessage stopMessage = new StopMessage();
			stopMessage.setReason(reason);
			stopMessage.setDate(new Date());
			
			if (readOnlyNotifier.getRelatedServices().size() > 0) {
				if (readOnlyNotifier.notifyToRelatedServices(stopMessage) == false)  {
					throw new FailedReadOnlyNotificationException();
				}
			}
		}
	}

	private void stopNow(Stop specificStop) {
		boolean result = specificStop.stopNow();
		if (result == false) {
			throw new FailedStopException("Cannot stop the service.");
		}		
	}
}
