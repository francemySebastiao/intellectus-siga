package ao.co.intellectus.config.filtro;

public class NomeLimpo {
   public static String rtrim(String toTrim) {
	   
	   char [] val=toTrim.toCharArray();
	   int len =val.length;
	   
	   while(len >0 && val[len -1]<=' ') {
		   len--;
	   }
	   return len < val.length ? toTrim.substring(0, len): toTrim;
   }
}
