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
@Table(name="V_PROG_HISTORICO_CTR_BOLSEIROS")
public class ProgHistoricoCrtBolseiro {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	@Column(name="EMPRESA")
	private String empresa;
	@Column(name="ANO_LECTIVO")
	private Integer anoLectivo;
	@Column(name="ID_ANO_LECTIVO")
	private Integer anoLectivoId;
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_REGISTRO")
	private Date dataRegistro;
	@Column(name="DATA_DEPOSITO")
	@Temporal(TemporalType.DATE)
	private Date dataDeposito;
	@Column(name="BORDERO")
	private String bordero;
	@Column(name="BANCO")
	private String banco;
	@Column(name="MOEDA")
	private String moeda;
	@Column(name="VALOR_DEPOSITO")
	private float valorDepositado;
	@Column(name="TOTAL_ALUNOS")
	private Integer totalAlunos;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Integer getAnoLectivoId() {
		return anoLectivoId;
	}
	public void setAnoLectivoId(Integer anoLectivoId) {
		this.anoLectivoId = anoLectivoId;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public Date getDataDeposito() {
		return dataDeposito;
	}
	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}
	public String getBordero() {
		return bordero;
	}
	public void setBordero(String bordero) {
		this.bordero = bordero;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getMoeda() {
		return moeda;
	}
	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	
	public float getValorDepositado() {
		return valorDepositado;
	}
	public void setValorDepositado(float valorDepositado) {
		this.valorDepositado = valorDepositado;
	}
	public Integer getTotalAlunos() {
		return totalAlunos;
	}
	public void setTotalAlunos(Integer totalAlunos) {
		this.totalAlunos = totalAlunos;
	}

	
}
