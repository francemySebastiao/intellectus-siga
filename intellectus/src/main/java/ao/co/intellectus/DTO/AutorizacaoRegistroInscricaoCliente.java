package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.TipoDisciplina;

public class AutorizacaoRegistroInscricaoCliente {
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	@Temporal(TemporalType.DATE)
	private Date dataFim;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraInicio;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraFim;
	@Enumerated(EnumType.STRING)
	private TipoDisciplina tipoDisciplina;
	private Integer anoLectivo;
	private Integer prova;
	@Enumerated(EnumType.STRING)
	private Grau grau;
	
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
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}
	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}
	public Date getDataHoraFim() {
		return dataHoraFim;
	}
	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}
	public TipoDisciplina getTipoDisciplina() {
		return tipoDisciplina;
	}
	public void setTipoDisciplina(TipoDisciplina tipoDisciplina) {
		this.tipoDisciplina = tipoDisciplina;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Integer getProva() {
		return prova;
	}
	public void setProva(Integer prova) {
		this.prova = prova;
	}
	public Grau getGrau() {
		return grau;
	}
	public void setGrau(Grau grau) {
		this.grau = grau;
	}
}
