package com.googlecode.goodsamples.xwork;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.opensymphony.xwork.Action;

public class MemoListActionTest {
	MemoListAction sut = new MemoListAction();

	@Test
	public void 메모목록을가져온다() {
		List<Memo> expectedMemos = new ArrayList<Memo>();
		MemoService memoBO = mock(MemoService.class);
		when(memoBO.allMemos()).thenReturn(expectedMemos);
		sut.memoService = memoBO;
		
		String result = sut.execute();
		
		assertThat(result, is(Action.SUCCESS));
		assertThat(sut.getMemos(), is(expectedMemos));
	}
}
