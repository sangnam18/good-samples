package com.googlecode.goodsamples.quartz;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class HelloJobLaunchTest {
	Scheduler scheduler;
	
	@Before
	public void startScheduler() throws Exception {
		scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
	}
	
	@After
	public void shutdownScheduler() throws Exception {
		scheduler.shutdown();		
	}
	
	@Test
	public void helloJobShouldBeRun() throws Exception {
		JobDetail jobDetail = new JobDetail("helloJob", null, HelloJob.class);
		Trigger trigger = new SimpleTrigger("rightNowTrigger", null);
		scheduler.scheduleJob(jobDetail, trigger);
	}
}
