package ao.co.intellectus.repository.aluno;

import java.util.List;

import ao.co.intellectus.config.filtro.AlunoFilter;
import ao.co.intellectus.model.Aluno;

public interface AlunoRepositoryQuery {
     public List<Aluno> filtrar(AlunoFilter alunoFilter);
}
