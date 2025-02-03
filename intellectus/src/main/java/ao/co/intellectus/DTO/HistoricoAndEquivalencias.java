package ao.co.intellectus.DTO;

import java.util.List;

import ao.co.intellectus.model.Equivalencia;

public class HistoricoAndEquivalencias {
	private Equivalencia equivalencia;
	private List<HistoricoEquivalenciaModelo> historicoEquivalencias;
	public Equivalencia getEquivalencia() {
		return equivalencia;
	}
	public void setEquivalencia(Equivalencia equivalencia) {
		this.equivalencia = equivalencia;
	}
	public List<HistoricoEquivalenciaModelo> getHistoricoEquivalencias() {
		return historicoEquivalencias;
	}
	public void setHistoricoEquivalencias(List<HistoricoEquivalenciaModelo> historicoEquivalencias) {
		this.historicoEquivalencias = historicoEquivalencias;
	}
}