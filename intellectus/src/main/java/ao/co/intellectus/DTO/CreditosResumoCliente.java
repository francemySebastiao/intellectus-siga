package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CreditosResumoCliente {
	private Integer id;
	private String borderoInterno;
	private String borderoExterno;
	private float valorDeposito;
	@Temporal(TemporalType.DATE)
	private Date dataRegisto;
	@Temporal(TemporalType.DATE)
	private Date dataDepositoExterno;
	private String banco;
	private String moeda;
	private Integer anoLectivo;
	private String anoLectivoDescricao;
	private boolean anulado;
	private boolean podeAnular;
	private String motivoAnulacao;
	private String empresa;
	private Integer totalAlunos;
	private String numeroFR;
	

	public String getAnoLectivoDescricao() {
		return anoLectivoDescricao;
	}

	public void setAnoLectivoDescricao(String anoLectivoDescricao) {
		this.anoLectivoDescricao = anoLectivoDescricao;
	}

	public String getBorderoInterno() {
		return borderoInterno;
	}

	public void setBorderoInterno(String borderoInterno) {
		this.borderoInterno = borderoInterno;
	}

	public String getBorderoExterno() {
		return borderoExterno;
	}

	public void setBorderoExterno(String borderoExterno) {
		this.borderoExterno = borderoExterno;
	}

	public float getValorDeposito() {
		return valorDeposito;
	}

	public void setValorDeposito(float valorDeposito) {
		this.valorDeposito = valorDeposito;
	}

	public Date getDataRegisto() {
		return dataRegisto;
	}

	public void setDataRegisto(Date dataRegisto) {
		this.dataRegisto = dataRegisto;
	}

	public Date getDataDepositoExterno() {
		return dataDepositoExterno;
	}

	public void setDataDepositoExterno(Date dataDepositoExterno) {
		this.dataDepositoExterno = dataDepositoExterno;
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

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public boolean isAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

	public boolean isPodeAnular() {
		return podeAnular;
	}

	public void setPodeAnular(boolean podeAnular) {
		this.podeAnular = podeAnular;
	}

	public String getMotivoAnulacao() {
		return motivoAnulacao;
	}

	public void setMotivoAnulacao(String motivoAnulacao) {
		this.motivoAnulacao = motivoAnulacao;
	}

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

	public Integer getTotalAlunos() {
		return totalAlunos;
	}

	public void setTotalAlunos(Integer totalAlunos) {
		this.totalAlunos = totalAlunos;
	}

	public String getNumeroFR() {
		return numeroFR;
	}

	public void setNumeroFR(String numeroFR) {
		this.numeroFR = numeroFR;
	}
}
