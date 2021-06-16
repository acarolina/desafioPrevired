package com.desafio.previred;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
	private String nombreArchivo;
	private static String filaUno;
	private static String filaDos;

	public ExportarCsv(String nombreArchivo,String filaUno ,String filaDos) {
		this.nombreArchivo=nombreArchivo;
		this.filaUno=filaUno;
		this.filaDos=filaDos;
	}


	
	private   String[] generarCabecera(Date fechaInicio,Date fechaFin) {
		
		String[] cabecera= {filaUno, formatoFeha.format(fechaInicio), formatoFeha.format(fechaFin)};
		
     	return cabecera; 

     }
	
	private    List<String[]> parseListToString(List<Uf> ufOrdenadas) {
		
		log.info("Metodo: parseListToString ");
		List<String[]> listaDatos = new ArrayList<String[]>();
		

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

    	try {
    		log.debug("ruta del Archivo: " + file.getAbsolutePath());

    	    // GENERA CABECERA
    	    String[] cabecera = generarCabecera(fechaInicio,fechaFin);
    	    List<String[]> ufs=parseListToString(ufOrdenadas);
    	    FileWriter fileWrite= new FileWriter(nombreArchivo);
    	    CSVWriter writer = new CSVWriter(fileWrite, ';');
    	    
    	    writer.writeNext(cabecera);
    	    for (String[] fila : ufs) {
    	    	writer.writeNext(fila);
    	    }
    	    fileWrite.close();
    	    writer.close();
    	    
    	    
    	    log.info("Fin de generarArchivo");

    	} catch (FileNotFoundException e) {
    	    log.error(e.getMessage(), e);
    	    return null;

    	} catch (IOException e) {
    	    log.error(e.getMessage(), e);
    	    return null;
    	} catch (Exception e) {
    	    log.error(e.getMessage(), e);
    	    return null;
    	}finally {
    	
    	}
    	
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
