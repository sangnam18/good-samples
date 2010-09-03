package com.googlecode.goodsamples.springbatch.retry;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.goodsamples.springbatch.AbstractJobRepositoryInitilization;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/RetryContext.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class JobRetryTest extends AbstractJobRepositoryInitilization {
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job job;

	@Test
	public void jobShouldBeRunOnPartitionedSlaves() throws Exception {
	}
}