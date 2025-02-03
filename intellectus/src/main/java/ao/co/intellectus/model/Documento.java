package ao.co.intellectus.model;

public enum Documento {
	BILHETE_DE_IDENTIDADE("Bilhete de Identidade"),PASSAPORTE("Passaporte"),CARTAO_DE_RESIDENCIA("Cartão de Residência"),CEDULA_CONSULAR("Cédula Consular"),OUTROS("Outro");
	
	private String descricao;
	
	Documento(String descricao){
		this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
