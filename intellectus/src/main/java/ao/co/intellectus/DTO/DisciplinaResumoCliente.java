package ao.co.intellectus.DTO;

public class DisciplinaResumoCliente {
	private Integer id;
	private String descricao;
	private String tipo;
	private boolean aprovacaoDisciplina;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isAprovacaoDisciplina() {
		return aprovacaoDisciplina;
	}
	public void setAprovacaoDisciplina(boolean aprovacaoDisciplina) {
		this.aprovacaoDisciplina = aprovacaoDisciplina;
	}
	
	
	
}
