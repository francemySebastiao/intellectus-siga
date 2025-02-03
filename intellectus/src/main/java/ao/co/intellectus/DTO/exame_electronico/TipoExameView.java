package ao.co.intellectus.DTO.exame_electronico;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.exame_eletronico.TipoExame;

public class TipoExameView {

	@Enumerated(EnumType.STRING)
	private TipoExame tipoExame;
	private String descricao;

	public TipoExame getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
