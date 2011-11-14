package com.googlecode.goodsamples.datastructure;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class TreeTest {
	private Tree sut = new Tree();
	
	@Test
	public void nodeCanBeAdded() {
		sut.add("4");
		sut.add("3");
		sut.add("5");
		sut.add("6");
		
		String result = sut.toString();
		
		assertThat(sut.level(), is(3));
		assertThat(result, is("[4,3,5,null,null,null,6]"));
	}

	@Test
	public void nodeCanBeAddedDisproportionately() {
		sut.add("3");
		sut.add("10");
		sut.add("15");
		sut.add("16");
		
		String result = sut.toString();
		
		assertThat(sut.level(), is(4));
		assertThat(result, is("[3,null,10,null,null,null,15,null,null,null,null,null,null,null,16]"));
	}

	@Test
	public void treeShouldBeGrownDouble() {
		final int initialCapacity = 16;
		sut = new Tree(16);
		sut.add("3");
		sut.add("10");
		sut.add("15");
		sut.add("16");

		sut.add("17");
		
		assertThat(sut.length(), is(initialCapacity * 2));
	}

	@Ignore
	@Test
	public void itemCanBeSearched() {
		sut.add("3");
		sut.add("10");
		sut.add("15");
		sut.add("16");
		
		//String result = sut.searchBy("16");
		
		//assertThat(result, is("16"));
	}
}
