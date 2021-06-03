package com.javasampleapproach.batchreportefija.processor;

import org.springframework.batch.item.ItemProcessor;

import com.javasampleapproach.batchreportefija.model.Report;

public class ReportItemProcessor implements ItemProcessor<Report, Report>{
	
	@Override
	public Report process(Report report) throws Exception{
		System.out.println("Processing item "+report);
		return report;
	}
}
