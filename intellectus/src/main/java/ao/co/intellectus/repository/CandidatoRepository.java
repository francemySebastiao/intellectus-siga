package ao.co.intellectus.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.Sexo;
import ao.co.intellectus.model.Turno;
import ao.co.intellectus.model.enumeracao.TipoCandidatura;

public interface CandidatoRepository extends JpaRepository<Candidato, Integer>{
	public List<Candidato> findByNomeLike(String nome);
	public List<Candidato> findByNumeroCandidato(Integer numero);
	public List<Candidato> findByNumeroCandidatoOrderByAnoLectivoDesc(Integer numero);
	public List<Candidato> findBynumeroDocumentoAndAnoLectivoId(String numeroDocumento,Integer anoLectivo);
	public Candidato findByNumeroCandidatoAndAnoLectivo(Integer numero,AnoLectivo anoLectivo);
	public List<Candidato> findByNomeLikeOrNumeroCandidato(String nome,Integer numero);
	public List<Candidato> findByCursoAndAnoLectivo(Curso curso,AnoLectivo anoLectivo);
	public List<Candidato> findByTurnoAndAnoLectivo(Turno turno,AnoLectivo anoLectivo);
	public List<Candidato> findByCursoAndTurnoAndAnoLectivo(Curso curso,Turno turno,AnoLectivo anoLectivo);
	public List<Candidato> findByDataCandidatura(Date data);
	public List<Candidato> findBySexoAndAnoLectivo(Sexo sexo,AnoLectivo anoLectivo);
	public List<Candidato> findBySexoAndCurso(Sexo sexo,Curso curso);
	public List<Candidato> findBySexoAndCursoAndAnoLectivo(Sexo sexo,Curso curso,AnoLectivo anoLectivo);	
	public List<Candidato> findByAnoLectivoAndCursoAndNotaExameGreaterThanEqual(AnoLectivo anoLectivo,Curso curso,double NotaExame);
	public List<Candidato> findByAnoLectivoAndTurnoAndNotaExameGreaterThanEqual(AnoLectivo anoLectivo,Turno turno,double NotaExame);
	public List<Candidato> findByAnoLectivoAndCursoAndTurnoAndNotaExameGreaterThanEqual(AnoLectivo anoLectivo,Curso curso,Turno turno,double NotaExame);
	public List<Candidato> findByAnoLectivoAndSexoAndNotaExameGreaterThanEqual(AnoLectivo anoLectivo,Sexo sexo,double NotaExame );
	public List<Candidato> findByAnoLectivoAndSexoAndCursoAndNotaExameGreaterThanEqual(AnoLectivo anoLectivo,Sexo sexo,Curso curso,double NotaExame);
	public List<Candidato> findByAnoLectivoAndSexoAndTurnoAndNotaExameGreaterThanEqual(AnoLectivo anoLectivo,Sexo sexo,Turno turno,double NotaExame);
	public List<Candidato> findByAnoLectivoAndSexoAndCursoAndTurnoAndNotaExameGreaterThanEqual(AnoLectivo anoLectivo,Sexo sexo,Curso curso,Turno turno,double NotaExame);
	public Candidato findById(Integer codigo);
	@Query(value="SELECT * FROM T_CANDIDATO WHERE ID=", nativeQuery = true)
	public List<Candidato> buscarCandidatos(Integer id);
	public Candidato findByNumeroDocumento(String numeroDocumento);
	public Candidato findByNumeroDocumentoAndNomeAndAnoLectivo(String bi,String nome,AnoLectivo anoLectivo);
	public Candidato findByNumeroDocumentoAndAnoLectivo(String bi, AnoLectivo anoLectivo);
	public Candidato findByNumeroDocumentoAndAnoLectivoId(String bi,Integer anoLectivo);
	public List<Candidato> findByMensagemEnviada(boolean mensagemEnviada);
	public List<Candidato> findByEmailEnviado(boolean mensagemEnviada);
	@Query(value = "SELECT * FROM T_CANDIDATO A, T_GUIA_CANDIDATURA B WHERE A.ID=B.NUMERO_DE_CANDIDATO AND A.EMAIL_ENVIADO=0 AND B.LIQUIDADA=1",nativeQuery = true)
	public List<Candidato> buscarCandidatosNaoNotificadosComPagamento();
	

	public List<Candidato> findByAnoLectivoIdAndCursoIdAndTurno(Integer anoLectivo,Integer curso,Turno turno);
	public List<Candidato> findByAnoLectivoAndGrauAndTipoCandidatura(AnoLectivo anoLectivo, Grau grauAcademico,TipoCandidatura tipoCandidatura);
	public List<Candidato> findByAnoLectivoAndGrauAndTipoCandidaturaAndDataCandidaturaBetween(AnoLectivo anoLectivo,Grau grauAcademico, TipoCandidatura tipoCandidatura, Date dataInicial, Date dataFim);
	public List<Candidato> findByAnoLectivoIdAndCursoId(Integer anoLectivo, Integer curso);
	
	
}
