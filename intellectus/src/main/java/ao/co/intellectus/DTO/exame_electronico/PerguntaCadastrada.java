package ao.co.intellectus.DTO.exame_electronico;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.exame_eletronico.TipoExame;

public class PerguntaCadastrada {
	private Integer id;
	private String descricao;
	private Integer classificacao;
	@Enumerated(EnumType.STRING)
	private TipoExame tipoExame;
	private String curso;
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
	public Integer getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(Integer classificacao) {
		this.classificacao = classificacao;
	}
	public TipoExame getTipoExame() {
		return tipoExame;
	}
	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	
	
}
