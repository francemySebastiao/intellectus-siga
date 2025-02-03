package ao.co.intellectus.DTO;

import java.sql.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CalendarioAcademicoView {

	private Integer id;
	private Integer anoLectivo;
	@Temporal(TemporalType.DATE)
	private Date inicioDaCandidatura;
	@Temporal(TemporalType.DATE)
	private Date fimDaCandidatura;
	@Temporal(TemporalType.DATE)
	private Date inicioDaInscricao;
	@Temporal(TemporalType.DATE)
	private Date fimDaInscricao;

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
