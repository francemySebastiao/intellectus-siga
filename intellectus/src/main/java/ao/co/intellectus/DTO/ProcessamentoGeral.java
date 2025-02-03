package ao.co.intellectus.DTO;

public class ProcessamentoGeral {
	private String mes;
	private Integer emolumentoNormal;
	private String descricaoNormal;
	private Integer emolumentoInscricaoDisciplina;
	private String descricaoInscricaoDisciplina;
	private Integer anoLectivo;

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Integer getEmolumentoNormal() {
		return emolumentoNormal;
	}

	public void setEmolumentoNormal(Integer emolumentoNormal) {
		this.emolumentoNormal = emolumentoNormal;
	}

	public String getDescricaoNormal() {
		return descricaoNormal;
	}

	public void setDescricaoNormal(String descricaoNormal) {
		this.descricaoNormal = descricaoNormal;
	}

	public Integer getEmolumentoInscricaoDisciplina() {
		return emolumentoInscricaoDisciplina;
	}

	public void setEmolumentoInscricaoDisciplina(Integer emolumentoInscricaoDisciplina) {
		this.emolumentoInscricaoDisciplina = emolumentoInscricaoDisciplina;
	}

	public String getDescricaoInscricaoDisciplina() {
		return descricaoInscricaoDisciplina;
	}

	public void setDescricaoInscricaoDisciplina(String descricaoInscricaoDisciplina) {
		this.descricaoInscricaoDisciplina = descricaoInscricaoDisciplina;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
}
