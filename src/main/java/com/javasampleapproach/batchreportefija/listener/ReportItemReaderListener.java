package com.javasampleapproach.batchreportefija.listener;

import org.springframework.batch.core.ItemReadListener;

import com.javasampleapproach.batchreportefija.model.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportItemReaderListener implements ItemReadListener<Report>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportItemReaderListener.class);

	@Override
    public void beforeRead() {
        LOGGER.info("beforeRead");
    }

    @Override
    public void afterRead(Report report) {
        LOGGER.info("afterRead: Report_" + report.getDni() + "_" + report.getUser_atis() );
    }

    @Override
    public void onReadError(Exception e) {
        LOGGER.info("onReadError: " + e.getMessage());
    }
}
