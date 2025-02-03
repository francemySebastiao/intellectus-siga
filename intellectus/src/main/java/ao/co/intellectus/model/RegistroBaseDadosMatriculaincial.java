package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_REGISTRO_BASE_DE_DADOS_MATRICULA_INICIAL")
public class RegistroBaseDadosMatriculaincial {

	@Id
	private Integer id;
	@Column(name = "ANO_LECTIVO")
	private Integer anoLectivo;
	@Column(name = "NOME_COMPLETO")
	private String nomeCompleto;
	@Column(name = "BILHETE_DE_IDENTIDADE")
	private String bilheteIdentidade;
	@Column(name = "SEXO")
	private String sexo;
	@Column(name = "IDADE")
	private Integer idade;
	@Column(name = "DATA_NASCIMENTO")
	private Date dataNascimento;
	@Column(name = "PROVINCIA_RESIDENCIA")
	private String provinciaResidencia;
	@Column(name = "MUNICIPIO_RESIDENCIA")
	private String municioResidencia;
	@Column(name = "NACIONALIDADE")
	private String nacionalidade;
	@Column(name = "PERIODO_ESTUDO")
	private String periodoEstudo;
	@Column(name = "UNIDADE_ORGANICA")
	private String unidadeOrganica;
	@Column(name = "NOME_CURSO")
	private String nomeCurso;
	@Column(name = "ANO_FREQUENCIA")
	private Integer anoFrequencia;
	@Column(name = "SITUACAO_ACADEMICA")
	private String ssituacaoAcademica;
	@Column(name = "ANO_PRIMEIRO_MATRICULA")
	private Integer anoPrimeiraMatricula;
	@Column(name = "NESS_EDUCACAO_ESPECIAL")
	private String nessEducacaoEspecial;
	@Column(name = "TRABALHADOR")
	private String trabalhador;
	@Column(name = "NIVEL_GRADUACAO")
	private String nivelGraduacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getBilheteIdentidade() {
		return bilheteIdentidade;
	}

	public void setBilheteIdentidade(String bilheteIdentidade) {
		this.bilheteIdentidade = bilheteIdentidade;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getProvinciaResidencia() {
		return provinciaResidencia;
	}

	public void setProvinciaResidencia(String provinciaResidencia) {
		this.provinciaResidencia = provinciaResidencia;
	}

	public String getMunicioResidencia() {
		return municioResidencia;
	}

	public void setMunicioResidencia(String municioResidencia) {
		this.municioResidencia = municioResidencia;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getPeriodoEstudo() {
		return periodoEstudo;
	}

	public void setPeriodoEstudo(String periodoEstudo) {
		this.periodoEstudo = periodoEstudo;
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

	public String getSsituacaoAcademica() {
		return ssituacaoAcademica;
	}

	public void setSsituacaoAcademica(String ssituacaoAcademica) {
		this.ssituacaoAcademica = ssituacaoAcademica;
	}

	public Integer getAnoPrimeiraMatricula() {
		return anoPrimeiraMatricula;
	}

	public void setAnoPrimeiraMatricula(Integer anoPrimeiraMatricula) {
		this.anoPrimeiraMatricula = anoPrimeiraMatricula;
	}

	public String getNessEducacaoEspecial() {
		return nessEducacaoEspecial;
	}

	public void setNessEducacaoEspecial(String nessEducacaoEspecial) {
		this.nessEducacaoEspecial = nessEducacaoEspecial;
	}

	public String getTrabalhador() {
		return trabalhador;
	}

	public void setTrabalhador(String trabalhador) {
		this.trabalhador = trabalhador;
	}

	public String getNivelGraduacao() {
		return nivelGraduacao;
	}

	public void setNivelGraduacao(String nivelGraduacao) {
		this.nivelGraduacao = nivelGraduacao;
	}

}
