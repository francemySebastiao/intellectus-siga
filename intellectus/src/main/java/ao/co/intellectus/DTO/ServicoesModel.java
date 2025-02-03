package ao.co.intellectus.DTO;

import java.util.List;

import ao.co.intellectus.model.ServicosAuditoria;

public class ServicoesModel {
	private Integer numeroDeAluno;
	private String nome;
	private String curso;
	private String grau;
	private boolean contencioso;
	private boolean fimCurso;
	private boolean inativo;
	private List<ServicosAuditoria> servicosAuditoria;

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

	public List<ServicosAuditoria> getServicosAuditoria() {
		return servicosAuditoria;
	}

	public void setServicosAuditoria(List<ServicosAuditoria> servicosAuditoria) {
		this.servicosAuditoria = servicosAuditoria;
	}
}
