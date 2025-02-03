package ao.co.intellectus.DTO;

public class CandidatoExameCliente {
	private String numeroDocumento;
	private Integer anoLectivo;
	private Integer notaFinal;
	public Integer getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(Integer notaFinal) {
		this.notaFinal = notaFinal;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
}
