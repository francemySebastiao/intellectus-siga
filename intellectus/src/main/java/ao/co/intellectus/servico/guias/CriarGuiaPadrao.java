package ao.co.intellectus.servico.guias;

import java.util.Date;
import java.util.List;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.EmolumentoHistorico;
import ao.co.intellectus.model.Guia;

public class CriarGuiaPadrao {
		
	public Guia guiaCriada(Aluno aluno,List<EmolumentoHistorico> emolumento,AnoLectivo anoLectivo) {
		
		Guia guia=new Guia();
		float valorGuia=0;
		
		for (EmolumentoHistorico eH : emolumento) {
			valorGuia+=eH.getValor();
		}
		guia.setAluno(aluno);
		guia.setDataEmicao(new Date());
		guia.setValor(valorGuia);
		guia.setDataVencimento(new Date());
		guia.setAcordo(false);
		guia.setAnulada(false);
		guia.setLiquidada(false);
		guia.setGerouCredito(false);
		guia.setGeradaReferencia(false);
		guia.setGeradaOnline(false);
	    return guia;
	} 
}