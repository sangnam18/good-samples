package com.googlecode.goodsamples.xwork;

import java.util.List;

import com.opensymphony.xwork.ActionSupport;

@SuppressWarnings("serial")
public class MemoListAction extends ActionSupport {
	public MemoService memoService;
	private List<Memo> memos;

	public String execute() {
		memos = memoService.allMemos();
		return SUCCESS;
	}

	public List<Memo> getMemos() {
		return memos;
	}
}
