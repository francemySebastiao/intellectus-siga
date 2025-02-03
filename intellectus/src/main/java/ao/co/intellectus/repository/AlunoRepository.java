package ao.co.intellectus.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.repository.aluno.AlunoRepositoryQuery;

public interface AlunoRepository extends JpaRepository<Aluno,Integer>,AlunoRepositoryQuery{
    
	
	public List<Aluno> findByNomeLike(String nome);
    
	
	//public List<Aluno> findByNumeroDeAlunoOrNomeLike(Integer numero,String nome);
    
	
	
	public Aluno findByNumeroDeAluno(String numero); 
	public Aluno findByNumeroDeAlunoAndEmailInstitucionalIsNotNull(String numero);
    public Aluno findByEmailInstitucional(String email);
    
    public Optional<Aluno> findByEmailInstitucionalAndDataNascimento(String email, Date data);
                 
    public List<Aluno> findByNomeLikeOrNumeroDeAluno(String nome,String numero);
    //public Aluno findByNumeroDeAluno(String numero); 
    public Aluno findByNumeroDocumentoAndNome(String numeroDocumento,String nome);
    public Aluno findByNome(String nome);
    
    public List<Aluno> findByNumeroDocumento(String numeroDocumento);
    
    
    //public Aluno findByNomeAndNomeDoPaiAndNomeDaMaeAndNumeroDocumentoAndFimCurso(String nome,String nomeDoPai,String nomeDaMae,String numeroDocumento,boolean fimCurso);
    public List<Aluno> findByNomeAndNomeDoPaiAndNomeDaMaeAndNumeroDocumentoAndFimCurso(String nome,String nomeDoPai,String nomeDaMae,String numeroDocumento,boolean fimCurso);
    
    @Query(value="SELECT * FROM T_ALUNO WHERE NUMERO_DE_ALUNO =:numeroAluno", nativeQuery=true)
	public Aluno buscarAluno(@Param("numeroAluno") Integer numeroAluno);

	

}