package com.googlecode.goodsamples.springbatch.retry;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.goodsamples.springbatch.basic.NameDAO;
import com.googlecode.goodsamples.springbatch.basic.NameJobLaunchTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/RetryContext.xml" })
@TransactionConfiguration(defaultRollback=true)
@Transactional
public class JobRetryTest {
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job retryJob;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	NameDAO nameDAO;

	@Test
	public void shouldBeRetriedFromLastFailPoint() throws Exception {
		assertThat(jobLauncher.run(retryJob, createConstantJobParameters()).getStatus(), is(BatchStatus.FAILED));
		assertThat(nameDAO.selectAll().size(), is(3));
		
		assertThat(jobLauncher.run(retryJob, createConstantJobParameters()).getStatus(), is(BatchStatus.COMPLETED));
		assertThat(nameDAO.selectAll().size(), is(10));
	}

	/* If a given JobParameters is exactly identical to previous one,
	 * Job is continued from the last failure point. */
	private JobParameters createConstantJobParameters() {
		Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
		parameters.put("key", new JobParameter("someValue"));
		JobParameters jobParameters = new JobParameters(parameters);
		return jobParameters;
	}

	@Before
	public void createJobRepository() throws Exception {
		jdbcTemplate.execute(IOUtils.toString(NameJobLaunchTest.class
				.getResourceAsStream("/schema-hsqldb.sql")));
	}

	@After
	public void dropJobRepository() throws Exception {
		jdbcTemplate.execute(IOUtils.toString(NameJobLaunchTest.class
				.getResourceAsStream("/schema-drop-hsqldb.sql")));
	}
}
