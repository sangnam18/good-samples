package com.googlecode.goodsamples.springbatch.basic;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.item.ItemWriter;

import com.googlecode.goodsamples.springbatch.basic.Name;
import com.googlecode.goodsamples.springbatch.basic.NameDAO;
import com.googlecode.goodsamples.springbatch.basic.NameWriter;

public class NameWriterTest {
	ItemWriter<Name> O = new NameWriter();
	NameDAO nameDAO = mock(InMemoryNameDAO.class);

	@Before
	public void prepareMock() {
		((NameWriter) O).nameDAO = nameDAO;
	}

	@Test
	public void givenItemsShouldBePersistedAgain() throws Exception {
		List<Name> items = new ArrayList<Name>();
		Name name = new Name(1);
		name.name = "Min";
		items.add(name);

		O.write(items);

		verify(nameDAO).update(name);
	}
}