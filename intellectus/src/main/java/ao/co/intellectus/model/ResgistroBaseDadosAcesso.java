package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_REGISTRO_BASE_DE_DADOS_ACESSO")
public class ResgistroBaseDadosAcesso {

	@Id
	private Integer id;
	@Column(name = "ANO_LECTIVO")
	private Integer anoLectivo;
	@Column(name = "NOME_COMPLETO")
	private String nomeCompleto;
	@Column(name = "BILHETE_DE_IDENTIDADE")
	private String bilheteDeIdentidade;
	@Column(name = "SEXO")
	private String Sexo;
	@Column(name = "IDADE")
	private Integer idade;
	@Column(name = "DATA_NASCIMENTO")
	private Date dataDeNascimento;
	@Column(name = "PROVINCIA_RESIDENCIA")
	private String provinciaResidencia;
	@Column(name = "MUNICIPIO_RESIDENCIA")
	private String municipioResidencia;
	@Column(name = "NACIONALIDADE")
	private String nacionalidade;
	@Column(name = "PERIODO_ESTUDO")
	private String periodoEstudo;
	@Column(name = "UNIDADE_ORGANICA")
	private String unidadeOrganica;
	@Column(name = "NOME_CURSO")
	private String nomeCurso;
	@Column(name = "NOTA_EXAME")
	private Float notaExame;
	@Column(name = "ADIMISSAO")
	private String adimissao;
	@Column(name = "PROC_ENS_MEDIO")
	private String procEnsMedio;
	@Column(name = "CURSO_ENS_MEDIO")
	private String cursoEnsMedio;
	@Column(name = "TRABALHADOR")
	private String trabalhador;

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

	public String getBilheteDeIdentidade() {
		return bilheteDeIdentidade;
	}

	public void setBilheteDeIdentidade(String bilheteDeIdentidade) {
		this.bilheteDeIdentidade = bilheteDeIdentidade;
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
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

	public Float getNotaExame() {
		return notaExame;
	}

	public void setNotaExame(Float notaExame) {
		this.notaExame = notaExame;
	}

	public String getAdimissao() {
		return adimissao;
	}

	public void setAdimissao(String adimissao) {
		this.adimissao = adimissao;
	}

	public String getProcEnsMedio() {
		return procEnsMedio;
	}

	public void setProcEnsMedio(String procEnsMedio) {
		this.procEnsMedio = procEnsMedio;
	}

	public String getCursoEnsMedio() {
		return cursoEnsMedio;
	}

	public void setCursoEnsMedio(String cursoEnsMedio) {
		this.cursoEnsMedio = cursoEnsMedio;
	}

	public String getTrabalhador() {
		return trabalhador;
	}

	public void setTrabalhador(String trabalhador) {
		this.trabalhador = trabalhador;
	}

}
