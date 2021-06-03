package com.javasampleapproach.batchreportefija.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Messages {
	
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
	
	public String messageFile(Integer type) {
		String msjFile = "";
		switch(type) {
			case 1:
				msjFile = "El archivo de Reporte ya existe.";
				break;
			case 2:
				msjFile = "Job de extracción de data terminado.";
				break;
			case 3:
				msjFile = "No se pudo generar el archivo.";
				break;
			case 4:
				msjFile = "No se pudo subir el archivo.";
				break;
			case 5:
				msjFile = "Archivo vacío o nulo. 0 Registros de consulta en la BD.";
				break;
			case 6:
				msjFile = "Archivo creado correctamente.";
				break;
			case 7:
				msjFile = "El archivo de Reporte ya se encuentra subido en el Blob Storage.";
				break;
			case 8:
				msjFile = "Archivo nulo";
				break;
		}
				return msjFile;
	}
	
	public String messageDone() {
		String msjOk = "El ReporteFijaBI fue subido al Blob Storage Azure. | Proceso Batch día: " + dateFormat.format(new Date()) +  " terminado a las: " + timeFormat.format(new Date()) + ".";
		return msjOk;
	}
	
	public String messageError(String error) {
		String msjError = "El proceso no pudo finalizar:  \n" + error +  "\n Hora de ejecución: " + timeFormat.format(new Date()) + ".";
		return msjError;
	}
}
