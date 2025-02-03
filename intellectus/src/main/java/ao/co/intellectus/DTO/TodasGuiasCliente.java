package ao.co.intellectus.DTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class TodasGuiasCliente {
	private Integer id;
	private Integer anoLectivo;
	private String numeroGuia;
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	private double valor;
	private String moeda;
	private boolean liquidada;
	private List<EmolumentoCliente> emolumento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public String getNumeroGuia() {
		return numeroGuia;
	}

	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public boolean isLiquidada() {
		return liquidada;
	}

	public void setLiquidada(boolean liquidada) {
		this.liquidada = liquidada;
	}

	public List<EmolumentoCliente> getEmolumento() {
		return emolumento;
	}

	public void setEmolumento(List<EmolumentoCliente> emolumento) {
		this.emolumento = emolumento;
	}

}
