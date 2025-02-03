package ao.co.intellectus.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.repository.AnoLectivoRepository;

public class FormataData {
	
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;

	
	
	public static Date data(String data) throws ParseException {
		SimpleDateFormat formatar = new SimpleDateFormat("yyyy-MM-dd");
		Date formatada = formatar.parse(data);
		return formatada;
	}
	
	public static Date dataEstilo(String data) throws ParseException {
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");
		Date formatada = formatar.parse(data);
		return formatada;
	}
	
	public static Double formatarValor(Double valor) {
		
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_UP);
		String valorFormatado = df.format(valor);
		valorFormatado = valorFormatado.replace(',', '.');
		
		double valorArredondado = Double.parseDouble(valorFormatado);
		
		return valorArredondado;
	}
	
	public Integer anoLectivo() {

		Calendar calendario = Calendar.getInstance();
		//Integer ano = calendario.get(Calendar.YEAR);
		calendario.add(Calendar.YEAR, -1);
		Integer ano = calendario.get(Calendar.YEAR);

		String anoString = String.valueOf(ano);
		String anoSubstring = anoString.substring(2, 4);
		Integer anoLimpo = Integer.parseInt(anoSubstring);

		return anoLimpo;
	}
}
