package ao.co.intellectus.servico.Auditoria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.AnosInscricoes;
import ao.co.intellectus.DTO.Auditoria.AlunoAuditDTO;
import ao.co.intellectus.DTO.Auditoria.FichaAcademicaAlunoAuditoriaDto;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AlunoAudit;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoAuditRepository;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@Service
public class FichaAcademicaAuditService {
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private AlunoAuditRepository alunoAuditRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private HttpResponse httpResponse;
	
	public ResponseEntity<ResponseCliente> buscarFichaPorNumero(String numero){
		
		ResponseCliente r = new ResponseCliente();
		
		Aluno aluno = alunoRepository.findByNumeroDeAluno(numero);

		if (aluno == null) {
			r.setMensagem("Nenhum Aluno encontrado.");
			r.setCodigo(ResponseCode.values()[2].getDescricao());
			return new ResponseEntity<ResponseCliente>(r, HttpStatus.OK);
		}
		
		List<AlunoAudit> audit= this.alunoAuditRepository.findByNumeroDeAluno(numero);
		
		List<Matricula> matricula=this.matriculaRepository.findByAluno(aluno);
		
		List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);
		
		Matricula mm = this.matriculaRepository.findByAlunoAndAnoLectivoAndAnulado(aluno, anoLectivo.get(0),
				false);
		
		List<AlunoAuditDTO> alunos= new ArrayList<AlunoAuditDTO>();
		
		List<AnosInscricoes> inscricoesAluno = new ArrayList<AnosInscricoes>();
		
		FichaAcademicaAlunoAuditoriaDto ficha= new FichaAcademicaAlunoAuditoriaDto();
		if (mm != null) {
			ficha.setBolseiro(mm.getEmpresaConvenio() != null ? true : false);
		}
		    ficha.setContencioso(aluno.isContencioso());
		    ficha.setCurso(aluno.getCurso().getDescricao());
		    ficha.setDataFimCurso(aluno.getDataFimDoCurso());
		    ficha.setFimCurso(aluno.isFimCurso());
		    ficha.setNumeroAluno(aluno.getNumeroDeAluno());
		    ficha.setNomeAluno(aluno.getNome());
		    //ficha.setAnulado(mm.isAnulado());
		    
		    if(audit.isEmpty()) {
				return this.httpResponse.MensagemDeRetorno(2, "Este aluno não possui alteraçõess", ficha);
			}
		for(AlunoAudit c:audit) {
			AlunoAuditDTO alunoAudit= new AlunoAuditDTO();
			alunoAudit.setNomeAluno(c.getNome());
			if(c.getUsuario()!=null) {
			alunoAudit.setNomeDoUsuariQueAlterou(c.getUsuario().getName());
			}else {
				alunoAudit.setNomeDoUsuariQueAlterou(null);	
			}
			if(c.getObservacao()!=null) {
			alunoAudit.setMotivoAlteracao(c.getObservacao());
			}else {
				alunoAudit.setMotivoAlteracao(null);
			}
			if(c.getUltimaModificacao()!=null) {
				alunoAudit.setDataAlteracao(c.getUltimaModificacao());
			}else {
				alunoAudit.setDataAlteracao(null);
			}
			
			alunoAudit.setDataNascimento(c.getDataNascimento());
			alunoAudit.setNacionalidade(c.getNacionalidade().getDescricao());
			alunoAudit.setProvincia(c.getProvincia().getProvincia());
			alunoAudit.setMunicipio(c.getMunicipio().getDescricao());
			if(c.getPaisDeResidencia()!=null) {
			alunoAudit.setPaisDeResidencia(c.getPaisDeResidencia().getDescricao());
			}
			if(c.getProvinciaResidencia()!=null) {
			alunoAudit.setProvinciaResidencia(c.getProvinciaResidencia().getProvincia());
			}
			if(c.getMunicipioResidencia()!=null) {
			alunoAudit.setMunicipioResidencia(c.getMunicipioResidencia().getDescricao());
			}
			alunoAudit.setTelefone(c.getTelefone());
			alunoAudit.setNomeDoPai(c.getNomeDoPai());
			alunoAudit.setNomeDaMae(c.getNomeDaMae());
			
            alunos.add(alunoAudit);
		}
		ficha.setAlunosAuditDTO(alunos);
		
		AnosInscricoes anosInscricoes;
		for(Matricula m: matricula) {
			anosInscricoes = new AnosInscricoes();
			anosInscricoes.setAnoLectivo(m.getAnoLectivo().getAnoLectivo());
			anosInscricoes.setAnoLectivoDescricao(m.getAnoLectivo().getAnoLectivoDescricao());
			anosInscricoes.setCodigoAnoLectivo(m.getAnoLectivo().getId());
			if(m.getEmpresaConvenio()!=null) {
			anosInscricoes.setEmpresaConvenio(m.getEmpresaConvenio().getDesignacao());
			anosInscricoes.setBolseiro(true);
			
			inscricoesAluno.add(anosInscricoes);
		}
			ficha.setAnosInscricoes(inscricoesAluno);
		}	
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ficha, 0, null);
	}
	
	

}
