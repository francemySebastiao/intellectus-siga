package ao.co.intellectus.DTO;

public class AnosInscricoes {
    private Integer anoLectivo;
    private String anoLectivoDescricao;
    private Integer codigoAnoLectivo;
    private boolean bolseiro;
    private String empresaConvenio;
    
	public String getAnoLectivoDescricao() {
		return anoLectivoDescricao;
	}
	public void setAnoLectivoDescricao(String anoLectivoDescricao) {
		this.anoLectivoDescricao = anoLectivoDescricao;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Integer getCodigoAnoLectivo() {
		return codigoAnoLectivo;
	}
	public void setCodigoAnoLectivo(Integer codigoAnoLectivo) {
		this.codigoAnoLectivo = codigoAnoLectivo;
	}
	public boolean isBolseiro() {
		return bolseiro;
	}
	public void setBolseiro(boolean bolseiro) {
		this.bolseiro = bolseiro;
	}
	public String getEmpresaConvenio() {
		return empresaConvenio;
	}
	public void setEmpresaConvenio(String empresaConvenio) {
		this.empresaConvenio = empresaConvenio;
	} 
}
