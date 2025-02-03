package ao.co.intellectus.DTO;

public class BolseiroMatriculaEdicao {
	private Integer id;
    private String empresaDescricao;
    private boolean inscrito;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmpresaDescricao() {
		return empresaDescricao;
	}
	public void setEmpresaDescricao(String empresaDescricao) {
		this.empresaDescricao = empresaDescricao;
	}
	public boolean isInscrito() {
		return inscrito;
	}
	public void setInscrito(boolean inscrito) {
		this.inscrito = inscrito;
	}
}
