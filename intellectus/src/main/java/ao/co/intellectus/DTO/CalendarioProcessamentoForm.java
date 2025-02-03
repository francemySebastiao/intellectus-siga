package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CalendarioProcessamentoForm {
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date dataProcessamento;
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
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
	
}
