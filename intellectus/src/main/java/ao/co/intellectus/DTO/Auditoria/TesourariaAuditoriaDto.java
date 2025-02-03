package ao.co.intellectus.DTO.Auditoria;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ao.co.intellectus.model.Guia;
@JsonInclude(Include.NON_NULL)
public class TesourariaAuditoriaDto {
	
	private String numeroAluno;
	private String banco;
	private Date dataEmissao;
	private Date dataLiquidacao;
	private Date dataRegistro;
	private String numeroBordero;
	private Double valor;
	private String numeroGuia;
	public String getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(String numeroAluno) {
		this.numeroAluno = numeroAluno;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Date getDataLiquidacao() {
		return dataLiquidacao;
	}
	public void setDataLiquidacao(Date dataLiquidacao) {
		this.dataLiquidacao = dataLiquidacao;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public String getNumeroBordero() {
		return numeroBordero;
	}
	public void setNumeroBordero(String numeroBordero) {
		this.numeroBordero = numeroBordero;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getNumeroGuia() {
		return numeroGuia;
	}
	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}
	
		
	

}
