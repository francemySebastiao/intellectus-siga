package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_empresa_convenio")
public class EmpresaConvenio {
    @Id 
    @GeneratedValue
	private Integer id;
    private String designacao;
    private boolean estado;
    private Integer quantidadeDeAlunos;
    private String morada;
    private String contribuinte;
    private String telefone;
    private String email;
    private String responsavelContrato;
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDesignacao() {
		return designacao;
	}
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Integer getQuantidadeDeAlunos() {
		return quantidadeDeAlunos;
	}
	public void setQuantidadeDeAlunos(Integer quantidadeDeAlunos) {
		this.quantidadeDeAlunos = quantidadeDeAlunos;
	}
	public String getMorada() {
		return morada;
	}
	public void setMorada(String morada) {
		this.morada = morada;
	}
	public String getContribuinte() {
		return contribuinte;
	}
	public void setContribuinte(String contribuinte) {
		this.contribuinte = contribuinte;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getResponsavelContrato() {
		return responsavelContrato;
	}
	public void setResponsavelContrato(String responsavelContrato) {
		this.responsavelContrato = responsavelContrato;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
