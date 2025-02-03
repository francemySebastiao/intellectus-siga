package ao.co.intellectus.DTO;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;

public class HistoricoEAluno {
	private Integer id;
	private String nome;
	private String curso;
	private boolean inativo;
	private boolean fimCurso;
	private boolean contencioso;
	private boolean inscrito;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] foto;
	
	private List<HistoricoAlunoCliente> historico;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<HistoricoAlunoCliente> getHistorico() {
		return historico;
	}

	public void setHistorico(List<HistoricoAlunoCliente> historico) {
		this.historico = historico;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
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

	public boolean isContencioso() {
		return contencioso;
	}

	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}

	public boolean isInscrito() {
		return inscrito;
	}

	public void setInscrito(boolean inscrito) {
		this.inscrito = inscrito;
	}
}
