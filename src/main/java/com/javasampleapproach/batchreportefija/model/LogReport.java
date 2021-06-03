package com.javasampleapproach.batchreportefija.model;

import java.time.LocalDateTime;

public class LogReport {
	
	private String trace;
	private String statusProccess;
	private String fileName;
	private LocalDateTime executedAt;
	
	public String getTrace() {
		return trace;
	}
	public void setTrace(String trace) {
		this.trace = trace;
	}
	public String getStatusProccess() {
		return statusProccess;
	}
	public void setStatusProccess(String statusProccess) {
		this.statusProccess = statusProccess;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public LocalDateTime getExecutedAt() {
		return executedAt;
	}
	public void setExecutedAt(LocalDateTime executedAt) {
		this.executedAt = executedAt;
	}
	
	
	public LogReport(String trace, String statusProccess, String fileName, LocalDateTime executedAt){
		this.trace = trace;
		this.statusProccess = statusProccess;
		this.fileName = fileName;
		this.executedAt = executedAt;
	}
	
	public LogReport(){
		this.trace = null;
		this.statusProccess = null;
		this.fileName = null;
		this.executedAt = null;
	}
	
}
