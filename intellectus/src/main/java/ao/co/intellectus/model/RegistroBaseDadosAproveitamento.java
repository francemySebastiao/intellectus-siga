package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_REGISTRO_BASE_DADOS_APROVEITAMENTO")
public class RegistroBaseDadosAproveitamento {

	@Id
	private Integer id;
	@Column(name = "ANO_LECTIVO")
	private Integer anolectivo;
	@Column(name = "NOME_COMPLETO")
	private String nomeCompleto;
	@Column(name = "BILHETE_DE_IDENTIDADE")
	private String bilheteDeItendidade;
	@Column(name = "SEXO")
	private String sexo;
	@Column(name = "IDADE")
	private Integer idade;
	@Column(name = "DATA_NASCIMENTO")
	private Date dataDeNascimento;
	@Column(name = "PROVINCIA_RESIDENCIA")
	private String provinciaoResidencia;
	@Column(name = "MUNICIPIO_RESIDENCIA")
	private String municipioResidencia;
	@Column(name = "PERIODO_ESTUDO")
	private String periodoEstudo;
	@Column(name = "NACIONALIDADE")
	private String nacionalidade;
	@Column(name = "UNIDADE_ORGANICA")
	private String unidadeOrganica;
	@Column(name = "NOME_CURSO")
	private String nomeCurso;
	@Column(name = "ANO_FREQUENCIA")
	private Integer anoFrequencia;
	@Column(name = "SITUACAO_ACADEMICA")
	private String situacaoAcademica;
	@Column(name = "Aproveitamento")
	private String aproveitamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnolectivo() {
		return anolectivo;
	}

	public void setAnolectivo(Integer anolectivo) {
		this.anolectivo = anolectivo;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getBilheteDeItendidade() {
		return bilheteDeItendidade;
	}

	public void setBilheteDeItendidade(String bilheteDeItendidade) {
		this.bilheteDeItendidade = bilheteDeItendidade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getProvinciaoResidencia() {
		return provinciaoResidencia;
	}

	public void setProvinciaoResidencia(String provinciaoResidencia) {
		this.provinciaoResidencia = provinciaoResidencia;
	}

	public String getMunicipioResidencia() {
		return municipioResidencia;
	}

	public void setMunicipioResidencia(String municipioResidencia) {
		this.municipioResidencia = municipioResidencia;
	}

	public String getPeriodoEstudo() {
		return periodoEstudo;
	}

	public void setPeriodoEstudo(String periodoEstudo) {
		this.periodoEstudo = periodoEstudo;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getUnidadeOrganica() {
		return unidadeOrganica;
	}

	public void setUnidadeOrganica(String unidadeOrganica) {
		this.unidadeOrganica = unidadeOrganica;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Integer getAnoFrequencia() {
		return anoFrequencia;
	}

	public void setAnoFrequencia(Integer anoFrequencia) {
		this.anoFrequencia = anoFrequencia;
	}

	public String getSituacaoAcademica() {
		return situacaoAcademica;
	}

	public void setSituacaoAcademica(String situacaoAcademica) {
		this.situacaoAcademica = situacaoAcademica;
	}

	public String getAproveitamento() {
		return aproveitamento;
	}

	public void setAproveitamento(String aproveitamento) {
		this.aproveitamento = aproveitamento;
	}

}
