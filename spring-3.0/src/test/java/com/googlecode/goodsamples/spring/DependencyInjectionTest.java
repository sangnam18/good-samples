package com.googlecode.goodsamples.spring;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import com.googlecode.goodsamples.spring.support.Dependency;

@SuppressWarnings("deprecation")
public class DependencyInjectionTest extends
		AbstractDependencyInjectionSpringContextTests {
	private Dependency dependency;

	public void testDependencyShouldBeInjected() {
		assertThat(dependency, is(notNullValue()));
		assertThat(dependency.getOneDataSource(), is(notNullValue()));
		assertThat(dependency.getTwoDataSource(), is(notNullValue()));
	}

	public void setDependency(Dependency dependency) {
		this.dependency = dependency;
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "classpath:dependencyContext.xml" };
	}
}
