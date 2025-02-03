package ao.co.intellectus.DTO;

import java.util.Date;
import java.util.List;

public class ConvenioCliente {
	private Integer empresa;
	private Integer anoLectivo;
	private Integer banco;
	private String numeroBorderoux;
	private Double valor;
	private Date dataDeposito;
	private Date dataRegistro;
    private List<AlunoResumoCliente> bolseiros;
                                    
	public Integer getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Integer getBanco() {
		return banco;
	}

	public void setBanco(Integer banco) {
		this.banco = banco;
	}

	public String getNumeroBorderoux() {
		return numeroBorderoux;
	}

	public void setNumeroBorderoux(String numeroBorderoux) {
		this.numeroBorderoux = numeroBorderoux;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataDeposito() {
		return dataDeposito;
	} 

	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public List<AlunoResumoCliente> getBolseiros() {
		return bolseiros;
	}

	public void setBolseiros(List<AlunoResumoCliente> bolseiros) {
		this.bolseiros = bolseiros;
	}
}
