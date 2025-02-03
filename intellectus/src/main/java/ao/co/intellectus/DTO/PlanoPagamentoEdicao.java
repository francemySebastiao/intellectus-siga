package ao.co.intellectus.DTO;

public class PlanoPagamentoEdicao {
	private Integer id;
	private String descricao;
	private boolean inscrito;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isInscrito() {
		return inscrito;
	}

	public void setInscrito(boolean inscrito) {
		this.inscrito = inscrito;
	}

}
