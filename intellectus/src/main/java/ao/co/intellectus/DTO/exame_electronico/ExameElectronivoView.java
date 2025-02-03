package ao.co.intellectus.DTO.exame_electronico;

import java.util.List;

public class ExameElectronivoView {

	private Boolean exameInicializado;
	private Boolean exameFinicializado;
	private String tipoExame;
	private List<PerguntaView> perguntas;

	public Boolean getExameInicializado() {
		return exameInicializado;
	}

	public void setExameInicializado(Boolean exameInicializado) {
		this.exameInicializado = exameInicializado;
	}

	public Boolean getExameFinicializado() {
		return exameFinicializado;
	}

	public void setExameFinicializado(Boolean exameFinicializado) {
		this.exameFinicializado = exameFinicializado;
	}

	public String getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(String tipoExame) {
		this.tipoExame = tipoExame;
	}

	public List<PerguntaView> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<PerguntaView> perguntas) {
		this.perguntas = perguntas;
	}

}
