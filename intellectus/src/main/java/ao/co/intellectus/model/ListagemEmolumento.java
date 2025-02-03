package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_LISTAGEM_EMOLUMENTO")
public class ListagemEmolumento {

	@Id
	private String numero_guia;
	private String numero_bordereux;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_deposito")
	private Date dataDeposito;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_liquidacao")
	private Date dataLiquidacao;
	private String emolumento;
	private float valor;
	private Integer anoLectivo;
	@Column(name = "MES_LIQUIDACAO_INTEIRO")
	private Integer mesLiquidacaoInteiro;

	public String getNumero_guia() {
		return numero_guia;
	}

	public void setNumero_guia(String numero_guia) {
		this.numero_guia = numero_guia;
	}

	public String getNumero_bordereux() {
		return numero_bordereux;
	}

	public void setNumero_bordereux(String numero_bordereux) {
		this.numero_bordereux = numero_bordereux;
	}

	public Date getDataDeposito() {
		return dataDeposito;
	}

	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}

	public Date getDataLiquidacao() {
		return dataLiquidacao;
	}

	public void setDataLiquidacao(Date dataLiquidacao) {
		this.dataLiquidacao = dataLiquidacao;
	}

	public String getEmolumento() {
		return emolumento;
	}

	public void setEmolumento(String emolumento) {
		this.emolumento = emolumento;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Integer getMesLiquidacaoInteiro() {
		return mesLiquidacaoInteiro;
	}

	public void setMesLiquidacaoInteiro(Integer mesLiquidacaoInteiro) {
		this.mesLiquidacaoInteiro = mesLiquidacaoInteiro;
	}

}
