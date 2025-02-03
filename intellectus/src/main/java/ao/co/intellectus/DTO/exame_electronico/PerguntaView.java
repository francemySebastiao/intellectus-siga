package ao.co.intellectus.DTO.exame_electronico;

import java.util.List;

public class PerguntaView {
	private Integer id;
	private String descricao;
	private List<RespostaView> respostas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<RespostaView> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<RespostaView> respostas) {
		this.respostas = respostas;
	}

}
