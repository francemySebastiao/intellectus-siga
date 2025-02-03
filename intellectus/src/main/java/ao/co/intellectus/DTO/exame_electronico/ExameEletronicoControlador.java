package ao.co.intellectus.DTO.exame_electronico;

public class ExameEletronicoControlador {
	private Integer id;
    private String nome;
    private String curso;
    private String turno;
    private String telefone;
    private Integer classificacao;
    
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
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Integer getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(Integer classificacao) {
		this.classificacao = classificacao;
	}
}
