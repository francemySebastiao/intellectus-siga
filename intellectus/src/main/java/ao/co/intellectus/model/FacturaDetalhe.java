package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_factura_detalhe")
public class FacturaDetalhe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private String nomeAluno;
	private String descricao;
	private Integer quantidade;
	@Column(name = "preco_unitario")
	private double precoUnitario;
	private double desconto;
	@Column(name = "codigo_iva")
	private String codigoIva;
	@Column(name = "percentagem_iva")
	private String percentagemIva;
	@Column(name = "valor_imposto")
	private double valorImposto;
	@Column(name = "valor_total")
	private double valorTotal;
	@Column(name = "valor_sem_desconto")
	private double valorSemDesconto;
	@ManyToOne
	@JoinColumn(name = "id_factura")
	private Factura idFactura;
	private Integer numeroAluno;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public double getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public String getCodigoIva() {
		return codigoIva;
	}
	public void setCodigoIva(String codigoIva) {
		this.codigoIva = codigoIva;
	}
	public String getPercentagemIva() {
		return percentagemIva;
	}
	public void setPercentagemIva(String percentagemIva) {
		this.percentagemIva = percentagemIva;
	}
	public double getValorImposto() {
		return valorImposto;
	}
	public void setValorImposto(double valorImposto) {
		this.valorImposto = valorImposto;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Factura getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Factura idFactura) {
		this.idFactura = idFactura;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public Integer getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
	}
	public double getValorSemDesconto() {
		return valorSemDesconto;
	}
	public void setValorSemDesconto(double valorSemDesconto) {
		this.valorSemDesconto = valorSemDesconto;
	}
	
	
	
}
