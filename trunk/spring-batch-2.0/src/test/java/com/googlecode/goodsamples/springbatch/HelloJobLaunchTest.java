package com.googlecode.goodsamples.springbatch;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/batchContext.xml" })
@TransactionConfiguration
@Transactional
public class HelloJobLaunchTest {
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job helloJob;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Before
	public void prepare() throws Exception {
		assertThat(helloJob.getName(), is("helloJob"));
		jdbcTemplate.execute(IOUtils.toString(HelloJobLaunchTest.class
				.getResourceAsStream("/schema-hsqldb.sql")));
	}

	@After
	public void rollback() throws Exception {
		jdbcTemplate.execute(IOUtils.toString(HelloJobLaunchTest.class
				.getResourceAsStream("/schema-drop-hsqldb.sql")));
	}

	@Test
	public void jobShouldBeRanWithoutAnyException() throws Exception {
		
		 jobLauncher.run(helloJob, new JobParameters());
	}
}