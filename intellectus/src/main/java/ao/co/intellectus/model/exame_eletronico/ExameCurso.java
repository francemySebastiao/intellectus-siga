package ao.co.intellectus.model.exame_eletronico;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ao.co.intellectus.model.Curso;

@Entity
@Table(name="t_exame_curso")
public class ExameCurso {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="codigo_curso")
	private Curso curso;
	@Enumerated(EnumType.STRING)
	private TipoExame tipoExame;
	public Integer getId() { 
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public TipoExame getTipoExame() {
		return tipoExame;
	}
	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}

}
