package com.javasampleapproach.batchreportefija.util;

import java.net.URI;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javasampleapproach.batchreportefija.dao.LogReportDao;
import com.javasampleapproach.batchreportefija.model.LogReport;
import com.javasampleapproach.batchreportefija.scheduling.ScheduledTasks;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

import com.javasampleapproach.batchreportefija.util.Messages;

@Service
public class UploadBlobStorage {
		
	@Autowired
	LogReportDao logReportDao;
	
	private static final Logger log = LoggerFactory.getLogger(UploadBlobStorage.class);
	private Messages msg = new Messages();
	
	public boolean upload(String storageConnectionString, String trace, String blobContainer,
			String nameBlob, String subdirectory, String fileCsv, String path, boolean fileUploaded,
			LogReport logReport) {
		System.out.println("Iniciando Proceso de Blob Storage ...");
		 
		CloudStorageAccount storageAccount;
		CloudBlobClient blobClient = null;
		CloudBlobContainer container=null;
		String blobNameD = "";
		
			try {    
				// Parse the connection string and create a blob client to interact with Blob storage
				storageAccount = CloudStorageAccount.parse(storageConnectionString);
				blobClient = storageAccount.createCloudBlobClient();
				System.out.println("Verificando contenedor ...");
				container = blobClient.getContainerReference(blobContainer);
				
				System.out.println("Agregando nombre al contenedor ...");
				// Create the container if it does not exist with public access.
				System.out.println("Contenedor_name: " + container.getName());
				
				System.out.println("Verificando que el contenedor exista, sino se creará ...");
				container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());		
				System.out.println("Contenedor : OK");
				
				//Getting a blob reference
				CloudBlockBlob blob = container.getBlockBlobReference(nameBlob);
				
				for (ListBlobItem blobItem : container.getDirectoryReference(subdirectory).listBlobs()) {
					blobNameD = blobNameFromUri(blobItem.getUri());
					
					System.out.println("Verificando nombre del reporte en el Blob Storage: " + blobNameD);
					
					if(blobNameD.toString().equals(fileCsv)) {
						fileUploaded = true;
						break;
					}
				}
				
				
				if(fileUploaded) {
					
					trace += " | " + msg.messageFile(7);
					log.info(trace);
					logReport.setTrace(trace);
		    		logReport.setStatusProccess("ERROR");
		    		logReport.setFileName(nameBlob);
		    		logReport.setExecutedAt(LocalDateTime.now());
					
				}else {
					//Creating blob and uploading file to it
					System.out.println("Subiendo Reporte a Azure Blob Storage ... ");
					blob.uploadFromFile(path);
					System.out.println("Subida: OK ");
				}
				
				
				System.out.println("Listando reportes en contenedor:");

				//Listing contents of container
				for (ListBlobItem blobItem : container.getDirectoryReference(subdirectory).listBlobs()) {
					
				System.out.println("URI of blob is: " + blobItem.getUri());
				}

			// Download blob. In most cases, you would have to retrieve the reference
			// to cloudBlockBlob here. However, we created that reference earlier, and 
			// haven't changed the blob we're interested in, so we can reuse it. 
			// Here we are creating a new file to download to. Alternatively you can also pass in the path as a string into downloadToFile method: blob.downloadToFile("/path/to/new/file").
			//downloadedFile = new File(sourceFile.getParentFile(), "downloadedFile.txt");
			//blob.downloadToFile(downloadedFile.getAbsolutePath());
			} 
			catch (StorageException ex)
			{
				System.out.println(String.format("Error returned from the service. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
			}
			catch (Exception ex) 
			{
				System.out.println(ex.getMessage());
			}
			
			return fileUploaded;
	}
	
	public static String blobNameFromUri(URI uri) {
        String path = uri.getPath();

        String[] splits = path.split("/", 4);

        return splits[3];
    }
}
