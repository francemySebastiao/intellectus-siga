package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_CERTIFICADO_INTERMEDIO_MESTRADO_LT")
public class CertificadoIntermedioMestrado {

	@Id
	private Integer id;
	private Integer codigoDisciplina;
	private Integer numero;
	private String disciplina;
	private Integer notaFinal;
	private boolean validacao;
	private Integer anoLectivo;
	private boolean equivalencia;
	private Integer anoCurricular;
	private String podeEquivalencia;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodigoDisciplina() {
		return codigoDisciplina;
	}
	public void setCodigoDisciplina(Integer codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public Integer getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(Integer notaFinal) {
		this.notaFinal = notaFinal;
	}
	public boolean isValidacao() {
		return validacao;
	}
	public void setValidacao(boolean validacao) {
		this.validacao = validacao;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public boolean isEquivalencia() {
		return equivalencia;
	}
	public void setEquivalencia(boolean equivalencia) {
		this.equivalencia = equivalencia;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}
	public String getPodeEquivalencia() {
		return podeEquivalencia;
	}
	public void setPodeEquivalencia(String podeEquivalencia) {
		this.podeEquivalencia = podeEquivalencia;
	}
}
