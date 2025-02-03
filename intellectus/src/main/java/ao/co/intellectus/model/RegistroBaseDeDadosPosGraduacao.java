package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_REGISTRO_BASE_DE_DADOS_DE_POS_GRADUACAO")
public class RegistroBaseDeDadosPosGraduacao {
	@Id
	private Integer id;
	@Column(name = "ANO_LECTIVO")
	private Integer anoLectivo;
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
	@Column(name = "NACIONALIDADE")
	private String nacionalidade;
	@Column(name = "UNIDADE_ORGANICA")
	private String unidadeOrganica;
	@Column(name = "TIPO_GUADUACAO")
	private String tipoGraduacao;
	@Column(name = "NOME_CURSO")
	private String nomeCurso;
	@Column(name = "NUMERO_EDICAO")
	private Integer numeroEdicao;
	@Column(name = "SITUACAO_ACADEMICA")
	private String situacaoAcademica;
	@Column(name = "TIPO_FUNCIONARIO")
	private String tipoFuncionario;
	@Column(name = "TRABALHADOR")
	private String trabalhador;
	@Column(name = "NESS_EDUCACAO_ESPECIAL")
	private String nessEducacaoEspecia;
	@Column(name = "ANO_PRIMEIRO_MATRICULA")
	private Integer anoPrimeiroMatricula;
	@Column(name = "DURACAO_FORMACAO")
	private Integer duracaoFormacao;

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

	public String getTipoGraduacao() {
		return tipoGraduacao;
	}

	public void setTipoGraduacao(String tipoGraduacao) {
		this.tipoGraduacao = tipoGraduacao;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Integer getNumeroEdicao() {
		return numeroEdicao;
	}

	public void setNumeroEdicao(Integer numeroEdicao) {
		this.numeroEdicao = numeroEdicao;
	}

	public String getSituacaoAcademica() {
		return situacaoAcademica;
	}

	public void setSituacaoAcademica(String situacaoAcademica) {
		this.situacaoAcademica = situacaoAcademica;
	}

	public String getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(String tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	public String getTrabalhador() {
		return trabalhador;
	}

	public void setTrabalhador(String trabalhador) {
		this.trabalhador = trabalhador;
	}

	public String getNessEducacaoEspecia() {
		return nessEducacaoEspecia;
	}

	public void setNessEducacaoEspecia(String nessEducacaoEspecia) {
		this.nessEducacaoEspecia = nessEducacaoEspecia;
	}

	public Integer getAnoPrimeiroMatricula() {
		return anoPrimeiroMatricula;
	}

	public void setAnoPrimeiroMatricula(Integer anoPrimeiroMatricula) {
		this.anoPrimeiroMatricula = anoPrimeiroMatricula;
	}

	public Integer getDuracaoFormacao() {
		return duracaoFormacao;
	}

	public void setDuracaoFormacao(Integer duracaoFormacao) {
		this.duracaoFormacao = duracaoFormacao;
	}

}
