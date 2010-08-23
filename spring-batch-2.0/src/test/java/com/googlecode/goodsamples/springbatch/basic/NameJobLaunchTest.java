package com.googlecode.goodsamples.springbatch.basic;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.LinkedList;

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

import com.googlecode.goodsamples.springbatch.InMemoryNameDAO;
import com.googlecode.goodsamples.springbatch.basic.Name;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/BasicContext.xml" })
@TransactionConfiguration
@Transactional
public class NameJobLaunchTest {
	@Autowired
	InMemoryNameDAO inMemorynameDAOImpl;
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job nameJob;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Before
	public void prepare() throws Exception {
		assertThat(nameJob.getName(), is("nameJob"));
		jdbcTemplate.execute(IOUtils.toString(NameJobLaunchTest.class
				.getResourceAsStream("/schema-hsqldb.sql")));

		inMemorynameDAOImpl.insert(new Name("Min"));
		inMemorynameDAOImpl.insert(new Name("Jea"));
	}

	@After
	public void rollback() throws Exception {
		jdbcTemplate.execute(IOUtils.toString(NameJobLaunchTest.class
				.getResourceAsStream("/schema-drop-hsqldb.sql")));

		inMemorynameDAOImpl.truncate();
	}

	@Test
	public void jobShouldBeRanWithoutAnyException() throws Exception {
		 jobLauncher.run(nameJob, new JobParameters());
		
		 LinkedList<Name> result = inMemorynameDAOImpl.selectAll();
		
		 assertThat(result.size(), is(2));
		 assertThat(result.get(0).name, is("Min-modified"));
		 assertThat(result.get(1).name, is("Jea-modified"));
	}
}