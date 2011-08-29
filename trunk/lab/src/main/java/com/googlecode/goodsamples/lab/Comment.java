package com.googlecode.goodsamples.lab;

import java.sql.Date;

public class Comment {
	private String comment;
	private Date createdate;

	public Comment(String comment, Date createdate) {
		this.comment = comment;
		this.createdate = createdate;
	}

	public String getComment() {
		return comment;
	}

	public Date getCreatedate() {
		return createdate;
	}
}
