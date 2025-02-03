package ao.co.intellectus.model;

public enum EstadoAssiduidade {
	CINZA("Cinza"),VERDE("Verde"),AMARELO("Amarelo"),VERMELHO("Vermelho");
	
	private String descricao;
	
	EstadoAssiduidade(String descricao){
		this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
