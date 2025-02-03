package ao.co.intellectus.servico.exema_electronico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.exame_electronico.PerguntaExameEletronicoView;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.exame_eletronico.ExameCurso;
import ao.co.intellectus.model.exame_eletronico.Pergunta;
import ao.co.intellectus.model.exame_eletronico.TipoExame;
import ao.co.intellectus.repository.exame_electronico.ExameCursoRepository;
import ao.co.intellectus.repository.exame_electronico.PerguntaRepository;
import ao.co.intellectus.servico.cafold.AnoLectivoService;
import ao.co.intellectus.servico.cafold.CursoService;
import ao.co.intellectus.servico.exception.DadoInvalidoException;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class PerguntaExamElectronicoService {

	@Autowired
	private PerguntaRepository perguntaExamElectronicoRepository;
	@Autowired 
	private AnoLectivoService anoLectivoService;
	@Autowired
	private CursoService cursoService;
	@Autowired
	private ExameCursoRepository exameCursoRepository;
	
	public List<Pergunta> todas(){
		List<Pergunta> todas = this.perguntaExamElectronicoRepository.findAll();
		if(todas.isEmpty())
			throw new RegistoNaoEncontradoException("Nenhum registo de pergunta encontrado.");
		return todas;
	}
	
	public Pergunta pergunta(Integer id) {
		Pergunta pergunta = this.perguntaExamElectronicoRepository.findOne(id);
		if(pergunta == null)
			throw new RegistoNaoEncontradoException("Registo de pergunta não encontrado.");
		return pergunta;
	}
	
	public List<Pergunta> perguntas(TipoExame tipoExame,Curso curso){
		List<Pergunta> perguntas = this.perguntaExamElectronicoRepository.findByTipoExameAndCurso(tipoExame,curso);
		if(perguntas.isEmpty())
			throw new RegistoNaoEncontradoException("Nenhum registo de pergunta encontrado.");
		return perguntas;
	}
	
	public Pergunta salvar(PerguntaExameEletronicoView perguntaExameView) {
		Pergunta pergunta=new Pergunta();
		AnoLectivo anoLectivo = this.anoLectivoService.anoLectivo(perguntaExameView.getAnoLectivo());
		Curso curso = this.cursoService.curso(perguntaExameView.getCurso());
		List<ExameCurso> cursoTemEsseExame = this.exameCursoRepository.findByCursoAndTipoExame(curso, perguntaExameView.getTipoExame());
		if(cursoTemEsseExame.isEmpty()) 
			throw new DadoInvalidoException("O curso seleccionado não possui este exame.");
		if(perguntaExameView.getId() != null)
			pergunta.setId(perguntaExameView.getId());
		pergunta.setClassificacao(perguntaExameView.getClassificacao());
		pergunta.setDescricao(perguntaExameView.getDescricao());
		pergunta.setTipoExame(perguntaExameView.getTipoExame());
		pergunta.setAnoLectivo(anoLectivo);
		pergunta.setCurso(curso);
		return this.perguntaExamElectronicoRepository.save(pergunta);			
	}
	
	public Boolean validarPergunta(Integer pergunta) {
		return this.perguntaExamElectronicoRepository.exists(pergunta);	
	}
	
	public List<Pergunta> pergunta(String descricao,TipoExame tipoExame) {
		return this.perguntaExamElectronicoRepository.findByDescricaoAndTipoExame(descricao, tipoExame);
	}
	
}
