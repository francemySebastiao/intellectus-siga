package ao.co.intellectus.DTO;

import java.util.List;

public class DespachoDisciplina {
	private Integer idAluno;
	private Integer numeroDeAluno;
	private String nome;
	private String curso;
	private String grau;
	private boolean contencioso;
	private boolean fimCurso;
	private boolean inativo;
	private boolean anulado;
	private List<DisciplinaMatricula> disciplinas;

	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}

	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public boolean isContencioso() {
		return contencioso;
	}

	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}

	public boolean isFimCurso() {
		return fimCurso;
	}

	public void setFimCurso(boolean fimCurso) {
		this.fimCurso = fimCurso;
	}

	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}
	
	

	public boolean isAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

	public List<DisciplinaMatricula> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<DisciplinaMatricula> disciplinas) {
		this.disciplinas = disciplinas;
	}
}
