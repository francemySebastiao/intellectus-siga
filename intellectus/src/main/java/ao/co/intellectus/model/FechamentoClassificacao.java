package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_fechamento_classificacao")
public class FechamentoClassificacao {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer anoCurricular;
	@Enumerated(EnumType.STRING)
	private Semestre semestre;
	@ManyToOne
	@JoinColumn(name = "codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	@ManyToOne
	@JoinColumn(name = "codigo_curso")
	private Curso curso;
	private boolean validar;
	private boolean fechar;
	private boolean executado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoCurricular() {
		return anoCurricular;
	}

	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public boolean isValidar() {
		return validar;
	}

	public void setValidar(boolean validar) {
		this.validar = validar;
	}

	public boolean isFechar() {
		return fechar;
	}

	public void setFechar(boolean fechar) {
		this.fechar = fechar;
	}

	public boolean isExecutado() {
		return executado;
	}
	public void setExecutado(boolean executado) {
		this.executado = executado;
	}
}
