package com.googlecode.goodsamples.springbatch;

import java.util.LinkedList;

//TODO Cursor 기반 예제 추가 필요
public interface NameDAO {
	Name insert(Name name);

	Name select(Name name);

	Name update(Name toBeUpdated);

	LinkedList<Name> selectAll();
}
