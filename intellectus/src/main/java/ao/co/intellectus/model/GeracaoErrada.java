package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_GERACAO_ERRADA")
public class GeracaoErrada {
	@Id
	@Column(name = "NUMERO_ALUNO")
	private Integer numeroAluno;
	@Column(name = "NUMERO_GUIA")
	private String numeroGuia;
	@Column(name = "LIQUIDADA")
	private Integer liquidada;

	public Integer getNumeroAluno() {
		return numeroAluno;
	}

	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
	}

	public String getNumeroGuia() {
		return numeroGuia;
	}

	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}

	public Integer getLiquidada() {
		return liquidada;
	}

	public void setLiquidada(Integer liquidada) {
		this.liquidada = liquidada;
	}
}
