package ao.co.intellectus.model.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Grau;

public class CursoRequest {
	private Integer id;
    private String descricao;
    private String sigla;
    private boolean status;
    private int duracao;
    private int qtdCadeiras;
    private String codigo;
    private int inicio;
    private String plano;
    private Integer faculdade;
    @Enumerated(EnumType.ORDINAL)
    private Grau grau;
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
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	public int getQtdCadeiras() {
		return qtdCadeiras;
	}
	public void setQtdCadeiras(int qtdCadeiras) {
		this.qtdCadeiras = qtdCadeiras;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getInicio() {
		return inicio;
	}
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	public String getPlano() {
		return plano;
	}
	public void setPlano(String plano) {
		this.plano = plano;
	}
	public Integer getFaculdade() {
		return faculdade;
	}
	public void setFaculdade(Integer faculdade) {
		this.faculdade = faculdade;
	}
	public Grau getGrau() {
		return grau;
	}
	public void setGrau(Grau grau) {
		this.grau = grau;
	}

}
