package ao.co.intellectus.servico.Auditoria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.Auditoria.MatriculAuditoriaDto;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.MatriculaAudit;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.MatriculaAuditRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@Service
public class MatriculaAuditService {
	
	@Autowired
	private MatriculaAuditRepository mRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private HttpResponse httpResponse;
	
	
	public ResponseEntity<ResponseCliente> buscarMatriculasPorNumero(String numero){
		   ResponseCliente r = new ResponseCliente();
			
			Aluno aluno = alunoRepository.findByNumeroDeAluno(numero);

			if (aluno == null) {
				r.setMensagem("Nenhum Aluno encontrado.");
				r.setCodigo(ResponseCode.values()[2].getDescricao());
				return new ResponseEntity<ResponseCliente>(r, HttpStatus.OK);
			}
		List<MatriculaAudit> matriculas= mRepository.findByNumeroDeAluno(numero);
		if(matriculas.isEmpty()) {
			return this.httpResponse.MensagemDeRetorno(2, "Estudante não possui alterações.");
		}
		System.out.println(matriculas);
		List<MatriculAuditoriaDto> matriculaAudt = new ArrayList<MatriculAuditoriaDto>();
		for(MatriculaAudit c :matriculas) {
			MatriculAuditoriaDto matricula = new MatriculAuditoriaDto();
			matricula.setAnoCurricular(c.getAnoCurricular());
			matricula.setAnoLectivoMatricula(c.getAnoLectivoMatricula());
			matricula.setAnulado(c.isAnulado());
			matricula.setNomeAluno(aluno.getNome());
			
			if(c.getUsuarioAlterou()!=null) {
			matricula.setNomeUseAlterou(c.getUsuarioAlterou().getName());
			matricula.setDataAlteracao(c.getDataAlteracao());
			}
			
			matricula.setNomeUserinscreveu(c.getUsuarioInscreveu().getName());
			matricula.setDataMatricula(c.getData());
			
			if(c.getAlterouTipoInscricao()!=null) {
			matricula.setUserAlterouTipoInscricao(c.getAlterouTipoInscricao().getName());
			matricula.setDataAlteracaoTipoInscricao(c.getDataAlteracaoTipoInscricao());
			}
			
			if(c.getUsuarioAnulou()!=null) {
			matricula.setUserAnulou(c.getUsuarioAnulou().getName());
			}
			if(c.getDataAnulamento()!=null) {
			matricula.setDataAnulamento(c.getDataAnulamento());
			}
			matricula.setCurso(c.getCurso().getDescricao());
			matricula.setTipoInscricao(c.getTipoInscricao().getDescricao());
			matricula.setTurmaBase(c.getTurmaBase().getTurma());
			matricula.setPlanoPagamento(c.getPlanoPagamento().getDescricao());
			
			matriculaAudt.add(matricula);
		}
		
		return ObjectoDeRetornoParcial.MensagemDeRetorno(matriculaAudt, 0, null);
	}

}
