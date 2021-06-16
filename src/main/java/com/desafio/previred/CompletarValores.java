package com.desafio.previred;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.tools.RandomDate;
import com.previred.desafio.tres.uf.vo.Uf;


public class CompletarValores {
    private static final Logger log = Logger.getLogger( CompletarValores.class.getName() );
	
	 public  List<Uf> ordenarUf(Set<Uf> ufs){
		 log.info("Metodo: ordenarUf ");
		 Stream <Uf> stream =ufs.stream();
		 List<Uf> listaUf= stream.sorted(Comparator.comparing(Uf::getFecha).reversed()).collect(Collectors.toList());
		 return listaUf;
		}

	public  Set<Uf> getValoresfaltantes(int tamaño, Date fechaInicio, Date fechaFin) {
		
		log.info("Metodo: getValoresfaltantes ");
		LocalDate fechaInicioLocal = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    LocalDate fechaFinLocal = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    Set<Uf> ufs = new HashSet<Uf>();
	    
	    log.info("Metodo: getValoresfaltantes - generando fecha aleatoria ");
	    log.debug("Rango de Fecha -  Fecha inicio: " + fechaInicioLocal + " Fecha Fin: "+ fechaFinLocal);	
	    RandomDate randomDate= new RandomDate(fechaInicioLocal, fechaFinLocal);
	   
	    //System.out.println(tamaño + " \n");
	    for (int i =0; i<tamaño; i++){
	    	DatosUf datos= DatosUf.getInstance(); 
	    	LocalDate fecha=randomDate.nextDate();
	    	Date siguienteFecha = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	Uf ufFaltante= datos.getUf(siguienteFecha);
	    	ufs.add(ufFaltante);	
	    }

	    return ufs;
	}

}
