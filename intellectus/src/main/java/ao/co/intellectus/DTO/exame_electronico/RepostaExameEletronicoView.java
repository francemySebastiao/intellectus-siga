package ao.co.intellectus.DTO.exame_electronico;

public class RepostaExameEletronicoView {

	private Integer id;
	private String descricao;
	private Boolean correta;
	private Integer pergunta;

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

	public Boolean getCorreta() {
		return correta;
	}

	public void setCorreta(Boolean correta) {
		this.correta = correta;
	}

	public Integer getPergunta() {
		return pergunta;
	}

	public void setPergunta(Integer pergunta) {
		this.pergunta = pergunta;
	}

}
