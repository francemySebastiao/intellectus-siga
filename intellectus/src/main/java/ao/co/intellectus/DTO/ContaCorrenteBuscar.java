package ao.co.intellectus.DTO;

public class ContaCorrenteBuscar {
	
	private Integer buscarId;
	private boolean tipo;
	private Integer anoLectivo;
	
	public Integer getBuscarId() {
		return buscarId;
	}

	public void setBuscarId(Integer buscarId) {
		this.buscarId = buscarId;
	}

	public boolean isTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
}
