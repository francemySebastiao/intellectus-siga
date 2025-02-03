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
@Table(name="t_guia_multa_valor")
public class GuiaMultaValor {
	@Id
    @GeneratedValue
	private Integer id;
	private double totalMulta;
	private double totalSelecionado;
	private Integer nPrestacoes;
	@Temporal(TemporalType.DATE)
	private Date data1Prestacao;
	@Temporal(TemporalType.DATE)
	private Date dataProximaPrestacao;
	@Temporal(TemporalType.DATE) 
	private Date dataUltimaPrestacao;
	private double valorAmnistia;
	@Column(name="valor_a_pagar")
	private double valorAPagar;
	private boolean consolidado;
	private Integer numeroDeAluno;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getnPrestacoes() {
		return nPrestacoes;
	}
	public void setnPrestacoes(Integer nPrestacoes) {
		this.nPrestacoes = nPrestacoes;
	}
	
	public Date getData1Prestacao() {
		return data1Prestacao;
	}
	public void setData1Prestacao(Date data1Prestacao) {
		this.data1Prestacao = data1Prestacao;
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
	public double getValorAmnistia() {
		return valorAmnistia;
	}
	public void setValorAmnistia(double valorAmnistia) {
		this.valorAmnistia = valorAmnistia;
	}
	public boolean isConsolidado() {
		return consolidado;
	}
	public void setConsolidado(boolean consolidado) {
		this.consolidado = consolidado;
	}
	public double getValorAPagar() {
		return valorAPagar;
	}
	public void setValorAPagar(double valorAPagar) {
		this.valorAPagar = valorAPagar;
	}
	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
}
