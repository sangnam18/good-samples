package com.googlecode.goodsamples.spring.support;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Dependency {
	@Autowired
	@Qualifier("oneDataSource")
	private DataSource oneDataSource;
	@Autowired
	@Qualifier("twoDataSource")
	private DataSource twoDataSource;

	public Dependency() {
	}

	public DataSource getOneDataSource() {
		return oneDataSource;
	}

	public DataSource getTwoDataSource() {
		return twoDataSource;
	}
}
