package ao.co.intellectus.model.reponse;

public enum ResponseCode {
	SUCESSO(200), ERRO_INTERNO(500),NENHUM_RESGISTRO(404),REGRA_NEGOCIO(600), USUARIO_VALIDO(2000);

	private int descricao;

	ResponseCode(int descricao) {
		this.descricao = descricao;
	}

	public int getDescricao() {
		return descricao;
	}

	public void setDescricao(int descricao) {
		this.descricao = descricao;
	}
}
