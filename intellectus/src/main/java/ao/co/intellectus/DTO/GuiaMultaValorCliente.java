package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class GuiaMultaValorCliente {
	private Integer id;
	private boolean consolidado;
	@Temporal(TemporalType.DATE)
	private Date data1Prestcao;
	@Temporal(TemporalType.DATE)
	private Date dataProximaPrestacao;
	@Temporal(TemporalType.DATE)
	private Date dataUltimaPrestacao;
	private Integer nPrestacoes;
	private double totalMulta;
	private double totalSelecionado;
	private double valorAPagar;
	private double valorAmnistia;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isConsolidado() {
		return consolidado;
	}
	public void setConsolidado(boolean consolidado) {
		this.consolidado = consolidado;
	}
	public Date getData1Prestcao() {
		return data1Prestcao;
	}
	public void setData1Prestcao(Date data1Prestcao) {
		this.data1Prestcao = data1Prestcao;
	}
	public Date getDataProximaPrestacao() {
		return dataProximaPrestacao;
	}
	public void setDataProximaPrestacao(Date dataProximaPrestacao) {
		this.dataProximaPrestacao = dataProximaPrestacao;
	}
	public Date getDataUltimaPrestacao() {
		return dataUltimaPrestacao;
	}
	public void setDataUltimaPrestacao(Date dataUltimaPrestacao) {
		this.dataUltimaPrestacao = dataUltimaPrestacao;
	}
	public Integer getnPrestacoes() {
		return nPrestacoes;
	}
	public void setnPrestacoes(Integer nPrestacoes) {
		this.nPrestacoes = nPrestacoes;
	}
	public double getTotalMulta() {
		return totalMulta;
	}
	public void setTotalMulta(double totalMulta) {
		this.totalMulta = totalMulta;
	}
	public double getTotalSelecionado() {
		return totalSelecionado;
	}
	public void setTotalSelecionado(double totalSelecionado) {
		this.totalSelecionado = totalSelecionado;
	}
	public double getValorAPagar() {
		return valorAPagar;
	}
	public void setValorAPagar(double valorAPagar) {
		this.valorAPagar = valorAPagar;
	}
	public double getValorAmnistia() {
		return valorAmnistia;
	}
	public void setValorAmnistia(double valorAmnistia) {
		this.valorAmnistia = valorAmnistia;
	}
}
