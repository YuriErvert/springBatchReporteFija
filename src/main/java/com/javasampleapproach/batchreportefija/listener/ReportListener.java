package com.javasampleapproach.batchreportefija.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class ReportListener implements JobExecutionListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportListener.class);
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
        LOGGER.info("beforeJob");
    }
	
	@Override
	public void afterJob(JobExecution jobExecution) {
        LOGGER.info("afterJob: " + jobExecution.getStatus());
    }
	
}
