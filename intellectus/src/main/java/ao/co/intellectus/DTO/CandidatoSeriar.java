package ao.co.intellectus.DTO;


import ao.co.intellectus.model.Candidato;

public class CandidatoSeriar extends Candidato{
	private Boolean guiaLiquidada;
	private Boolean tipoDeCandidatura;
	private Boolean equivalencia;
	private Boolean cursoAcesso;
	public Boolean isGuiaLiquidada() {
		return guiaLiquidada;
	}
	public void setGuiaLiquidada(Boolean guiaLiquidada) {
		this.guiaLiquidada = guiaLiquidada;
	}
	public Boolean isTipoDeCandidatura() {
		return tipoDeCandidatura;
	}
	public void setTipoDeCandidatura(Boolean tipoDeCandidatura) {
		this.tipoDeCandidatura = tipoDeCandidatura;
	}
	public Boolean isEquivalencia() {
		return equivalencia;
	}
	public void setEquivalencia(Boolean equivalencia) {
		this.equivalencia = equivalencia;
	}
	public Boolean isCursoAcesso() {
		return cursoAcesso;
	}
	public void setCursoAcesso(Boolean cursoAcesso) {
		this.cursoAcesso = cursoAcesso;
	}	
}