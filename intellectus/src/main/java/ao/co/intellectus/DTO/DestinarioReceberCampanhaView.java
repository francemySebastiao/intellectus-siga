package ao.co.intellectus.DTO;

import java.util.List;

public class DestinarioReceberCampanhaView {

	private Integer campanha;
	private List<Integer> destinarios;

	public Integer getCampanha() {
		return campanha;
	}

	public void setCampanha(Integer campanha) {
		this.campanha = campanha;
	}

	public List<Integer> getDestinarios() {
		return destinarios;
	}

	public void setDestinarios(List<Integer> destinarios) {
		this.destinarios = destinarios;
	}

}
