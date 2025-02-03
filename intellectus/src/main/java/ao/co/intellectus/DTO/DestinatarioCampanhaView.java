package ao.co.intellectus.DTO;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

public class DestinatarioCampanhaView {

	private Integer id;
	@Column(nullable = false)
	private String destinatario;
	@ManyToOne
	private Integer campanha;
	private Boolean enviada;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public Integer getCampanha() {
		return campanha;
	}

	public void setCampanha(Integer campanha) {
		this.campanha = campanha;
	}

	public Boolean getEnviada() {
		return enviada;
	}

	public void setEnviada(Boolean enviada) {
		this.enviada = enviada;
	}

}
