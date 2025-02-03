package ao.co.intellectus.servico.cafold;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.CompensassaoProvisoria;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.repository.CompensassaoProvisoriaRepository;

@Service
public class CompensassaoService {

	@Autowired
	private CompensassaoProvisoriaRepository compensassaoRepository;
	
	public boolean apagarCompessassoes(Guia guia) {
		List<CompensassaoProvisoria> compensassoes = this.compensassaoRepository.findByNumeroGuia(guia.getNumeroGuia());
		if(compensassoes.size() > 0) {
			for (CompensassaoProvisoria pr : compensassoes) {
				CompensassaoProvisoria compesassao = this.compensassaoRepository.findOne(pr.getId());
				this.compensassaoRepository.delete(compesassao);
			}
		}
		return compensassoes.isEmpty();
	}
	
	public void salvar(List<GuiaPagamentoHistorico> guiaHistorico,Guia guia, String telefone) {
		CompensassaoProvisoria compensassao = new CompensassaoProvisoria();
		// Guia guia = this.guiaRepository.findByNumeroGuia(numeroGuia);
		compensassao.setNumeroGuia(guia.getNumeroGuia());
		compensassao.setDataCriacao(new Date());
		compensassao.setDataVencimento(guia.getDataVencimento());
		compensassao.setDescricao(guiaHistorico.get(0).getEmolumento().getEmolumento());
		compensassao.setNome(guia.getAluno().getNome());
		compensassao.setNumero(Integer.parseInt(guia.getAluno().getNumeroDeAluno()));
		compensassao.setTelefone(telefone);
		compensassao.setValor(guia.getValor());
		this.compensassaoRepository.save(compensassao);
	}
	
	
}
