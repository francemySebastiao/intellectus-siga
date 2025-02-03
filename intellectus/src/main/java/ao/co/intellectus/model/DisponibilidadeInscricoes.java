package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_disponibilidade_inscricoes")
public class DisponibilidadeInscricoes {
	@Id
	@GeneratedValue
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	@ManyToOne
	@JoinColumn(name="codigo_curso")
	private Curso curso; 
	@ManyToOne
	@JoinColumn(name="codigo_ano_lectivo")
	private AnoLectivo anoLectivo; 
	private Integer anoCurricuar;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Integer getAnoCurricuar() {
		return anoCurricuar;
	}
	public void setAnoCurricuar(Integer anoCurricuar) {
		this.anoCurricuar = anoCurricuar;
	}
}
