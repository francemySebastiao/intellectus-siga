package ao.co.intellectus.servico.Auditoria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.Auditoria.AlunoAvaliacoesDTO;
import ao.co.intellectus.DTO.Auditoria.AvaliacoAuditoriaDto;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.HistoricoAlunoAudit;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.HistoricoAlunoAuditRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@Service
public class AvaliacoesAuditService {

	@Autowired
	private HistoricoAlunoAuditRepository hRepository;
	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	
	public  ResponseEntity<ResponseCliente> buscarAvaliacoesPorNumero(String numero){
		   ResponseCliente r = new ResponseCliente();
			
			Aluno aluno = alunoRepository.findByNumeroDeAluno(numero);

			if (aluno == null) {
				r.setMensagem("Nenhum Aluno encontrado.");
				r.setCodigo(ResponseCode.values()[2].getDescricao());
				return new ResponseEntity<ResponseCliente>(r, HttpStatus.OK);
			}
		
		List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);
		
		Matricula mm = this.matriculaRepository.findByAlunoAndAnoLectivoAndAnulado(aluno, anoLectivo.get(0),
				false);
		
		
		List<HistoricoAlunoAudit> historico= this.hRepository.findByNumeroDeAluno(numero);
		if(historico.isEmpty()) {
			return this.httpResponse.MensagemDeRetorno(2, "Estudante não possui alterações.");
		}
		List<AvaliacoAuditoriaDto> avaliacoes=new ArrayList<AvaliacoAuditoriaDto>();
		
		AlunoAvaliacoesDTO alunos=new AlunoAvaliacoesDTO();
		 alunos.setContencioso(aluno.isContencioso());
		    alunos.setCurso(aluno.getCurso().getDescricao());
		    alunos.setDataFimCurso(aluno.getDataFimDoCurso());
		    alunos.setFimCurso(aluno.isFimCurso());
		    alunos.setNumeroAluno(aluno.getNumeroDeAluno());
		    alunos.setNomeAluno(aluno.getNome());
		    //alunos.setAnulado(mm.isAnulado());
		
