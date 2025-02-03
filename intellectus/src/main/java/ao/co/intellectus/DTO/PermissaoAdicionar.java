package ao.co.intellectus.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Permissao;

public class PermissaoAdicionar {
	private Integer idUsuario;
	private Integer idCurso;
	@Enumerated(EnumType.STRING)
	private Permissao tipoPermissao;
	private Integer codigoPermissao;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public Permissao getTipoPermissao() {
		return tipoPermissao;
	}

	public void setTipoPermissao(Permissao tipoPermissao) {
		this.tipoPermissao = tipoPermissao;
	}

	public Integer getCodigoPermissao() {
		return codigoPermissao;
	}

	public void setCodigoPermissao(Integer codigoPermissao) {
		this.codigoPermissao = codigoPermissao;
	}
}
