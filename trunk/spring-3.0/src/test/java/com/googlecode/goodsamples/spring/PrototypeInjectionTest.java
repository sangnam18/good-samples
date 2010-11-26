package com.googlecode.goodsamples.spring;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:prototypeContext.xml"})
public class PrototypeInjectionTest {
	@Autowired
	Prototype prototypeOne;

	@Autowired
	Prototype prototypeTwo;

	@Autowired
	Provider<Prototype> prototypeProvider;

	@Test
	public void prototypeIsNotSingletone() {
		assertThat(prototypeOne, is(not(sameInstance(prototypeTwo))));
	}

	@Test
	public void newPrototypeShouldBeInjectedAlways() {
		Set<Prototype> prototypes = new HashSet<Prototype>();

		for (int i = 0; i < 10; i++) {
			Prototype each = prototypeProvider.get();
			if (prototypes.contains(each)) {
				fail();
			}
			prototypes.add(each);
		}
	}
}
