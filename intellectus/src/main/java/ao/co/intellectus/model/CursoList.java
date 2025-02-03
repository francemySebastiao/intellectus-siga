package ao.co.intellectus.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public class CursoList {
	private Integer id;
	private String descricao;
	private String plano;
	@Enumerated(EnumType.STRING)
	private Grau grau;

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

	public Grau getGrau() {
		return grau;
	}

	public void setGrau(Grau grau) {
		this.grau = grau;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}
	
}
