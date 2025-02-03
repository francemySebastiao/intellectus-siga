package ao.co.intellectus.DTO;

import java.util.List;

import ao.co.intellectus.model.EmolumentoPedido;

public class PedidoPorGuiaCliente {
	private Integer numeroDeAluno;
	private String nome;
	private String curso;
	private String numeroGuia;
	private boolean contencioso;
	private boolean inativo;
	private boolean fimCurso;
	private EmolumentoPedido emolumentoPedido;
	private List<AnosInscricoes> anoInscricoes;
	
	
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
	public String getNumeroGuia() {
		return numeroGuia;
	}
	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}
	public boolean isContencioso() {
		return contencioso;
	}
	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}
	public boolean isInativo() {
		return inativo;
	}
	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}
	public boolean isFimCurso() {
		return fimCurso;
	}
	public void setFimCurso(boolean fimCurso) {
		this.fimCurso = fimCurso;
	}
	public EmolumentoPedido getEmolumentoPedido() {
		return emolumentoPedido;
	}
	public void setEmolumentoPedido(EmolumentoPedido emolumentoPedido) {
		this.emolumentoPedido = emolumentoPedido;
	}
	public List<AnosInscricoes> getAnoInscricoes() {
		return anoInscricoes;
	}
	public void setAnoInscricoes(List<AnosInscricoes> anoInscricoes) {
		this.anoInscricoes = anoInscricoes;
	}
}