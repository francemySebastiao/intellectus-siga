package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_curso_ensino_medio")
public class CursoEnsinoMedio {
	@Id
	@GeneratedValue
	private Integer id;
	private String descricao;
	
	public CursoEnsinoMedio() {
	}
	
	public CursoEnsinoMedio(Integer id) {
		this.id = id;
	}


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
}
