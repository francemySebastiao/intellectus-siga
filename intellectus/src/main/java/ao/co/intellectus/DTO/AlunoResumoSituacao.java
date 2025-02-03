package ao.co.intellectus.DTO;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;

public class AlunoResumoSituacao {
	private Integer numero;
	private String nome;
	private String curso;
	private Integer codigoCurso;
	private String email;
	private String telefone;
	private String unidadeDeApoio;
	private boolean fimCurso;
	private boolean anulado;
	private boolean contencioso;
	private String grau;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] foto;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getUnidadeDeApoio() {
		return unidadeDeApoio;
	}

	public void setUnidadeDeApoio(String unidadeDeApoio) {
		this.unidadeDeApoio = unidadeDeApoio;
	}

	public boolean isFimCurso() {
		return fimCurso;
	}

	public void setFimCurso(boolean fimCurso) {
		this.fimCurso = fimCurso;
	}

	public boolean isAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

	public boolean isContencioso() {
		return contencioso;
	}

	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getGrau() {
		return grau;
	}

	public void setGrau(String grau) {
		this.grau = grau;
	}

	public Integer getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(Integer codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
}
