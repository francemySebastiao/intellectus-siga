package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CalendarioProcessamentoDTO {
	private Integer id;	
	@Temporal(TemporalType.DATE)
	private Date dataProcessamento; 
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	private String mes;
	private Integer totalGuias;
	private float totalValor;
	private float totalPago;
	private float totalAberto;
	private boolean processado;
	private Integer anoLectivo;
	private boolean mesAProcessar;
	private String propinaAnoCompleto;
	private String proprinaPorDisciplina;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataProcessamento() {
		return dataProcessamento;
	}
	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public Integer getTotalGuias() {
		return totalGuias;
	}
	public void setTotalGuias(Integer totalGuias) {
		this.totalGuias = totalGuias;
	}
	public float getTotalValor() {
		return totalValor;
	}
	public void setTotalValor(float totalValor) {
		this.totalValor = totalValor;
	}
	public float getTotalPago() {
		return totalPago;
	}
	public void setTotalPago(float totalPago) {
		this.totalPago = totalPago;
	}
	public float getTotalAberto() {
		return totalAberto;
	}
	public void setTotalAberto(float totalAberto) {
		this.totalAberto = totalAberto;
	}
	public boolean isProcessado() {
		return processado;
	}
	public void setProcessado(boolean processado) {
		this.processado = processado;
	}
	
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public void setProprinaPorDisciplina(String proprinaPorDisciplina) {
		this.proprinaPorDisciplina = proprinaPorDisciplina;
	}
	public boolean isMesAProcessar() {
		return mesAProcessar;
	}
	public void setMesAProcessar(boolean mesAProcessar) {
		this.mesAProcessar = mesAProcessar;
	}
	public String getPropinaAnoCompleto() {
		return propinaAnoCompleto;
	}
	public void setPropinaAnoCompleto(String propinaAnoCompleto) {
		this.propinaAnoCompleto = propinaAnoCompleto;
	}
	public String getProprinaPorDisciplina() {
		return proprinaPorDisciplina;
	}
}