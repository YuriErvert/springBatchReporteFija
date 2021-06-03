package com.javasampleapproach.batchreportefija.dao;

import java.util.List;

import com.javasampleapproach.batchreportefija.model.Report;

public interface ReportDao {
	public List<Report> loadAllReports();
}
