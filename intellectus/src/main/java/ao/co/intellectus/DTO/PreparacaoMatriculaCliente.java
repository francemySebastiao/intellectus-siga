package ao.co.intellectus.DTO;

import java.util.List;

import ao.co.intellectus.model.TipoInscricao;


public class PreparacaoMatriculaCliente {
    private Integer numeroDeAluno;
    private String nome;
    private String curso;
    private Integer cursoCodigo;
    private Integer anoCurricular;
    private boolean contencioso;
    private boolean fimCurso;
    private boolean inativo;
    private Integer anoLectivo;
    private List<TipoInscricao> tipoInscricoes;
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
	public Integer getCursoCodigo() {
		return cursoCodigo;
	}
	public void setCursoCodigo(Integer cursoCodigo) {
		this.cursoCodigo = cursoCodigo;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}
	public List<TipoInscricao> getTipoInscricoes() {
		return tipoInscricoes;
	}
	public void setTipoInscricoes(List<TipoInscricao> tipoInscricoes) {
		this.tipoInscricoes = tipoInscricoes;
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
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
}
