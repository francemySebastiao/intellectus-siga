package ao.co.intellectus.servico.cafold;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.DataFiltro;
import ao.co.intellectus.servico.exception.DataNullaException;

@Service
public class DataService {
	
	public Date getTempoActual() {
		return new Date(System.currentTimeMillis());
	}

	public DataFiltro stringParaData(String de, String para) throws ParseException {
		DataFiltro datas = new DataFiltro();
		datas.setDe(this.stringParaData(de));
		datas.setPara(this.stringParaData(para));
		return datas;
	}

	public Date stringParaData(String data) throws ParseException {
		SimpleDateFormat formatar = new SimpleDateFormat("yyyy-MM-dd");
		return formatar.parse(data);
	}

	public String dataLocal(Date data) {
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd", local);
		return formato.format(data);
	}

	public Integer diasEmFalta(Date data) {
		return this.diferenciaDeDias(data);
	}
	
	public Integer diferenciaDeDias(Date data) {
		DateTime inicio = new DateTime();
		DateTime fim = new DateTime(data);
		Days diasEmFalta = Days.daysBetween(inicio, fim);
		return diasEmFalta.getDays();
	}

	public Boolean validarDataFinal(Date inicio, Date fim) {
		if (inicio == null)
			throw new DataNullaException("Introduza a data incial");
		else if (fim == null)
			throw new DataNullaException("Introduza a data incial");
		return inicio.getTime() < fim.getTime();
	}
	
	
	public Integer diferencaDeHora(String horaFixa) {
		DateTime dateTime = new DateTime();
		DateTimeFormatter formatoTempo = DateTimeFormat.forPattern("HH:mm");
		LocalTime tempoActualFormado = formatoTempo.parseLocalTime(formatoTempo.print(dateTime));
		LocalTime localTimeFixa = formatoTempo.parseLocalTime(horaFixa);
		return localTimeFixa.compareTo(tempoActualFormado);
		/*
		 	SENDO QUE:
			System.out.println(localTimeFixa.compareTo(horaActual));    => -1
			System.out.println(horaActual.compareTo(localTimeFixa));    =>  1
			System.out.println(localTimeFixa.compareTo(localTimeFixa)); =>  0 
		 */
	}

}
