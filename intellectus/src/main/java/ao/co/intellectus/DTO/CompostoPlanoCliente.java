package ao.co.intellectus.DTO;

import java.util.List;

import ao.co.intellectus.model.GuiaMultaValor;

public class CompostoPlanoCliente {
    private GuiaMultaValor guiaMultaValor;
    private List<GuiaCliente> guiasAnuladasParaAcordo;
    private List<GuiaCliente> guiasDoAcordo;
	public GuiaMultaValor getGuiaMultaValor() {
		return guiaMultaValor;
	}
	public void setGuiaMultaValor(GuiaMultaValor guiaMultaValor) {
		this.guiaMultaValor = guiaMultaValor;
	}
	public List<GuiaCliente> getGuiasAnuladasParaAcordo() {
		return guiasAnuladasParaAcordo;
	}
	public void setGuiasAnuladasParaAcordo(List<GuiaCliente> guiasAnuladasParaAcordo) {
		this.guiasAnuladasParaAcordo = guiasAnuladasParaAcordo;
	}
	public List<GuiaCliente> getGuiasDoAcordo() {
		return guiasDoAcordo;
	}
	public void setGuiasDoAcordo(List<GuiaCliente> guiasDoAcordo) {
		this.guiasDoAcordo = guiasDoAcordo;
	}
}
