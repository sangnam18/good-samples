package com.googlecode.goodsamples.lab;

import java.sql.Date;

public class Article {
	private String title;
	private String contents;
	private Date createdate;
	private int hit;

	public Article(String title, String contetns, Date createdate, int hit) {
		this.title = title;
		this.contents = contetns;
		this.createdate = createdate;
		this.hit = hit;
	}

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public int getHit() {
		return hit;
	}
}
