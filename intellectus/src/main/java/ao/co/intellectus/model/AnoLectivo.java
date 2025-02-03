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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_ano_lectivo")
public class AnoLectivo {
	@Id
	@GeneratedValue
	private Integer id;
	@NotNull
	private Integer anoLectivo;
	private boolean status;
	private String anoLectivoDescricao;

	/* PRIMEIRO SEMESTRE */
	@Temporal(TemporalType.DATE)
	private Date inicioPrimeiroSemestre;
	@Temporal(TemporalType.DATE)
	private Date fimPrimeiroSemestre;
	@Temporal(TemporalType.DATE)
	private Date inicioExamePrimeiroSemestre;
	@Temporal(TemporalType.DATE)
	private Date fimExamePrimeiroSemestre;
	@Temporal(TemporalType.DATE)
	private Date inicioRecursoPrimeiroSemestre;
	@Temporal(TemporalType.DATE)
	private Date fimRecursoPrimeiroSemestre;
	/* SEGUNDO SEMESTRE */
	@Temporal(TemporalType.DATE)
	private Date inicioSegundoSemestre;
	@Temporal(TemporalType.DATE)
	private Date fimSegundoSemestre;
	@Temporal(TemporalType.DATE)
	private Date inicioExameSegundoSemestre;
	@Temporal(TemporalType.DATE)
	private Date fimExameSegundoSemestre;
	@Temporal(TemporalType.DATE)
	private Date inicioRecursoSegundoSemestre;
	@Temporal(TemporalType.DATE)
	private Date fimRecursoSegundoSemestre;
	// INSTITUICAO DE REFERENCIA...
	@ManyToOne
	@JoinColumn(name = "codigo_instituicao")
	@NotNull
	private Instituicao instit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getAnoLectivoDescricao() {
		return anoLectivoDescricao;
	}

	public void setAnoLectivoDescricao(String anoLectivoDescricao) {
		this.anoLectivoDescricao = anoLectivoDescricao;
	}

	public Date getInicioPrimeiroSemestre() {
		return inicioPrimeiroSemestre;
	}

	public void setInicioPrimeiroSemestre(Date inicioPrimeiroSemestre) {
		this.inicioPrimeiroSemestre = inicioPrimeiroSemestre;
	}

	public Date getFimPrimeiroSemestre() {
		return fimPrimeiroSemestre;
	}

	public void setFimPrimeiroSemestre(Date fimPrimeiroSemestre) {
		this.fimPrimeiroSemestre = fimPrimeiroSemestre;
	}

	public Date getInicioExamePrimeiroSemestre() {
		return inicioExamePrimeiroSemestre;
	}

	public void setInicioExamePrimeiroSemestre(Date inicioExamePrimeiroSemestre) {
		this.inicioExamePrimeiroSemestre = inicioExamePrimeiroSemestre;
	}

	public Date getFimExamePrimeiroSemestre() {
		return fimExamePrimeiroSemestre;
	}

	public void setFimExamePrimeiroSemestre(Date fimExamePrimeiroSemestre) {
		this.fimExamePrimeiroSemestre = fimExamePrimeiroSemestre;
	}

	public Date getInicioRecursoPrimeiroSemestre() {
		return inicioRecursoPrimeiroSemestre;
	}

	public void setInicioRecursoPrimeiroSemestre(Date inicioRecursoPrimeiroSemestre) {
		this.inicioRecursoPrimeiroSemestre = inicioRecursoPrimeiroSemestre;
	}

	public Date getFimRecursoPrimeiroSemestre() {
		return fimRecursoPrimeiroSemestre;
	}

	public void setFimRecursoPrimeiroSemestre(Date fimRecursoPrimeiroSemestre) {
		this.fimRecursoPrimeiroSemestre = fimRecursoPrimeiroSemestre;
	}

	public Date getInicioSegundoSemestre() {
		return inicioSegundoSemestre;
	}

	public void setInicioSegundoSemestre(Date inicioSegundoSemestre) {
		this.inicioSegundoSemestre = inicioSegundoSemestre;
	}

	public Date getFimSegundoSemestre() {
		return fimSegundoSemestre;
	}

	public void setFimSegundoSemestre(Date fimSegundoSemestre) {
		this.fimSegundoSemestre = fimSegundoSemestre;
	}

	public Date getInicioExameSegundoSemestre() {
		return inicioExameSegundoSemestre;
	}

	public void setInicioExameSegundoSemestre(Date inicioExameSegundoSemestre) {
		this.inicioExameSegundoSemestre = inicioExameSegundoSemestre;
	}

	public Date getFimExameSegundoSemestre() {
		return fimExameSegundoSemestre;
	}

	public void setFimExameSegundoSemestre(Date fimExameSegundoSemestre) {
		this.fimExameSegundoSemestre = fimExameSegundoSemestre;
	}

	public Date getInicioRecursoSegundoSemestre() {
		return inicioRecursoSegundoSemestre;
	}

	public void setInicioRecursoSegundoSemestre(Date inicioRecursoSegundoSemestre) {
		this.inicioRecursoSegundoSemestre = inicioRecursoSegundoSemestre;
	}

	public Date getFimRecursoSegundoSemestre() {
		return fimRecursoSegundoSemestre;
	}

	public void setFimRecursoSegundoSemestre(Date fimRecursoSegundoSemestre) {
		this.fimRecursoSegundoSemestre = fimRecursoSegundoSemestre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnoLectivo other = (AnoLectivo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Instituicao getInstit() {
		return instit;
	}

	public void setInstit(Instituicao instit) {
		this.instit = instit;
	}
}