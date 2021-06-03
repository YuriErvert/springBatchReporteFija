package com.javasampleapproach.batchreportefija;

import java.io.BufferedWriter;
import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.javasampleapproach.batchreportefija.util.Messages;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

import org.springframework.core.io.FileSystemResource;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;

/**
 * 
 * @author 			Jonathan Pierre Reyna Rossel
 * @provider 		Everis
 * @created  		18/05/2020
 * @name			ReporteFijaBI
 * @description		Proceso automático el cual extrae data de la web de ventas fija, 
 * 					luego genera un reporte para finalizar con la carga del mismo en 
 * 					el blob storage designado.	
 */

@SpringBootApplication
@EnableScheduling
public class SpringBatchReporteFijaApplication{
	    


		public static void main(String[] args)  throws Exception{
			
			SpringApplication.run(SpringBatchReporteFijaApplication.class, args);

		}
		
	    @Bean
	    public DataSource fijaDatasource() {

	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        
	        dataSource.setDriverClassName("org.postgresql.Driver");
	        dataSource.setUrl(System.getenv("TDP_FIJA_DB_URL"));
	        dataSource.setUsername(System.getenv("TDP_FIJA_DB_USR"));
	        dataSource.setPassword(System.getenv("TDP_FIJA_DB_PW"));

	        return dataSource;
	    }
} 	



