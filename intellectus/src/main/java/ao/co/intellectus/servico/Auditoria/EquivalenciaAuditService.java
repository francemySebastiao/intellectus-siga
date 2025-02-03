package ao.co.intellectus.servico.Auditoria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.Auditoria.EquivalenciaAuditoriaDto;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.HistoricoEquivalencia;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.EquivalenciaHistoricoRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@Service
public class EquivalenciaAuditService {
	
	@Autowired
	private EquivalenciaHistoricoRepository hRepository;
	@Autowired
	private AlunoRepository aRepository;
	@Autowired
	private HttpResponse httpResponse;
	
	
	public ResponseEntity<ResponseCliente> buscarEquivalenciasPorNumero(String numero){
           ResponseCliente r = new ResponseCliente();
		
		Aluno aluno = aRepository.findByNumeroDeAluno(numero);

		if (aluno == null) {
			r.setMensagem("Nenhum Aluno encontrado.");
			r.setCodigo(ResponseCode.values()[2].getDescricao());
			return new ResponseEntity<ResponseCliente>(r, HttpStatus.OK);
		}
		List<HistoricoEquivalencia> equivalencias = this.hRepository.findByAluno(aluno);
		if(equivalencias.isEmpty()) {
			return this.httpResponse.MensagemDeRetorno(2, "Estudante não possui alterações.");
		}
		List<EquivalenciaAuditoriaDto> lista=new ArrayList<EquivalenciaAuditoriaDto>();
		
		for(HistoricoEquivalencia c :equivalencias ) {
			EquivalenciaAuditoriaDto equi= new EquivalenciaAuditoriaDto();
			equi.setAnoLectivoDescricao(c.getEquivalencia().getAnoLectivo().getAnoLectivoDescricao());
			equi.setCursoDestino(c.getEquivalencia().getCursoDestino().getDescricao());
			equi.setCursoOrigem(c.getEquivalencia().getCursoOrigem());
			if(c.isPrimeiraValidacao()==true) {
				equi.setDataPrimeiraValidacao(c.getDataPrimeiraValidacao());
				equi.setPrimeniraValidacao(c.isPrimeiraValidacao());
			}
			if(c.isPrimeiraValidacao()==true) {
				equi.setDataSegundaValidacao(c.getDataSegundaValidacao());
				equi.setSegundaValidacao(c.isSegundaValidacao());
			}
			if(c.isPrimeiraValidacao()==true) {
				equi.setDataTerceiraValidacao(c.getDataTerceiraValidacao());
				equi.setTerceiraValidacao(c.isTerceiraValidacao());
			}
			equi.setDataEquivalencia(c.getEquivalencia().getDataEquivalencia());
			equi.setDisciplinaDestino(c.getDisciplinaDestino().getDescricao());
			equi.setDisciplinaOrigem(c.getDisciplinaOrigem());
			equi.setEscolaOrigem(c.getEquivalencia().getEscolaOrigem());
			equi.setEstado(c.isEstado());
			equi.setFechada(c.getEquivalencia().isFechada());
			equi.setNotaOrigem(c.getNotaOrigem());
			equi.setUserRealizouEquivalencia(c.getEquivalencia().getUsuarioEquivalencia().getName());
			
			lista.add(equi);
			 
		}
		
		
		return ObjectoDeRetornoParcial.MensagemDeRetorno(lista, 0, null);
	}
	
}
