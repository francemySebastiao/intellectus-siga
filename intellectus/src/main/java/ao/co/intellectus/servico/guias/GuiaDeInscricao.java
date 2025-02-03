package ao.co.intellectus.servico.guias;

import java.util.Calendar;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.EmolumentoHistorico;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Moeda;

@Transactional
public class GuiaDeInscricao {
	public static Guia guiaDeInscricao(Aluno aluno, AnoLectivo anoLectivo,EmolumentoHistorico emoluHist,Moeda moeda,Instituicao instituicao) {
		Guia guia=new Guia();
		guia.setAluno(aluno);
		guia.setNumeroDeAluno(aluno.getNumeroDeAluno());
		guia.setAutomaticamente(true);
		guia.setDataEmicao(new Date());
		guia.setLiquidada(false);
		guia.setAnoLectivo(anoLectivo);
		guia.setValor(emoluHist.getValor());
		guia.setDataVencimento(new Date());
		guia.setMoeda(moeda);
		guia.setInstituicao(instituicao);
		guia.setAnulada(false);
		guia.setGerouCredito(false);
		guia.setGeradaOnline(false);
		guia.setLiquidacaoAGT(false);
		guia.setLiquidacaoCredito(false);
		guia.setGeradaReferencia(false);
		Calendar hoje = Calendar.getInstance();
		Integer ano = hoje.get(Calendar.YEAR);
		guia.setNumeroGuia(ano.toString());
		return guia;
	}
}