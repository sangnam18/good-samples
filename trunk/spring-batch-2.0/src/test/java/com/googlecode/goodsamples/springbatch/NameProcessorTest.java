package com.googlecode.goodsamples.springbatch;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.batch.item.ItemProcessor;

import com.googlecode.goodsamples.springbatch.Name;
import com.googlecode.goodsamples.springbatch.NameProcessor;

public class NameProcessorTest {
	ItemProcessor<Name, Name> O = new NameProcessor();

	@Test
	public void nameShouldBeModified() throws Exception {
		Name name = new Name(1, "Min");
		assertEquals(new Name(1, "Min-modified"), O.process(name));
	}
}
