package com.javasampleapproach.batchreportefija.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Configuration
@PropertySource(value = {"classpath:/azure.properties"})
public class MyProperties {
	
	public String STORAGECONDEV = "DefaultEndpointsProtocol=https;AccountName=pruebareportebi;AccountKey=29tFYnTfuvqWTr9hxd+NLEzGMtACzIcS0R7IYh2VJi4m0iOklsoYEAbhBC+Px7CZghGveIvKFsF3twJ00Tk4Nw==;EndpointSuffix=core.windows.net";	
	public String STORAGECONPROD = "DefaultEndpointsProtocol=https;AccountName=binegocio;AccountKey=UGCSOhDSPsWvYPNAGVlW0TornR+Dsq3JHVtQehy5/PNSv8jYFnkpf6pVFaWHXQgqfYGirHlobRSdVv21ib15dA==;EndpointSuffix=core.windows.net";
	public String CONTAINERDEV = "reportefijabi";
	public String CONTAINERPROD = "binegocios";
	public String DIRECTORYPROD = "reporteVentasBi";
	public String DIRECTORYDEV = "pruebas";
	
	 @Autowired
	 private Environment env;

	public String getSTORAGECONDEV() {
		return env.getProperty("blobazuredev");
	}

	public String getSTORAGECONPROD() {
		return env.getProperty("blobazureprod");
	}

	public String getCONTAINERDEV() {
		return env.getProperty("containerdev");
	}

	public String getCONTAINERPROD() {
		return env.getProperty("containerprod");
	}




}
