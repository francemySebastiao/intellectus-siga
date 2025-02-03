package ao.co.intellectus.model;

public enum Semana {
	SEGUNDA("Segunda-Feira"), TERCA("Terça-Feira"),QUARTA("quarta-feira"),QUINTA("quinta-feira"),SEXTA("sexta-feira"),SABADO("sábado"),NA("NA"),DOMINGO("Domingo");

	private String descricao;

	private Semana(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
