package ao.co.intellectus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AlunoAnexo;

public interface AlunoAnexoRepository extends JpaRepository<AlunoAnexo,Integer>{
    public AlunoAnexo findByAluno(Aluno aluno);
    public AlunoAnexo findByAlunoNumeroDeAluno(String numero);
    
    public AlunoAnexo findByNumeroDeAluno(String aluno);
}
