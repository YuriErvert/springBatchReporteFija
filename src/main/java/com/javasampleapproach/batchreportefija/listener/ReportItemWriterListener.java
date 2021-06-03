package com.javasampleapproach.batchreportefija.listener;

import com.javasampleapproach.batchreportefija.model.Report;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

public class ReportItemWriterListener implements ItemWriteListener<Report>{

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportItemWriterListener.class);

    @Override
    public void beforeWrite(List<? extends Report> list) {
        LOGGER.info("beforeWrite");
    }


    @Override
    public void afterWrite(List<? extends Report> list) {
        for (Report report : list) {
            LOGGER.info("afterWrite :Report_" + report.getDni() + "_" + report.getUser_atis());
        }
    }

    @Override
    public void onWriteError(Exception e, List<? extends Report> list) {
        LOGGER.info("onWriteError");
    }
}
