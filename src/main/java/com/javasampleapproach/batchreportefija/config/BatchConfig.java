package com.javasampleapproach.batchreportefija.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.javasampleapproach.batchreportefija.config.writer.StringHeaderWriter;
import com.javasampleapproach.batchreportefija.dao.ReportDao;
import com.javasampleapproach.batchreportefija.listener.ReportItemReaderListener;
import com.javasampleapproach.batchreportefija.listener.ReportItemWriterListener;
import com.javasampleapproach.batchreportefija.listener.ReportListener;
import com.javasampleapproach.batchreportefija.model.Report;
import com.javasampleapproach.batchreportefija.processor.ReportItemProcessor;
import com.javasampleapproach.batchreportefija.util.GlobalVariables;

@Service
@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);
	
	 @Autowired
	 private JobBuilderFactory jobBuilderFactory;
	 
	 @Autowired
	 private StepBuilderFactory stepBuilderFactory;
	 
	 @Autowired
	 private DataSource dataSource;
	 
	 @Autowired
	 private ReportDao reportDao;
	 
	 @Autowired
	 private JobRepository jobRepository;
	 
	 @Autowired
	 private GlobalVariables gv;
	 
	 /*private String nameJ;
	  	 
	 public String getNameJ() {
		return nameJ;
	}


	public void setNameJ(String nameJ) {
		this.nameJ = nameJ;
	}*/
	
	//@Scheduled(cron = "0 */1 * * * ?" , zone = "America/Lima")
	 /*private void restartName() {
		setNameJ(String.valueOf(System.currentTimeMillis()));
	}*/
	
	 @Bean
	 public ReportItemProcessor processor(){
		 return new ReportItemProcessor();
	 }
	 
	 
	 @Bean
	    public ReportListener jobExecutionListener() {
	        return new ReportListener();
	    }
	 
	 @Bean
	    public ReportItemReaderListener readerListener() {
	        return new ReportItemReaderListener();
	    }
	 
	 @Bean
	 public ReportItemWriterListener writerListener() {
	        return new ReportItemWriterListener();
	 }
	 
	 @Bean
	 @StepScope
	 public ItemReader<Report> reader(ReportDao reportDao){
		ListItemReader<Report> itemReader = new ListItemReader<Report>(reportDao.loadAllReports());
		System.out.println("Muestra de Reader");
		return itemReader;
	 }
	 
	 //Agregando Delimitador al Archivo csv
	 private LineAggregator<Report> createReportLineAggregator() {
	        DelimitedLineAggregator<Report> lineAggregator = new DelimitedLineAggregator<>();
	        lineAggregator.setDelimiter(";");
	 
	        FieldExtractor<Report> fieldExtractor = createReportFieldExtractor();
	        lineAggregator.setFieldExtractor(fieldExtractor);
	 
	        return lineAggregator;
	    }
	 
	 //Campos extraidos del reader para pasar al writer
	 private FieldExtractor<Report> createReportFieldExtractor() {
	        BeanWrapperFieldExtractor<Report> extractor = new BeanWrapperFieldExtractor<>();
	        extractor.setNames(gv.fieldsReaderReportBi);
	        return extractor;
	 }
	 
	 @Bean
	 @StepScope
	 public FlatFileItemWriter<Report> writer(){
		 
		 //Para dar formato de Fecha y agregar al nombre del archivo
		 Date fecha = new Date();
		 SimpleDateFormat objSDF = new SimpleDateFormat("yyyyMMdd");
		 
		 //Generando FlatFileItemWriter
		 FlatFileItemWriter<Report> writer = 	new FlatFileItemWriter<>();
		 
		 //Campos para el Header
		 String exportFileHeader = gv.exportFileHeaderReportBi;
		 StringHeaderWriter headerWriter = new StringHeaderWriter(exportFileHeader);		 

		 writer.setHeaderCallback(headerWriter);
		 
		 try {
			 String exportFilePath = "temp/ReporteAppWebFijaBBII_"+objSDF.format(fecha)+".csv";	
			 //String exportFilePath = "classpath:report/ReporteAppWebFijaBBII_"+objSDF.format(fecha)+".csv";
			 writer.setResource(new FileSystemResource(exportFilePath));
		 }catch(Exception e) {
			 System.out.println("Fallo al crear archivo de Reporte en carpeta Resource: "+e.getMessage());
		 }
		 
		
	     //DelimitedLineAggregator<Report> lineAggregator = new DelimitedLineAggregator<Report>();
		 LineAggregator<Report> lineAggregator= createReportLineAggregator();
		 writer.setLineAggregator(lineAggregator);
		 
		 //Verificando y si el archivo ya existe se borra - Luego Creando archivo
		 writer.setAppendAllowed(true);
	     writer.setShouldDeleteIfEmpty(true);
	     writer.setShouldDeleteIfExists(true);
			 
			 System.out.println("Muestra de Writer FlatItem");
			 return writer;		 		 
	 }
	 /*	 
	  * Metodo para hacer prueba de Writer
	 @Bean
		public ItemWriter<? super Object> writer() {
		 
		 System.out.println("Muestra de Writer");
			return new ItemWriter<Object>() {
				@Override
				public void write(List<? extends Object> items) throws Exception {
					System.out.println("dsa");
				}
				
			};
			
		}
	  */
	 
	 @Bean
	 public TaskExecutor taskExecutor(){
	     SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor("spring_batch");
	     asyncTaskExecutor.setConcurrencyLimit(5);
	     return asyncTaskExecutor;
	 }
	 
	 
	 @Bean
	 public Step step(ItemReader<Report> reader, ItemWriter<Report> writer) {

		 Step step = stepBuilderFactory.get("step")
				 .<Report,Report>chunk(100)
				 .reader(reader)
				 .writer(writer)
				 .listener(readerListener())
				 .listener(writerListener())
				 //.taskExecutor(taskExecutor())
				 .build();
		 return step;
	 }
	 
	 @Bean
	 public Job job(Step step, JobExecutionListener listener) {
		 /*Job job = jobBuilderFactory.get("exportReportJob")				 
				 .start(step)
				 .listener(listener)				 
				 .build();*/
		 //Job job = new SimpleJob();
		 //job = 
		 //restartName();
		 //String.valueOf(System.currentTimeMillis())
		 //return jobBuilderFactory.get("job_"+getNameJ())
		 return jobBuilderFactory.get("job")
				 .incrementer(new RunIdIncrementer())
				.listener(listener)
                .flow(step)
                .end().preventRestart()
                .build();
	 }
	 

	 
	 //@Bean
	 /*public TaskExecutor threadPoolTaskExecutor(){
	  
	  ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setMaxPoolSize(40);
	        executor.setCorePoolSize(40);
	        executor.setQueueCapacity(40);
	  
	   return executor;
	 }*/
	 
	 //@Bean
	 /*public JobLauncher asyncJobLauncher() throws Exception {
	        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
	        
	        jobLauncher.setJobRepository(jobRepository);
	        jobLauncher.setTaskExecutor(taskExecutor());
	        return jobLauncher;
	 }*/


}
