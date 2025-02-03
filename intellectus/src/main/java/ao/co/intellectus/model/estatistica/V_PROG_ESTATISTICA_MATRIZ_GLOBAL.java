package ao.co.intellectus.model.estatistica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_PROG_ESTATISTICA_MATRIZ_GLOBAL")
public class V_PROG_ESTATISTICA_MATRIZ_GLOBAL {
	@Id
	private Integer id;
	@Column(name="CURSO")
	private String curso;
	@Column(name="GRAU")
	private String grau;
	@Column(name="ano_lectivo")
	private Integer anoLectivo;
	@Column(name="ano_curricular")
	private Integer anoCurricular;
	private Integer manha;
	private Integer tarde;
	private Integer noite;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getGrau() {
		return grau;
	}
	public void setGrau(String grau) {
		this.grau = grau;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}
	public Integer getManha() {
		return manha;
	}
	public void setManha(Integer manha) {
		this.manha = manha;
	}
	public Integer getTarde() {
		return tarde;
	}
	public void setTarde(Integer tarde) {
		this.tarde = tarde;
	}
	public Integer getNoite() {
		return noite;
	}
	public void setNoite(Integer noite) {
		this.noite = noite;
	}
}
