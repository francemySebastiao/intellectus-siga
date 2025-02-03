package ao.co.intellectus.DTO;

import java.util.Date;

public class PedidoDocumentoMestrado {

	
	private Date dataEmissao;
	private String tipoDeclaracao; 
	private String nomeAluno;
	private String numeroAluno;
	private Integer numeroDeclaracao;
	private String nomeOperador;
	
	
	public String getTipoDeclaracao() {
		return tipoDeclaracao;
	}
	public void setTipoDeclaracao(String tipoDeclaracao) {
		this.tipoDeclaracao = tipoDeclaracao;
	}
	
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public String getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(String numeroAluno) {
		this.numeroAluno = numeroAluno;
	}
	public Integer getNumeroDeclaracao() {
		return numeroDeclaracao;
	}
	public void setNumeroDeclaracao(Integer numeroDeclaracao) {
		this.numeroDeclaracao = numeroDeclaracao;
	}
	public String getNomeOperador() {
		return nomeOperador;
	}
	public void setNomeOperador(String nomeOperador) {
		this.nomeOperador = nomeOperador;
	}
	
}
	
