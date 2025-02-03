package ao.co.intellectus.model.request;

public class ComunaRequest {
	private Integer id;
	private String descricao;
	private Integer codigoComuna;
	private Integer municipio;
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
	public Integer getCodigoComuna() {
		return codigoComuna;
	}
	public void setCodigoComuna(Integer codigoComuna) {
		this.codigoComuna = codigoComuna;
	}
	public Integer getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}
	

}
