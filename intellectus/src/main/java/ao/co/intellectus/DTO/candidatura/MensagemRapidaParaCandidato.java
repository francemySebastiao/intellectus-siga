package ao.co.intellectus.DTO.candidatura;

import java.util.List;

import ao.co.intellectus.DTO.AlvoParaEnviarMensagem;

public class MensagemRapidaParaCandidato {

	private String corpoMensagem;
	private List<AlvoParaEnviarMensagem> listaTelefonica;

	public String getCorpoMensagem() {
		return corpoMensagem;
	}

	public void setCorpoMensagem(String corpoMensagem) {
		this.corpoMensagem = corpoMensagem;
	}

	public List<AlvoParaEnviarMensagem> getListaTelefonica() {
		return listaTelefonica;
	}

	public void setListaTelefonica(List<AlvoParaEnviarMensagem> listaTelefonica) {
		this.listaTelefonica = listaTelefonica;
	}

}
