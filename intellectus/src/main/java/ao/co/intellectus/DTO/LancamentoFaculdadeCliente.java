package ao.co.intellectus.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Permissao;

public class LancamentoFaculdadeCliente {
	private Integer id;
	private Integer faculdade;
	private Integer prova;
	@Enumerated(EnumType.STRING)
	private Permissao permissao;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFaculdade() {
		return faculdade;
	}

	public void setFaculdade(Integer faculdade) {
		this.faculdade = faculdade;
	}

	public Integer getProva() {
		return prova;
	}

	public void setProva(Integer prova) {
		this.prova = prova;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
}
