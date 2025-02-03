package ao.co.intellectus.DTO;

public class FacturaDetalheAlterada {

	private Long idFactura;
	
	private Double valorTotal;
	private Integer quantidade;
	private String nomeAluno;
	private Integer numeroAluno;
	private String descricao;
	private String codigoIva;
	private String codigo;
	private String percentagemIva;
	private Double precoUnitario;
	private Double desconto;
	private Double valorImposto;
	

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigoIva() {
		return codigoIva;
	}

	public void setCodigoIva(String codigoIva) {
		this.codigoIva = codigoIva;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPercentagemIva() {
		return percentagemIva;
	}

	public void setPercentagemIva(String percentagemIva) {
		this.percentagemIva = percentagemIva;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getValorImposto() {
		return valorImposto;
	}

	public void setValorImposto(Double valorImposto) {
		this.valorImposto = valorImposto;
	}

	public Long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Long idFactura) {
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

	
}
