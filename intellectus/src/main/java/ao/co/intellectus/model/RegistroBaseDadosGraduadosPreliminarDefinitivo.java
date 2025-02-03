package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "V_REGISTRO_BASE_DE_DADOS_GRADUADOS_PRELIMINAR_E_DEFINITIVO")
public class RegistroBaseDadosGraduadosPreliminarDefinitivo {

	@Id
	private Integer id;
	private Integer anoLectivo;
	@Column(name = "NOME_COMPLETO")
	private String nomeCompleto;
	@Column(name = "BILHETE_DE_IDENTIDADE")
	private String bilheteDeEEntidade;
	@Column(name = "SEXO", nullable = false)
	private String sexo;
	@Column(name = "IDADE")
	private Integer idade;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_NASCIMENTO")
	private Date dataNascimento;
	@Column(name = "PROVINCIA_RESIDENCIA")
	private String provinciaResidencia;
	@Column(name = "MUNICIPIO_RESIDENCIA")
	private String municipioResidencia;
	@Column(name = "NACIONALIDADE")
	private String nacionalidade;
	@Column(name = "PERIODO_ESTUDO", nullable = false)
	private String periodoEstudo;
	@Column(name = "UNIDADE_ORGANICA", nullable = false)
	private String unidadeOrganica;
	@Column(name = "NOME_CURSO")
	private String nomeCurso;
	@Column(name = "ANO_FREQUENCIA")
	private Integer anoFrequencia;
	@Column(name = "ANO_PRIMEIRO_MATRICULA")
	private Integer anoPrimeiroMatricula;
	@Column(name = "TRABALHADOR", nullable = false)
	private String trabalhador;
	@Column(name = "NIVEL_GRADUACAO")
	private String nivelGraducao;
	@Column(name = "QUADRO_HONRA", nullable = false)
	private String quadroHonra;
	@Column(name = "MEDIA_FINA")
	private Integer mediaFinal;

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

	public String getBilheteDeEEntidade() {
		return bilheteDeEEntidade;
	}

	public void setBilheteDeEEntidade(String bilheteDeEEntidade) {
		this.bilheteDeEEntidade = bilheteDeEEntidade;
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

	public String getMunicipioResidencia() {
		return municipioResidencia;
	}

	public void setMunicipioResidencia(String municipioResidencia) {
		this.municipioResidencia = municipioResidencia;
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

	public Integer getAnoPrimeiroMatricula() {
		return anoPrimeiroMatricula;
	}

	public void setAnoPrimeiroMatricula(Integer anoPrimeiroMatricula) {
		this.anoPrimeiroMatricula = anoPrimeiroMatricula;
	}

	public String getTrabalhador() {
		return trabalhador;
	}

	public void setTrabalhador(String trabalhador) {
		this.trabalhador = trabalhador;
	}

	public String getNivelGraducao() {
		return nivelGraducao;
	}

	public void setNivelGraducao(String nivelGraducao) {
		this.nivelGraducao = nivelGraducao;
	}

	public String getQuadroHonra() {
		return quadroHonra;
	}

	public void setQuadroHonra(String quadroHonra) {
		this.quadroHonra = quadroHonra;
	}

	public Integer getMediaFinal() {
		return mediaFinal;
	}

	public void setMediaFinal(Integer mediaFinal) {
		this.mediaFinal = mediaFinal;
	}

}
