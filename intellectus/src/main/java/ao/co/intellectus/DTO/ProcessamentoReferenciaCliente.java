package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ProcessamentoReferenciaCliente {
	@Column(length=15,unique=true)
	private String mes;
	@Temporal(TemporalType.DATE)
	private Date dataProcessamento;
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	private Integer totalGuias;
	private Integer anoLectivo;
	private boolean processado;
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
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
	public Integer getTotalGuias() {
		return totalGuias;
	}
	public void setTotalGuias(Integer totalGuias) {
		this.totalGuias = totalGuias;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public boolean isProcessado() {
		return processado;
	}
	public void setProcessado(boolean processado) {
		this.processado = processado;
	}
}
