package ao.co.intellectus.DTO;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;

public class AlunoCademico {
	private String numeroDeAluno;
	private String nome;
	private String curso;
	private Integer cursoID;
	private String grau;
	private boolean fimCurso;
	private List<Integer> inscricoes;
	private List<DisciplinaMatricula> disciplinas;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] foto;

	public String getNumeroDeAluno() {
		return numeroDeAluno;
	}

	public void setNumeroDeAluno(String numeroDeAluno) {
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

	public Integer getCursoID() {
		return cursoID;
	}

	public void setCursoID(Integer cursoID) {
		this.cursoID = cursoID;
	}

	public String getGrau() {
		return grau;
	}

	public void setGrau(String grau) {
		this.grau = grau;
	}

	public boolean isFimCurso() {
		return fimCurso;
	}

	public void setFimCurso(boolean fimCurso) {
		this.fimCurso = fimCurso;
	}

	public List<Integer> getInscricoes() {
		return inscricoes;
	}

	public void setInscricoes(List<Integer> inscricoes) {
		this.inscricoes = inscricoes;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public List<DisciplinaMatricula> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<DisciplinaMatricula> disciplinas) {
		this.disciplinas = disciplinas;
	}
}
