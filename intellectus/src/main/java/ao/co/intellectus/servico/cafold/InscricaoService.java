package ao.co.intellectus.servico.cafold;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.InscricaoPorAno;
import ao.co.intellectus.model.Matricula;

@Service
public class InscricaoService {
	
	public List<InscricaoPorAno> todasInscricoesPorAnoDeUmAluno(List<Matricula> todasMatriculasDeUmAluno){
		List<InscricaoPorAno> inscricoes = new ArrayList<InscricaoPorAno>();
		for(Matricula matricula : todasMatriculasDeUmAluno)  {
			InscricaoPorAno inscricao = new InscricaoPorAno();
			inscricao.setId(matricula.getId());
			inscricao.setAnoCurricular(matricula.getAnoCurricular());
			inscricao.setAnoLectivo(matricula.getAnoLectivo().getAnoLectivo());
			inscricao.setCurso(matricula.getCurso().getPlano());
			inscricao.setDataInscricao(matricula.getData());
			inscricao.setDesconto(matricula.getPercentagemDesconto());

			if(matricula.getTurmaBase()!=null)
			inscricao.setTurma(matricula.getTurmaBase().getTurma());
			
			if(matricula.getUsuarioInscreveu()!=null)
			  inscricao.setUsuario(matricula.getUsuarioInscreveu().getUserName());
			

			if (matricula.getPlanoPagamento() != null)
				inscricao.setPlanoPagamento(matricula.getPlanoPagamento().getDescricao());
			if (matricula.getTipoInscricao() != null)
				inscricao.setTipoInscricao(matricula.getTipoInscricao().getDescricao());

			inscricao.setSigla(matricula.getCurso().getSigla());
			inscricao.setAnulado(matricula.isAnulado());
			inscricao.setDataAnulamento(matricula.getDataAnulamento());
			inscricoes.add(inscricao);			
		}
		return inscricoes;
	}
	
	

}
