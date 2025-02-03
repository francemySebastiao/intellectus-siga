package ao.co.intellectus.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_instituicao")
public class Instituicao {
	@Id
	@GeneratedValue
	private int id;
	private String descricao;
	private String sigla;
	private String grupoOwner;
	private String endereco;
	private String unidadeOrganica;
	@Column(length = 4)
	private String code;
	private String contribuinte;
	private String chefeServicosAcademicos;
	private String secretarioGeral;
	//private String secretarioGeralAdjunto;
	private String webSite;
	private String decretoLei;
	private String reitor;
	private String viceReitor;
	private String email;
	private Blob logo;
	private String chavePrivada;

	public Instituicao() {
	}

	public Instituicao(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getGrupoOwner() {
		return grupoOwner;
	}

	public void setGrupoOwner(String grupoOwner) {
		this.grupoOwner = grupoOwner;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getUnidadeOrganica() {
		return unidadeOrganica;
	}

	public void setUnidadeOrganica(String unidadeOrganica) {
		this.unidadeOrganica = unidadeOrganica;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContribuinte() {
		return contribuinte;
	}

	public void setContribuinte(String contribuinte) {
		this.contribuinte = contribuinte;
	}

	public String getChefeServicosAcademicos() {
		return chefeServicosAcademicos;
	}

	public void setChefeServicosAcademicos(String chefeServicosAcademicos) {
		this.chefeServicosAcademicos = chefeServicosAcademicos;
	}

	public String getSecretarioGeral() {
		return secretarioGeral;
	}

	public void setSecretarioGeral(String secretarioGeral) {
		this.secretarioGeral = secretarioGeral;
	}
	/*
	public String getSecretarioGeralAdjunto() {
		return secretarioGeralAdjunto;
	}

	public void setSecretarioGeralAdjunto(String secretarioGeralAdjunto) {
		this.secretarioGeralAdjunto = secretarioGeralAdjunto;
	}*/

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getDecretoLei() {
		return decretoLei;
	}

	public void setDecretoLei(String decretoLei) {
		this.decretoLei = decretoLei;
	}

	public String getReitor() {
		return reitor;
	}

	public void setReitor(String reitor) {
		this.reitor = reitor;
	}

	public String getViceReitor() {
		return viceReitor;
	}

	public void setViceReitor(String viceReitor) {
		this.viceReitor = viceReitor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Blob getLogo() {
		return logo;
	}

	public void setLogo(Blob logo) {
		this.logo = logo;
	}

	public String getChavePrivada() {
		return chavePrivada;
	}

	public void setChavePrivada(String chavePrivada) {
		this.chavePrivada = chavePrivada;
	}
	
	

}