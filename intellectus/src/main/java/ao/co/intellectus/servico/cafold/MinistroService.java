package ao.co.intellectus.servico.cafold;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.RegistroBaseDadosAproveitamento;
import ao.co.intellectus.model.RegistroBaseDadosGraduadosPreliminarDefinitivo;
import ao.co.intellectus.model.RegistroBaseDadosMatriculaincial;
import ao.co.intellectus.model.RegistroBaseDeDadosPosGraduacao;
import ao.co.intellectus.model.ResgistroBaseDadosAcesso;
import ao.co.intellectus.repository.RegistroBaseDadosAproveitamentoRepository;
import ao.co.intellectus.repository.RegistroBaseDadosGraduadosPreliminarDefinitivoRepository;
import ao.co.intellectus.repository.RegistroBaseDadosMatriculaincialRepository;
import ao.co.intellectus.repository.RegistroBaseDeDadosPosGraduacaoRepository;
import ao.co.intellectus.repository.ResgistroBaseDadosAcessoRepository;

@Service
public class MinistroService {

	@Autowired
	private RegistroBaseDadosAproveitamentoRepository registroBaseDadosAproveitamentoRepository;
	@Autowired
	private RegistroBaseDadosGraduadosPreliminarDefinitivoRepository registroBaseDadosGraduadosPreliminarDefinitivoRepository;
	@Autowired
	private RegistroBaseDadosMatriculaincialRepository registroBaseDadosMatriculaincialRepository;
	@Autowired
	private RegistroBaseDeDadosPosGraduacaoRepository registroBaseDeDadosPosGraduacaoRepository;
	@Autowired
	private ResgistroBaseDadosAcessoRepository resgistroBaseDadosAcessoRepository;
	
	public List<RegistroBaseDadosAproveitamento> registroBaseDadosAproveitamento(Integer anoLectivo) {
		return this.registroBaseDadosAproveitamentoRepository.findByAnolectivo(anoLectivo);
	}
	 
	public List<RegistroBaseDadosGraduadosPreliminarDefinitivo> registroBaseDadosGraduadosPreliminarDefinitivo(Integer anoLectivo) {
		return this.registroBaseDadosGraduadosPreliminarDefinitivoRepository.findByAnoLectivo(anoLectivo);
	}

	public List<RegistroBaseDadosMatriculaincial> registroBaseDadosMatriculaincial(Integer anoLectivo) {
		return this.registroBaseDadosMatriculaincialRepository.findByAnoLectivo(anoLectivo);
	}

	public List<RegistroBaseDeDadosPosGraduacao> registroBaseDeDadosPosGraduacao(Integer anoLectivo) {
		return this.registroBaseDeDadosPosGraduacaoRepository.findByAnoLectivo(anoLectivo);
	}

	public List<ResgistroBaseDadosAcesso> resgistroBaseDadosAcesso(Integer anoLectivo) {
		return this.resgistroBaseDadosAcessoRepository.findByAnoLectivo(anoLectivo);
	}
}
