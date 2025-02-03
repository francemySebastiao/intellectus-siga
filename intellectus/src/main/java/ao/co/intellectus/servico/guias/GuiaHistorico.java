package ao.co.intellectus.servico.guias;

import org.springframework.transaction.annotation.Transactional;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.EmolumentoHistorico;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.util.FormataData;

@Transactional
public class GuiaHistorico {
	public static GuiaPagamentoHistorico guiaHistorico(Aluno aluno,AnoLectivo anoLectivo,Emolumento emolumento,Guia pGuia,EmolumentoHistorico eH) {
		GuiaPagamentoHistorico guia=new GuiaPagamentoHistorico();
		
		double valorComIva = 0;
		double valorImposto = 0;
		
		valorImposto = (eH.getValor() * emolumento.getPercentagemIva()) / 100;
		valorComIva = eH.getValor() + valorImposto;
		
		double valorTotalIvaDesconto = (valorComIva - (eH.getValor() * emolumento.getPercentagemDesconto()) / 100);
		double desconto = (eH.getValor() * emolumento.getPercentagemDesconto()) / 100;
		
		guia.setAluno(aluno);
		guia.setQuantidade("1");
		guia.setCodigoIva(emolumento.getCodigoIva());
		guia.setPercentagemIva(emolumento.getPercentagemIva().toString());
		guia.setDesconto(FormataData.formatarValor(desconto));
		guia.setValorImposto(FormataData.formatarValor(valorImposto));
		guia.setValorTotal(FormataData.formatarValor(valorTotalIvaDesconto));
		guia.setNumeroDeAluno(aluno.getNumeroDeAluno());
		guia.setAnoLectivo(anoLectivo);
		guia.setEmolumento(emolumento);
		guia.setGuia(pGuia);
		guia.setValor(eH.getValor());
		
		return guia;
	}
}
