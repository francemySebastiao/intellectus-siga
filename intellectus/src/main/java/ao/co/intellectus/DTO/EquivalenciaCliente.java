package ao.co.intellectus.DTO;

import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.TipoEquivalencia;

public class EquivalenciaCliente {
	private Integer id;
	private Integer aluno;
	@Enumerated(EnumType.ORDINAL)
	private TipoEquivalencia tipo;
	@Temporal(TemporalType.DATE)
	private Date dataEquivalencia;
	private String escolaOrigem;
	private String cursoOrigem;
	private Integer cursoDestino;
	private Integer anoLectivo;
	private String userName;
	private Integer userCode;
	private List<DisciplinaEquivalencia> disciplinas;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAluno() {
		return aluno;
	}
	public void setAluno(Integer aluno) {
		this.aluno = aluno;
	}
	public TipoEquivalencia getTipo() {
		return tipo;
	}
	public void setTipo(TipoEquivalencia tipo) {
		this.tipo = tipo;
	}
	public Date getDataEquivalencia() {
		return dataEquivalencia;
	}
	public void setDataEquivalencia(Date dataEquivalencia) {
		this.dataEquivalencia = dataEquivalencia;
	}
	public String getEscolaOrigem() {
		return escolaOrigem;
	}
	public void setEscolaOrigem(String escolaOrigem) {
		this.escolaOrigem = escolaOrigem;
	}
	public String getCursoOrigem() {
		return cursoOrigem;
	}
	public void setCursoOrigem(String cursoOrigem) {
		this.cursoOrigem = cursoOrigem;
	}
	public Integer getCursoDestino() {
		return cursoDestino;
	}
	public void setCursoDestino(Integer cursoDestino) {
		this.cursoDestino = cursoDestino;
	}
	public List<DisciplinaEquivalencia> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<DisciplinaEquivalencia> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserCode() {
		return userCode;
	}
	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}	
	
}