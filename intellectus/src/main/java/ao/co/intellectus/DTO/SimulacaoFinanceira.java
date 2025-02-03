package ao.co.intellectus.DTO;

import java.util.List;

public class SimulacaoFinanceira {
	private String nome;
    private Integer numero;
    private String grau;
    private String curso;
    private boolean contencioso;
    private List<MensalidadesLiquidadasResumo> mensalidadeLiquidadas;
    private float totalPropinas;
	private float totalDeposito;
	private float totalExame;
	private float totalRecurso;
    private float totalInscricao;
    private float totalEquivalencia;
    private float totalAcordo;
    private float totalAcordoNaoVencido;
    private float totalCredito;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getGrau() {
		return grau;
	}
	public void setGrau(String grau) {
		this.grau = grau;
	}
	public boolean isContencioso() {
		return contencioso;
	}
	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}
	public List<MensalidadesLiquidadasResumo> getMensalidadeLiquidadas() {
		return mensalidadeLiquidadas;
	}
	public void setMensalidadeLiquidadas(List<MensalidadesLiquidadasResumo> mensalidadeLiquidadas) {
		this.mensalidadeLiquidadas = mensalidadeLiquidadas;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public float getTotalPropinas() {
		return totalPropinas;
	}
	public void setTotalPropinas(float totalPropinas) {
		this.totalPropinas = totalPropinas;
	}
	public float getTotalDeposito() {
		return totalDeposito;
	}
	public void setTotalDeposito(float totalDeposito) {
		this.totalDeposito = totalDeposito;
	}
	public float getTotalExame() {
		return totalExame;
	}
	public void setTotalExame(float totalExame) {
		this.totalExame = totalExame;
	}
	public float getTotalRecurso() {
		return totalRecurso;
	}
	public void setTotalRecurso(float totalRecurso) {
		this.totalRecurso = totalRecurso;
	}
	public float getTotalInscricao() {
		return totalInscricao;
	}
	public void setTotalInscricao(float totalInscricao) {
		this.totalInscricao = totalInscricao;
	}
	public float getTotalEquivalencia() {
		return totalEquivalencia;
	}
	public void setTotalEquivalencia(float totalEquivalencia) {
		this.totalEquivalencia = totalEquivalencia;
	}
	public float getTotalAcordo() {
		return totalAcordo;
	}
	public void setTotalAcordo(float totalAcordo) {
		this.totalAcordo = totalAcordo;
	}
	public float getTotalAcordoNaoVencido() {
		return totalAcordoNaoVencido;
	}
	public void setTotalAcordoNaoVencido(float totalAcordoNaoVencido) {
		this.totalAcordoNaoVencido = totalAcordoNaoVencido;
	}
	public float getTotalCredito() {
		return totalCredito;
	}
	public void setTotalCredito(float totalCredito) {
		this.totalCredito = totalCredito;
	}  
}
