package ao.co.intellectus.DTO;

public class AlunoResumoCliente {
	private Integer id;
	private String nome;
	private String curso;
	private Integer valorAdd;
	private Integer valor;
	private Integer valorDesconto;
	private Integer quantidade;
	private String descricao;
	
	private boolean status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Integer getValorAdd() {
		return valorAdd;
	}

	public void setValorAdd(Integer valorAdd) {
		this.valorAdd = valorAdd;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Integer getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Integer valorDesconto) {
		this.valorDesconto = valorDesconto;
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
	
}
