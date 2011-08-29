package com.googlecode.goodsamples.lab;

public class Visitor {
	private String id;
	private String nickname;

	public Visitor(String id, String nickname) {
		this.id = id;
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}
}
