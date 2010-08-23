package com.googlecode.goodsamples.springbatch.basic;

import java.util.LinkedList;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NameReader implements ItemReader<Name> {
	@Autowired
	NameDAO nameDAO;
	LinkedList<Name> rows;

	public Name read() throws Exception, UnexpectedInputException,
			ParseException {
		if (rows == null) {
			rows = nameDAO.selectAll();
		}
		return rows.poll();
	}
}
