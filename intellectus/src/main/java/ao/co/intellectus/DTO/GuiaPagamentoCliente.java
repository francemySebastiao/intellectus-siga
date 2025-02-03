package ao.co.intellectus.DTO;

public class GuiaPagamentoCliente {
	private Integer aluno;
	private Integer AnoLectivo;
	private Integer Emolumento;
	//private List<ResumoHistoricoEmolumento> historicoEmolumento;
	
	public Integer getAluno() {
		return aluno;
	}
	public void setAluno(Integer aluno) {
		this.aluno = aluno;
	}
	public Integer getAnoLectivo() {
		return AnoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		AnoLectivo = anoLectivo;
	}
	public Integer getEmolumento() {
		return Emolumento;
	}
	public void setEmolumento(Integer emolumento) {
		Emolumento = emolumento;
	}
}
