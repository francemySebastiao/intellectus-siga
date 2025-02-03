package ao.co.intellectus.DTO;

import java.util.Date;

public class ValidarEquivalenciaCliente {
	private Integer id;
	private boolean primeiraValidacao;
	private Date dataPrimeiraValidacao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isPrimeiraValidacao() {
		return primeiraValidacao;
	}
	public void setPrimeiraValidacao(boolean primeiraValidacao) {
		this.primeiraValidacao = primeiraValidacao;
	}
	public Date getDataPrimeiraValidacao() {
		return dataPrimeiraValidacao;
	}
	public void setDataPrimeiraValidacao(Date dataPrimeiraValidacao) {
		this.dataPrimeiraValidacao = dataPrimeiraValidacao;
	}
}
