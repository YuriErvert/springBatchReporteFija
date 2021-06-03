package com.javasampleapproach.batchreportefija.scheduling;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.job.AbstractJob;
import org.springframework.batch.core.job.flow.FlowJob;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.javasampleapproach.batchreportefija.SpringBatchReporteFijaApplication;
import com.javasampleapproach.batchreportefija.config.BatchConfig;
import com.javasampleapproach.batchreportefija.dao.LogReportDao;
import com.javasampleapproach.batchreportefija.model.LogReport;
import com.javasampleapproach.batchreportefija.properties.MyProperties;
import com.javasampleapproach.batchreportefija.util.Messages;
import com.javasampleapproach.batchreportefija.util.UploadBlobStorage;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

@Component
@EnableScheduling
public class ScheduledTasks {
	
	
	@Autowired
	JobLauncher jobLauncher;
	     
	@Autowired
	Job job;
	
	@Autowired
	LogReportDao logReportDao;
	
	@Autowired
	BatchConfig bc;
	
	@Autowired
	UploadBlobStorage ubs;
	
	private MyProperties myProperties = new MyProperties();
	private Messages msg = new Messages();
	private LogReport logReport = new LogReport();
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
	//Para dar formato de Fecha a la hora de ejecución, fecha de ejecución , log del proceso y agregar al nombre del archivo	
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
	private static final SimpleDateFormat logDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat objSDF = new SimpleDateFormat("yyyyMMdd");

	//Variables de conexión Blob Storage
	//private String storageConnectionString = myProperties.STORAGECONDEV;
	//private String blobContainer = myProperties.CONTAINERDEV;
	//private String subdirectory = myProperties.DIRECTORYDEV;
	private String storageConnectionString = myProperties.STORAGECONPROD;
	private String blobContainer = myProperties.CONTAINERPROD;
	private String subdirectory = myProperties.DIRECTORYPROD;
 
	//Variables para uso del proceso programado
	private Date fecha;
	private String fileCsv;
	private String path;
	private String nameBlob;
	private File sourceFile;
	private boolean fileUploaded;
	private boolean validateJob;
	private String trace = "";
	
	@Component
	public class CommandLineAppStartupRunner implements CommandLineRunner {
			
	    @Override
	    public void run(String...args) throws Exception {	    	
	    	executeProccesReport();
	    	
	    }
	    
	}


	//@Scheduled(cron = "0 0 4 * * ?" , zone = "America/Lima")
	//@Scheduled(cron = "0 0 1 * * ?" , zone = "America/Lima")
	private void executeProccesReport() throws Exception
    {	
		validateJob = false;		
		fileUploaded = false;
		fecha = new Date();
		fileCsv = "ReporteAppWebFijaBBII_"+objSDF.format(fecha)+".csv";
		nameBlob = subdirectory+"/"+fileCsv;
		path = "temp/"+fileCsv;

		if(sourceFile!=null && sourceFile.isFile()) {
			System.out.println(msg.messageFile(1));

    		logReport = new LogReport(msg.messageFile(1),"ERROR",path,LocalDateTime.now());
    		sourceFile.delete();
		}else {
			try {
				JobParameters params = new JobParametersBuilder()
		                .addString("JobID", String.valueOf(System.currentTimeMillis()))
		                .toJobParameters();
		        jobLauncher.run(job, params);
					    			    			    	
		    	log.info(msg.messageFile(2));
		    	trace = msg.messageFile(2);

		    	sourceFile = new File(path);
		    	if(sourceFile.isFile()) {
		    		validateJob = true;
		    	}
		    	
	    	}
	    	catch(Exception e) {
	    		trace = msg.messageFile(3) + " | " + msg.messageError(e.getMessage());
	    		log.info(trace);

	    		logReport = new LogReport(trace,"ERROR",msg.messageFile(8),LocalDateTime.now());
	    	}

			if(validateJob) {
				try {
					trace += " | " + msg.messageFile(6);
					fileUploaded = ubs.upload(storageConnectionString, trace, blobContainer, nameBlob, subdirectory, fileCsv, path, fileUploaded, logReport);
					if(!fileUploaded) {
						trace += " | " + msg.messageDone();

			    		logReport = new LogReport(trace,"OK",blobContainer+"/"+nameBlob,LocalDateTime.now());
					}		    		
		    		sourceFile.delete();
				}
				catch(Exception e){
					trace += " | " + msg.messageFile(4) + " | " + msg.messageError(e.getMessage());
					log.info(trace);

		    		logReport = new LogReport(trace,"ERROR",path,LocalDateTime.now());
				}
			}else {
				trace += " | " + msg.messageFile(5);
				log.info(trace);

	    		logReport = new LogReport(trace,"ERROR",msg.messageFile(8),LocalDateTime.now());
			}
		}
			
		/*
		try {
			logReportDao.insertLogReport(logReport);
		}
		catch(Exception e) {
			log.info("No se pudo guardar el Log : " + e.getMessage());
		}*/
		

    }
	

}