		for(HistoricoAlunoAudit c:historico) {
			AvaliacoAuditoriaDto avaliacao =new AvaliacoAuditoriaDto();
			avaliacao.setAnoCurricular(c.getAnoCorricular());
			avaliacao.setAnoLectivoDescricao(c.getAnoLectivo().getAnoLectivoDescricao());
			avaliacao.setDisciplina(c.getDisciplina().getDescricao());
			avaliacao.setAprovado(c.isAprovado());
			if(c.getAvaliacao1()!=null) {
			    avaliacao.setPrimeiraFrequencia(c.getAvaliacao1());
			    avaliacao.setDataPrimeiraFrequencia(c.getDataPrimeiraFrequencia());
			    
			}
			if(c.getUsuarioPrimeiraFrequencia()!=null) {
				avaliacao.setUsuarioPrimeiraFrequencia(c.getUsuarioPrimeiraFrequencia().getName());
			}
			if(c.getAvaliacao2()!=null) {
				avaliacao.setSegundaFrequencia(c.getAvaliacao2());
				avaliacao.setDataSegundaFrequencia(c.getDataSegundaFrequencia());
				
			}
			if(c.getUsuarioSegundaFrequencia()!=null) {
				avaliacao.setUsuarioSegundaFrequencia(c.getUsuarioSegundaFrequencia().getName());
			}
			if(c.getAvaliacao3()!=null) {
				avaliacao.setTerceiraFrequencia(c.getAvaliacao3());
				avaliacao.setDataTerceiraFrequencia(c.getDataTerceiraFrequencia());
				
			if(c.getUsuarioTerceiraFrequencia()!=null) {
				avaliacao.setUsuarioTerceiraFrequencia(c.getUsuarioTerceiraFrequencia().getName());
			}
			}
			if(c.getAvaliacao4()!=null) {
				avaliacao.setQuartaFrequencia(c.getAvaliacao4());
				avaliacao.setDataQuartaFrequencia(c.getDataQuartaFrequencia());
				
			}
			if(c.getUsuarioQuartaFrequencia()!=null) {
				avaliacao.setUsuarioQuartaFrequencia(c.getUsuarioQuartaFrequencia().getName());
			}
			
			if(c.getUsuarioInscreveu()!=null) {
			    avaliacao.setDataInscricao(c.getDataInscricao());
			    avaliacao.setUsuarioInscreveu(c.getUsuarioInscreveu().getName());
			}
			
			if(c.getDataAnulacao()!=null) {
			    avaliacao.setDataAnulacao(c.getDataAnulacao());
			}
			
			if(c.getNotaExame()!=null) {
			    avaliacao.setDataExame(c.getDataExame());
			    avaliacao.setNotaExame(c.getNotaExame());
			    
			}
			if(c.getUsuarioExame()!=null) {
				avaliacao.setUsuarioNotaProjecto(c.getUsuarioExame().getName());
			}
			
			if(c.getNotaExame()!=null) {
			    avaliacao.setDataExameOral(c.getDataExameOral());
			    avaliacao.setNotaExameOral(c.getNotaExameOral());
			}
			if(c.getUsuarioExameOral()!=null) {
				avaliacao.setUsuarioExameOral(c.getUsuarioExameOral().getName());
			}
			
			if(c.getNotaEpocaEspecial()!=null) {
			    avaliacao.setDataInscricaoEpocaEspecial(c.getDataInscricaoEpocaEspecial());
			    avaliacao.setDataNotaEpocaEspecial(c.getDataNotaEpocaEspecial());
			    avaliacao.setNotaEpocaEspecial(c.getNotaEpocaEspecial());
			    
		    }
			if(c.getUsuarioEspecial()!=null) {
				avaliacao.setUsuarioEspecial(c.getUsuarioEspecial().getName());
			}
			if(c.getNotaEpocaEspecialOral()!=null) {
				avaliacao.setDataNotaEpocaEspecialOral(c.getDataNotaEpocaEspecialOral());
				avaliacao.setNotaEpocaEspecialOral(c.getNotaEpocaEspecialOral());
			    }
			if(c.getUsuarioEspecialOral()!=null) {
				avaliacao.setUsuarioEspecialOral(c.getUsuarioEspecialOral().getName());
			}
			
			if(c.getNotaFinal()!=null) {
		        avaliacao.setDataNotaFinal(c.getDataNotaFinal());
		        avaliacao.setNotaFinal(c.getNotaFinal());
			}
			if(c.getMelhoriaNota()!=null) {
		       avaliacao.setDataInscricaoMelhoria(c.getDataInscricaoMelhoria());
		       avaliacao.setDataMelhoria(c.getDataMelhoria());
		       avaliacao.setMelhoriaNota(c.getMelhoriaNota());
		       avaliacao.setNotaFinalMelhoria(c.getNotaFinalMelhoria());
		       
			}
			if(c.getUsuarioMelhoria()!=null) {
				avaliacao.setUsuarioMelhoria(c.getUsuarioMelhoria().getName());
			}
			if(c.getMelhoriaNotaOral()!=null) {
			   avaliacao.setDataMelhoriaOral(c.getDataMelhoriaOral());
			   avaliacao.setMelhoriaNotaOral(c.getMelhoriaNotaOral());
	           
			}
			if(c.getUsuarioMelhoriaOral()!=null) {
				avaliacao.setUsuarioMelhoriaOral(c.getUsuarioMelhoriaOral().getName());
			}
			if(c.getNotaCursoDeVerao()!=null) {
				avaliacao.setNotaCursoDeVerao(c.getNotaCursoDeVerao());
				avaliacao.setDataNotaCursoVerao(c.getDataNotaCursoVerao());
			}
			if(c.getUsuarioCursoVerao()!=null) {
				avaliacao.setUsuarioCursoVerao(c.getUsuarioCursoVerao().getName());
			}
		   if(c.isExtraordinaria()==true) {
			   avaliacao.setExtraordinaria(c.isExtraordinaria());
			   avaliacao.setDataNotaExtraordinaria(c.getDataNotaExtraordinaria());
			   avaliacao.setNotaExtraodinaria(c.getNotaExtraodinaria());
			  
		   }
		   if(c.getUsuarioExtraordinaria()!=null) {
			   avaliacao.setUsuarioExtraordinaria(c.getUsuarioExtraordinaria().getName());
			}
		   if(c.getNotaFinalContinua()!=null) {
			   avaliacao.setDataNotaFinalContinua(c.getDataNotaFinalContinua());
			   avaliacao.setNotaFinalContinua(c.getNotaFinalContinua());			   
		   }
		   if(c.getNotaPratica()!=null) {
			   avaliacao.setDataNotaPratica(c.getDataNotaPratica());
			   avaliacao.setNotaPratica(c.getNotaPratica());
			   avaliacao.setUsuarioPratica(c.getUsuarioPratica().getName());
		   }
		   if(c.getProjectoNota()!=null){
			   avaliacao.setDataNotaPratica(c.getDataNotaPratica());
			   avaliacao.setProjectoNota(c.getProjectoNota());
		   }
		   if(c.getUsuarioNotaProjecto()!=null) {
			   avaliacao.setUsuarioNotaProjecto(c.getUsuarioNotaProjecto().getName());
			}
		   if(c.getNotaRecursoOral()!=null) {
			   avaliacao.setDataNotaRecursoOral(c.getDataNotaRecursoOral());
			   avaliacao.setNotaRecursoOral(c.getNotaRecursoOral());
			   
		   }
		   if(c.getUsuarioRecursoOral()!=null) {
			   avaliacao.setUsuarioRecursoOral(c.getUsuarioRecursoOral().getName());
			}
		   if(c.getNotaRecurso()!=null) {
			   avaliacao.setDataInscricaoRecurso(c.getDataInscricaoRecurso());
			   avaliacao.setDataRecurso(c.getDataRecurso());
			   avaliacao.setNotaRecurso(c.getNotaRecurso());
		   }
		   if(c.getNotaFinalRecurso()!=null) { 
			   avaliacao.setNotaFinalRecurso(c.getNotaFinalRecurso());
		   }
		   if(c.getUsuarioRecurso()!=null) {
		      avaliacao.setUsuarioRecurso(c.getUsuarioRecurso().getName());
		   } 
		   if(c.isFechada()==true) {
			   avaliacao.setFechada(c.isFechada());
		   }
		   if(c.isValidada()==true) {
			   avaliacao.setValidada(c.isValidada());
			   avaliacao.setDataValidacao(c.getDataValidacao());
			   
		   }
		   if(c.getUsuarioValidou()!=null) {
			   avaliacao.setUsuarioValidou(c.getUsuarioValidou().getName());
			}
		   
		   avaliacoes.add(avaliacao);
		   
		}
		alunos.setAvaliacoes(avaliacoes);
		
		return ObjectoDeRetornoParcial.MensagemDeRetorno(alunos, 0, null);
		
		
	}
	
	
	
}
