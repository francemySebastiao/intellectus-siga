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
@Table(name="t_bordero_empresa")
public class BorderoEmpresa {

	@Id
	@GeneratedValue
    private Integer id;
	private String numero;
	private double valor;
	@Temporal(TemporalType.DATE)
	private Date dataRegistro;
	//JUNÇÕES COM OUTRAS TABELAS.
	@ManyToOne
	@JoinColumn(name="codigo_empresa")	
	private EmpresaConvenio empresConvenio;
	@OneToOne
	@JoinColumn(name="codigo_banco")	
	private Banco banco;
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
	public EmpresaConvenio getEmpresConvenio() {
		return empresConvenio;
	}
	public void setEmpresConvenio(EmpresaConvenio empresConvenio) {
		this.empresConvenio = empresConvenio;
	}
	public Banco getBanco() {
		return banco;
	}
	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	
}
