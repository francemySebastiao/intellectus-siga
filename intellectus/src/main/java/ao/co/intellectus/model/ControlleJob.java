package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_controlle_job")
public class ControlleJob {
	@Id
	@GeneratedValue
	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimaExecucao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataProximaExecucao;
	@Column(length=50)
	private String procedimento;
	@Column(length=20)
	private String estado;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) { 
		this.id = id;
	}
	public Date getDataUltimaExecucao() {
		return dataUltimaExecucao;
	}
	public void setDataUltimaExecucao(Date dataUltimaExecucao) {
		this.dataUltimaExecucao = dataUltimaExecucao;
	}
	public Date getDataProximaExecucao() {
		return dataProximaExecucao;
	}
	public void setDataProximaExecucao(Date dataProximaExecucao) {
		this.dataProximaExecucao = dataProximaExecucao;
	}
	public String getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
