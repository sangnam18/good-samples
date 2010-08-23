package com.googlecode.goodsamples.springbatch.retry;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.googlecode.goodsamples.springbatch.basic.Name;
import com.googlecode.goodsamples.springbatch.basic.NameDAO;

@Component
public class RetryWriter implements ItemWriter<Integer> {
	@Autowired
	NameDAO nameDAO;
	
	boolean errorHook = true;
	
	@Override
	public void write(List<? extends Integer> items) throws Exception {
		for (Integer each : items) {
			if (errorHook && each.intValue() == 4) {
				errorHook = false;
				throw new RuntimeException("For testing retry.");
			}
			nameDAO.insert(new Name(each));
		}
	}
}
