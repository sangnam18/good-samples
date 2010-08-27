package com.googlecode.goodsamples.refactoring.stop.support;

public class ReadOnlyStop implements Stop {
	public boolean stopNow() {
		return false;
	}
}
