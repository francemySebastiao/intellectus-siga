package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_bordero")
public class Bordero {
	@Id
	@GeneratedValue
    private Integer id;
	private String numero;
	@Temporal(TemporalType.DATE)
	private Date dataDeposito;
	private double valor;
	@Temporal(TemporalType.DATE)
	private Date dataRegistro;
	//JUNÇÕES COM OUTRAS TABELAS.
	@ManyToOne
	@JoinColumn(name="codigo_moeda")
	private Moeda moeda;
	@ManyToOne
	@JoinColumn(name="codigo_aluno")	
	private Aluno aluno;
	@OneToOne
	@JoinColumn(name="codigo_banco")	
	private Banco banco;
	@OneToOne
	@JoinColumn(name="codigo_guia")
	private Guia guia;
	
	
	@OneToOne
	@JoinColumn(name="codigo_empresa")
	private EmpresaConvenio empresa;
	
	@OneToOne
	@JoinColumn(name="codigo_factura")
	private Factura factura;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Date getDataDeposito() {
		return dataDeposito;
	}
	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public Moeda getMoeda() {
		return moeda;
	}
	public void setMoeda(Moeda moeda) {
		this.moeda = moeda;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Banco getBanco() {
		return banco;
	}
	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	public Guia getGuia() {
		return guia;
	}
	public void setGuia(Guia guia) {
		this.guia = guia;
	}
	public EmpresaConvenio getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaConvenio empresa) {
		this.empresa = empresa;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	
}
