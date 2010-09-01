package com.googlecode.goodsamples.spring.support;

import javax.sql.DataSource;

public class Dependency {
	private DataSource oneDataSource;
	private DataSource twoDataSource;

	public Dependency() {
	}

	public DataSource getOneDataSource() {
		return oneDataSource;
	}

	public void setOneDataSource(DataSource oneDataSource) {
		this.oneDataSource = oneDataSource;
	}

	public DataSource getTwoDataSource() {
		return twoDataSource;
	}

	public void setTwoDataSource(DataSource twoDataSource) {
		this.twoDataSource = twoDataSource;
	}
}
