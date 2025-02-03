package ao.co.intellectus.DTO;

import java.util.List;

public class AproveitametoCliente {
	private Integer numero;
	private String nome;
	private String curso;
	private String email;
	private String telefone;
	private String unidadeDeApoio;
	private boolean fimCurso;
	private boolean contencioso;
	private List<DisciplinaAproveitamentoCliente> disciplinas;

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

	public boolean isContencioso() {
		return contencioso;
	}

	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}

	public List<DisciplinaAproveitamentoCliente> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<DisciplinaAproveitamentoCliente> disciplinas) {
		this.disciplinas = disciplinas;
	}
}