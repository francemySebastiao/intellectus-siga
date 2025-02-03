package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.enumeracao.TipoCandidatura;
import ao.co.intellectus.model.enumeracao.TipoPublicoAlvo;

public class PublicoAlvo {
	private Integer id;
	@Enumerated(EnumType.STRING)
	private TipoPublicoAlvo tipoEnvio;
	private Integer anoLectivo;
	@Enumerated(EnumType.STRING)
	private Grau grauAcademico;
	private Integer curso;
	@Enumerated(EnumType.STRING)
	private TipoCandidatura tipoCandidatura;
	@Temporal(TemporalType.DATE)
	private Date dataInicial;
	@Temporal(TemporalType.DATE)
	private Date dataFim;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoPublicoAlvo getTipoEnvio() {
		return tipoEnvio;
	}

	public void setTipoEnvio(TipoPublicoAlvo tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Grau getGrauAcademico() {
		return grauAcademico;
	}

	public void setGrauAcademico(Grau grauAcademico) {
		this.grauAcademico = grauAcademico;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public TipoCandidatura getTipoCandidatura() {
		return tipoCandidatura;
	}

	public void setTipoCandidatura(TipoCandidatura tipoCandidatura) {
		this.tipoCandidatura = tipoCandidatura;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}
