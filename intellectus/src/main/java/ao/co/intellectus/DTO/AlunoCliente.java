package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.EstadoCivil;
import ao.co.intellectus.model.Sexo;

public class AlunoCliente { 
	private Integer id;
	private String numeroDeAluno;
	private String numeroMecanografico; // novo
//1. DADOS PESSOAIS
	private String nome;
	private Integer curso;
	@Enumerated(EnumType.ORDINAL)
	private Sexo sexo;
	@Enumerated(EnumType.ORDINAL)
    private EstadoCivil estadoCivil;
//2. DADOS DE NASCIMENTO                -> OK
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	private Integer nacionalidade;
	private Integer provincia; //novo 
	private Integer municipio; //novo
//2. DADOS DE CONTACTOS                 -> OK
	private String morada;
	private String bairro;
	private Integer paisDeResidencia; 
	private Integer provinciaResidencia;
	private Integer municipioResidencia;
	private String telefone;
	private String telefone2;
	private String telefone3;
	private boolean isTrabalhador;
	private String profissao;
	private String empresa;
	private String email;
//SEGUINDO AS VALIDAÇÕES
	private String bilheteIdentidade;
	private String DescricaoCurso;
	private boolean certificado;
	private boolean fotografia;
	private String nomeDoPai;
	private String nomeDaMae; 
	private String docuementoIdentificacao;
	private String numeroDocumento;
	/* Edereço de morada do candidato... */
	private String rua;
	/* Final dos dados de endereço de candidato... */
	private String numeroCasa;
	private Date dataEmissao;
	private String arquivoIdentificacao;
	private boolean AtestadoMedico;
	private boolean nif;
	private String semestre;
	private int anoLectivo;
	private double mediaFimCurso;
	private String referenciaDiploma;
	private boolean coteciso;
	private String observacao;
	private boolean anulado;
	@Temporal(TemporalType.DATE)
	private Date dataAnulamento;
	private boolean fimCurso;
	@Temporal(TemporalType.DATE)
	private Date dataFimCurso;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] foto;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(String numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public String getNumeroMecanografico() {
		return numeroMecanografico;
	}
	public void setNumeroMecanografico(String numeroMecanografico) {
		this.numeroMecanografico = numeroMecanografico;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getCurso() {
		return curso;
	}
	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Integer getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(Integer nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public Integer getProvincia() {
		return provincia;
	}
	public void setProvincia(Integer provincia) {
		this.provincia = provincia;
	}
	public Integer getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}
	public String getMorada() {
		return morada;
	}
	public void setMorada(String morada) {
		this.morada = morada;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public Integer getPaisDeResidencia() {
		return paisDeResidencia;
	}
	public void setPaisDeResidencia(Integer paisDeResidencia) {
		this.paisDeResidencia = paisDeResidencia;
	}
	public Integer getProvinciaResidencia() {
		return provinciaResidencia;
	}
	public void setProvinciaResidencia(Integer provinciaResidencia) {
		this.provinciaResidencia = provinciaResidencia;
	}
	public Integer getMunicipioResidencia() {
		return municipioResidencia;
	}
	public void setMunicipioResidencia(Integer municipioResidencia) {
		this.municipioResidencia = municipioResidencia;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public String getTelefone3() {
		return telefone3;
	}
	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}
	public boolean isTrabalhador() {
		return isTrabalhador;
	}
	public void setTrabalhador(boolean isTrabalhador) {
		this.isTrabalhador = isTrabalhador;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBilheteIdentidade() {
		return bilheteIdentidade;
	}
	public void setBilheteIdentidade(String bilheteIdentidade) {
		this.bilheteIdentidade = bilheteIdentidade;
	}
	public String getDescricaoCurso() {
		return DescricaoCurso;
	}
	public void setDescricaoCurso(String descricaoCurso) {
		DescricaoCurso = descricaoCurso;
	}
	public boolean isCertificado() {
		return certificado;
	}
	public void setCertificado(boolean certificado) {
		this.certificado = certificado;
	}
	public boolean isFotografia() {
		return fotografia;
	}
	public void setFotografia(boolean fotografia) {
		this.fotografia = fotografia;
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
	public String getDocuementoIdentificacao() {
		return docuementoIdentificacao;
	}
	public void setDocuementoIdentificacao(String docuementoIdentificacao) {
		this.docuementoIdentificacao = docuementoIdentificacao;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumeroCasa() {
		return numeroCasa;
	}
	public void setNumeroCasa(String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public String getArquivoIdentificacao() {
		return arquivoIdentificacao;
	}
	public void setArquivoIdentificacao(String arquivoIdentificacao) {
		this.arquivoIdentificacao = arquivoIdentificacao;
	}
	public boolean isAtestadoMedico() {
		return AtestadoMedico;
	}
	public void setAtestadoMedico(boolean atestadoMedico) {
		AtestadoMedico = atestadoMedico;
	}
	public boolean isNif() {
		return nif;
	}
	public void setNif(boolean nif) {
		this.nif = nif;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public int getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(int anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public double getMediaFimCurso() {
		return mediaFimCurso;
	}
	public void setMediaFimCurso(double mediaFimCurso) {
		this.mediaFimCurso = mediaFimCurso;
	}
	public String getReferenciaDiploma() {
		return referenciaDiploma;
	}
	public void setReferenciaDiploma(String referenciaDiploma) {
		this.referenciaDiploma = referenciaDiploma;
	}
	public boolean isCoteciso() {
		return coteciso;
	}
	public void setCoteciso(boolean coteciso) {
		this.coteciso = coteciso;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public boolean isAnulado() {
		return anulado;
	}
	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}
	public Date getDataAnulamento() {
		return dataAnulamento;
	}
	public void setDataAnulamento(Date dataAnulamento) {
		this.dataAnulamento = dataAnulamento;
	}
	public boolean isFimCurso() {
		return fimCurso;
	}
	public void setFimCurso(boolean fimCurso) {
		this.fimCurso = fimCurso;
	}
	public Date getDataFimCurso() {
		return dataFimCurso;
	}
	public void setDataFimCurso(Date dataFimCurso) {
		this.dataFimCurso = dataFimCurso;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
}