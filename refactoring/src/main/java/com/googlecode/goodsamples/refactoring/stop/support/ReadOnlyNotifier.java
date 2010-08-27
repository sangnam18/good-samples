package com.googlecode.goodsamples.refactoring.stop.support;

import java.util.List;

public class ReadOnlyNotifier {

	public List<String> getRelatedServices() {
		return null;
	}

	public boolean notifyToRelatedServices(StopMessage stopMessage) {
		return false;
	}
}
