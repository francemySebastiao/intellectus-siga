package ao.co.intellectus.util;

import org.springframework.beans.BeanUtils;

public class Utils {
	
	/*
     * NOME: ERNESTO TADEU TCHITECULO SAMBONGO
     * DATA: 01-06-2020
     * 
     */
    public static void copyObjecto(Object source,Object target) {
    	BeanUtils.copyProperties(source, target);
    }
    
    public static String geracaoNumeroProforma(Integer ano, Integer guia) {
    	return String.format("PP UGS" + "/"+ "%d%d", ano, guia);
    }
}
