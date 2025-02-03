package ao.co.intellectus.model.exame_eletronico;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_CANDIDATO_EXAME_MAP")
public class CandidatoExameMap {
	@Id
	@Column(name="codigo_candidato")
	private Integer codigo;
	private String nome;
	private Integer notafinal;
	private String curso;
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getNotafinal() {
		return notafinal;
	}
	public void setNotafinal(Integer notafinal) {
		this.notafinal = notafinal;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
}
