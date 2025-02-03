package ao.co.intellectus.DTO.proxpay;

import java.math.BigDecimal;
import java.util.Date;

public class ReferenciaRetornoDTO {
	private String codigo;
	private String nome;
	private String numeroAluno;
	private String telefone;
	private String email;
	private String descricao;
	private String guia;
	private String estadoReferencia;
	private String referencia;
	private Date dataVencimento;
	private Date dataCriacao;
	private Date dataEstado;
	private String unidade;
	private BigDecimal valor;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(String numeroAluno) {
		this.numeroAluno = numeroAluno;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getGuia() {
		return guia;
	}
	public void setGuia(String guia) {
		this.guia = guia;
	}
	public String getEstadoReferencia() {
		return estadoReferencia;
	}
	public void setEstadoReferencia(String estadoReferencia) {
		this.estadoReferencia = estadoReferencia;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataEstado() {
		return dataEstado;
	}
	public void setDataEstado(Date dataEstado) {
		this.dataEstado = dataEstado;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
