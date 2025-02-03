package ao.co.intellectus.DTO;

public class PlanoPagamentoAlteracao {
	private Integer idMatricula;
	private Integer idPlanoPagamento;
	private String userName;
	public Integer getIdMatricula() {
		return idMatricula;
	}
	public void setIdMatricula(Integer idMatricula) {
		this.idMatricula = idMatricula;
	}
	public Integer getIdPlanoPagamento() {
		return idPlanoPagamento;
	}
	public void setIdPlanoPagamento(Integer idPlanoPagamento) {
		this.idPlanoPagamento = idPlanoPagamento;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
