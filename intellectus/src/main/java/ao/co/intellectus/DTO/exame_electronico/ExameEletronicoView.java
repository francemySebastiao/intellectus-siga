package ao.co.intellectus.DTO.exame_electronico;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.exame_eletronico.TipoExame;

public class ExameEletronicoView {

	private Integer id;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private TipoExame tipoExame;
	private Integer classificao;
	private Integer curso;
	private Integer anoLectivo;

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

	public TipoExame getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}

	public Integer getClassificao() {
		return classificao;
	}

	public void setClassificao(Integer classificao) {
		this.classificao = classificao;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

}
