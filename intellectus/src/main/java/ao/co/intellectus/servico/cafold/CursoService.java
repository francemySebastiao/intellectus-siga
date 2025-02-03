package ao.co.intellectus.servico.cafold;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.CursoEnsinoMedio;
import ao.co.intellectus.model.Faculdade;
import ao.co.intellectus.repository.CursoEnsinoMedioRepository;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private CursoEnsinoMedioRepository cursoEnsinoMedioREpository;
	
	public Curso curso(Integer id) {
		return cursoRepository.findById(id);
	}
	
	public List<Curso> cursos(Faculdade faculdade) {
		return cursoRepository.findByFaculdade(faculdade);
	}
	
	public Curso activo(Integer id) {
		Curso curso = this.cursoRepository.findByIdAndStatus(id, true);
		if(curso == null)
			throw new RegistoNaoEncontradoException("Nenhum registo de curso encontrado.");
		return curso;	
	}
	
	public CursoEnsinoMedio cursoEnsinoMedio(Integer id) {
		CursoEnsinoMedio cursoEnsinoMedio = this.cursoEnsinoMedioREpository.findOne(id);
		if(cursoEnsinoMedio == null)
			throw new RegistoNaoEncontradoException("Nenhum registo de curso médio encontrado.");
		return cursoEnsinoMedio;	
	}
	
	
	
	

}
