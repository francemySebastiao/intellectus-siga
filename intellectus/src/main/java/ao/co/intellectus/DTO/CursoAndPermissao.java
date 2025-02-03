package ao.co.intellectus.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Permissao;

public class CursoAndPermissao {
	private Integer id;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private Permissao permissao;
	private Integer codigoPermissao;

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

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public Integer getCodigoPermissao() {
		return codigoPermissao;
	}

	public void setCodigoPermissao(Integer codigoPermissao) {
		this.codigoPermissao = codigoPermissao;
	}
}
