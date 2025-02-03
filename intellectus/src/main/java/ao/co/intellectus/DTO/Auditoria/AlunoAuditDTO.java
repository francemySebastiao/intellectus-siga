package ao.co.intellectus.DTO.Auditoria;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AlunoAuditDTO {
	private String  NomeAluno;
	private String  NomeDoUsuariQueAlterou;
	private String  MotivoAlteracao;
	@DateTimeFormat(pattern ="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date    dataAlteracao;	
	private Date   dataNascimento;
	private String nacionalidade;
	private String provincia;
	private String municipio;
	private String paisDeResidencia;
	private String provinciaResidencia;
	private String municipioResidencia;
	private String telefone;
	private String nomeDoPai;
	private String nomeDaMae;
	
	public String getNomeAluno() {
		return NomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		NomeAluno = nomeAluno;
	}
	public String getNomeDoUsuariQueAlterou() {
		return NomeDoUsuariQueAlterou;
	}
	public void setNomeDoUsuariQueAlterou(String nomeDoUsuariQueAlterou) {
		NomeDoUsuariQueAlterou = nomeDoUsuariQueAlterou;
	}
	public String getMotivoAlteracao() {
		return MotivoAlteracao;
	}
	public void setMotivoAlteracao(String motivoAlteracao) {
		MotivoAlteracao = motivoAlteracao;
	}
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getPaisDeResidencia() {
		return paisDeResidencia;
	}
	public void setPaisDeResidencia(String paisDeResidencia) {
		this.paisDeResidencia = paisDeResidencia;
	}
	public String getProvinciaResidencia() {
		return provinciaResidencia;
	}
	public void setProvinciaResidencia(String provinciaResidencia) {
		this.provinciaResidencia = provinciaResidencia;
	}
	public String getMunicipioResidencia() {
		return municipioResidencia;
	}
	public void setMunicipioResidencia(String municipioResidencia) {
		this.municipioResidencia = municipioResidencia;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getNomeDoPai() {
		return nomeDoPai;
	}
	public void setNomeDoPai(String nomeDoPai) {
		this.nomeDoPai = nomeDoPai;
	}
	public String getNomeDaMae() {
		return nomeDaMae;
	}
	public void setNomeDaMae(String nomeDaMae) {
		this.nomeDaMae = nomeDaMae;
	}

}
