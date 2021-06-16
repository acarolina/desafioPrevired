package com.desafio.previred;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVWriter;

import com.previred.desafio.tres.uf.vo.Uf;

public class ExportarCsv {
	
	private static final Logger log = Logger.getLogger( ExportarCsv.class.getName() );
	private  SimpleDateFormat formatoFeha = new SimpleDateFormat("yyyy-MM-dd");
	private  String nombreArchivo;
	private  String filaUno;
	private  String filaDos;

	public ExportarCsv(String nombreArchivo,String filaUno ,String filaDos) {
		this.nombreArchivo=nombreArchivo;
		this.filaUno=filaUno;
		this.filaDos=filaDos;
	}


	
	private   String[] generarCabecera(Date fechaInicio,Date fechaFin) {
		
		
		
     	return new String[]{filaUno, formatoFeha.format(fechaInicio), formatoFeha.format(fechaFin)}; 

     }
	
	private    List<String[]> parseListToString(List<Uf> ufOrdenadas) {
		
		log.info("Metodo: parseListToString ");
		List<String[]> listaDatos = new ArrayList<>();
		

		for (Uf uf : ufOrdenadas) {
			String fecha=formatoFeha.format(uf.getFecha());
			String registro = "";
			registro += filaDos + ";";
			registro += fecha + ";";
			registro += uf.getValor();
			listaDatos.add(registro.split(";"));

		}
		return listaDatos;
	}
	
	public File generarArchivo(List<Uf> ufOrdenadas,Date fechaInicio, Date fechaFin) {
		
		 log.info("Metodo: generarArchivo ");
    	  File file = new File(nombreArchivo);

 
    		log.debug("ruta del Archivo: " + file.getAbsolutePath());

    	    // GENERA CABECERA
    	    String[] cabecera = generarCabecera(fechaInicio,fechaFin);
    	    List<String[]> ufs=parseListToString(ufOrdenadas);
    	    try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(nombreArchivo)), ';')){
    	    	 writer.writeNext(cabecera);
    	    	    for (String[] fila : ufs) {
    	    	    	writer.writeNext(fila);
    	    	    }
    	    	   
    	    } catch (Exception e) {
    	    	log.error(e.getMessage(), e);
    	    	return null;

    	    }
    	    log.info("Fin de generarArchivo");
    	return file;
        }

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getColumna1() {
		return filaUno;
	}

	public void setColumna1(String columna1) {
		this.filaUno = columna1;
	}

	public String getColumna2() {
		return filaDos;
	}

	public void setColumna2(String columna2) {
		this.filaDos = columna2;
	}
    
	

}
