package ao.co.intellectus.servico.notificacoes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.alerta.Campanha;
import ao.co.intellectus.repository.alerta.CampanhaRepository;
import ao.co.intellectus.servico.cafold.DataService;
import ao.co.intellectus.servico.exception.DadosDuplicadoException;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class CampanhaService {

	@Autowired
	private CampanhaRepository campanhaRepository;
	@Autowired
	private DataService dataService;
	
	public List<Campanha> paraHoje(){
		List<Campanha> campanhas = new ArrayList<Campanha>();
		for(Campanha campanha: this.campanhaRepository.findAll()) {
			Integer diasEmFalta = this.dataService.diasEmFalta(campanha.getDataEnviar());
			if(diasEmFalta == 0)
				campanhas.add(campanha);
		}
		return campanhas;
	}
	
	public Campanha campanha(String designacao) {
		Campanha campanha = this.campanhaRepository.findByDesigancao(designacao);
		if(campanha == null)
			throw new RegistoNaoEncontradoException("Registo de campanha não encontrada.");
		return campanha;
	}
	
	public Campanha campanha(Integer id) {
		Campanha campanha = this.campanhaRepository.findOne(id);
		if(campanha == null)
			throw new RegistoNaoEncontradoException("Registo de campanha não encontrada.");
		return campanha;
	}
	
	public void salvar(Campanha campanhaIntroduzida) {
		Campanha campanha = this.campanhaRepository.findByDesigancao(campanhaIntroduzida.getDesigancao());
		if(campanha == null)
			this.campanhaRepository.save(campanhaIntroduzida);
		else
			throw new DadosDuplicadoException("Já existe uma camapanha com esta designação");
	}
	
}
