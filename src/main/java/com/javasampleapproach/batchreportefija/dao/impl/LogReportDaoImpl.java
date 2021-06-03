package com.javasampleapproach.batchreportefija.dao.impl;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javasampleapproach.batchreportefija.dao.LogReportDao;
import com.javasampleapproach.batchreportefija.model.LogReport;
import com.javasampleapproach.batchreportefija.util.GlobalVariables;

@Repository
public class LogReportDaoImpl extends JdbcDaoSupport implements LogReportDao{
		
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	GlobalVariables gv;
	
	private static final Logger log = LoggerFactory.getLogger(ReportDaoImpl.class);
	
	@PostConstruct
	private void initialize() {
	  setDataSource(dataSource);
	}
	
	@Override
	public void insertLogReport(LogReport logReport) {
		
		try {
			
			String trace = logReport.getTrace();
			String statusProccess = logReport.getStatusProccess();
			String fileName = logReport.getFileName();
			LocalDateTime executedAt = logReport.getExecutedAt();
			
			String sql = gv.queryLogReportBi;
			
			jdbcTemplate.update(sql, trace, statusProccess, fileName, executedAt);
			
			log.info("Log de proceso registrado en la base de datos.");
			
		}catch(Exception e) {
			
			log.info("Error al intentar registrar Log : " + e.getMessage());
			
		}
								
	}
}
