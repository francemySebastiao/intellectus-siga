package ao.co.intellectus.servico.cafold;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

@Service
public class SistemaOperativoService {
	
	
	public boolean servicoEstaEmExecucao(String servico) {
		boolean encontrado = false;
	    try {
	        File ficheiro = File.createTempFile("realhowto",".vbs");
	        ficheiro.deleteOnExit();
	        FileWriter escreverNoFicheiro = new java.io.FileWriter(ficheiro);

	        String script = "Set WshShell = WScript.CreateObject(\"WScript.Shell\")\n"
	                   + "Set locator = CreateObject(\"WbemScripting.SWbemLocator\")\n"
	                   + "Set service = locator.ConnectServer()\n"
	                   + "Set processes = service.ExecQuery _\n"
	                   + " (\"select * from Win32_Process where name='" + servico +"'\")\n"
	                   + "For Each process in processes\n"
	                   + "wscript.echo process.Name \n"
	                   + "Next\n"
	                   + "Set WSHShell = Nothing\n";

	        escreverNoFicheiro.write(script);
	        escreverNoFicheiro.close();
	        Process p = Runtime.getRuntime().exec("cscript //NoLogo " + ficheiro.getPath());
	        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line = input.readLine();
	        if (line != null) {
	            if (line.equals(servico)) {
	            	encontrado = true;
	            }
	        }
	        input.close();
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	    return encontrado;
	  }
	
	
}
