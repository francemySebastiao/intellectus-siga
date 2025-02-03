package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_processamento_referencias")
public class ProcessamentoReferencia {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(length=15,unique=true)
	private String mes;
	@Temporal(TemporalType.DATE)
	private Date dataProcessamento;
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	private Integer totalGuias;
	@ManyToOne
	@JoinColumn(name="codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	private boolean processado;
	
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
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public boolean isProcessado() {
		return processado;
	}
	public void setProcessado(boolean processado) {
		this.processado = processado;
	}
}
