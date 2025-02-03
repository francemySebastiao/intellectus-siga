package ao.co.intellectus.servico.cafold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.PermissaoCurso;
import ao.co.intellectus.repository.PermissaoCursoRepository;

@Service
public class PermissaoService {
	
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private PermissaoCursoRepository permissaoCursoRepository;
	
	public PermissaoCurso permissaoAoCurso(String numeroDoAluno, String userName) {
		Aluno aluno = this.alunoService.aluno(numeroDoAluno);
		return this.permissaoCursoRepository.findByCursoAndUsuarioUserName(aluno.getCurso(), userName);
	}

}
