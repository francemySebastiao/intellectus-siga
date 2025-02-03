package ao.co.intellectus.DTO.exame_electronico;

public class ExameRevisaoDTO {
	private String nome;
	private String numeroDocumento;
	private Integer classificacao;
	private String TipoExame;
	private String pergunta;
	private String resposta;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public Integer getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(Integer classificacao) {
		this.classificacao = classificacao;
	}
	public String getTipoExame() {
		return TipoExame;
	}
	public void setTipoExame(String tipoExame) {
		TipoExame = tipoExame;
	}
	public String getPergunta() {
		return pergunta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

}
