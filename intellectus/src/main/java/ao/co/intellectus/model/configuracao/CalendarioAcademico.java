package ao.co.intellectus.model.configuracao;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ao.co.intellectus.model.AnoLectivo;

@Entity
@Table(name = "t_calendario_academico")
public class CalendarioAcademico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	@JoinColumn(name = "ano_lectivo_codigo")
	private AnoLectivo anoLectivo;
	private Date inicioDaCandidatura;
	private Date fimDaCandidatura;
	private Date inicioDaInscricao;
	private Date fimDaInscricao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Date getInicioDaCandidatura() {
		return inicioDaCandidatura;
	}

	public void setInicioDaCandidatura(Date inicioDaCandidatura) {
		this.inicioDaCandidatura = inicioDaCandidatura;
	}

	public Date getFimDaCandidatura() {
		return fimDaCandidatura;
	}

	public void setFimDaCandidatura(Date fimDaCandidatura) {
		this.fimDaCandidatura = fimDaCandidatura;
	}

	public Date getInicioDaInscricao() {
		return inicioDaInscricao;
	}

	public void setInicioDaInscricao(Date inicioDaInscricao) {
		this.inicioDaInscricao = inicioDaInscricao;
	}

	public Date getFimDaInscricao() {
		return fimDaInscricao;
	}

	public void setFimDaInscricao(Date fimDaInscricao) {
		this.fimDaInscricao = fimDaInscricao;
	}

}
