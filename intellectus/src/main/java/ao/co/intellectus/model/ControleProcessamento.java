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
@Table(name = "t_processamento_mensal")
public class ControleProcessamento {
	@Id
	@GeneratedValue
	private Integer id;
	private String mes;
	private boolean processado;
	@ManyToOne
	@JoinColumn(name="codigo_emolumento")
	private Emolumento emolumento;
	@Temporal(TemporalType.DATE)
	private Date dataProcessamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public boolean isProcessado() {
		return processado;
	}

	public void setProcessado(boolean processado) {
		this.processado = processado;
	}

	public Emolumento getEmolumento() {
		return emolumento;
	}

	public void setEmolumento(Emolumento emolumento) {
		this.emolumento = emolumento;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
}
