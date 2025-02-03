package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.Documento;
import ao.co.intellectus.model.EstadoCivil;
import ao.co.intellectus.model.NecessidadeEspecial;
import ao.co.intellectus.model.Sexo;

public class AlunoGeral {
	private Integer id;
	@Column(unique = true)
	private String numeroDeAluno;
	private String numeroMecanografico;
//1. DADOS PESSOAIS ---------------------------------->   OK
	private String nome;
	private Integer curso;
	private String cursoDescricao;
	@Enumerated(EnumType.ORDINAL)
	private Sexo sexo;
	@Enumerated(EnumType.ORDINAL)
	private EstadoCivil estadoCivil;
//2. DADOS DE NASCIMENTO------------------------------>   OK
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nasciemnto")
	private Date dataNascimento;
	private Integer nacionalidade;
	private String nacionalidadeDescricao;
	private Integer provincia;
	private Integer provinciaDescricao;
	private Integer municipio;
	private Integer municipioDescricao;
	private Integer naturalidade;
//3. DADOS DE CONTACTOS------------------------------->   OK
	private String morada;
	private String bairro;
	private Integer paisDeResidencia;
	private String paisDeResidenciaDescricao;
	private Integer provinciaResidencia;
	private String provinciaResidenciaDescricao;
	private Integer municipioResidencia;
	private String municipioResidenciaDescricao;
	private String email;
	private String emailInstitucional;
	private String telefone;
	private String telefone1;
	private String telefone2;
	private String telefone3;
	private boolean isTrabalhador;
	private String profissao;
	private String empresa;
//4. DADOS DE IDENTIFICAÇÃO-------------------------->   OK
	@Column(name = "documento_indentificacao")
	@Enumerated(EnumType.STRING)
	private Documento documentoIdentificacao;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emissao")
	private Date dataEmissao;
	@Column(name = "numero_documento")
	private Date dataEmissaoDocumento;
	@Column(name = "arquivo_identificacao")
	private String arquivoIdentificacao;
	@Column(name = "nome_do_pai")
	private String nomeDoPai;
	@Column(name = "nome_da_mae")
	private String nomeDaMae;
	private String profissaoPai;
	private String profissaoMae;
	@Enumerated(EnumType.STRING)
	private NecessidadeEspecial necessidadeEspecial;
//5. DADOS DA SITUAÇÃO ACADEMICA----------------------->  OK
	@Column(name = "atestado_medico", nullable = true)
	private boolean AtestadoMedico;
	private boolean copiaDocumentoIdentificacao;
	@Column(nullable = true)
	private boolean CopiaCertificado;
	private boolean fotografias;
	private boolean copiaDocumentoMilitar;
	@Column(name = "cartao_de_contribuinte", nullable = true)
	private String nif;
	private boolean inactivo;
	@Temporal(TemporalType.DATE)
	private Date dataInativo;
	@Temporal(TemporalType.DATE)
	private Date dataCandidatura;
	@Temporal(TemporalType.DATE)
	private Date dataPrimeiraMatricula;
	private boolean cursoFinalizado;
	@Temporal(TemporalType.DATE)
	private Date dataFimDoCurso;
	private double mediaFinal;
	private Integer anoFimDoCurso;
	//private String ClassificacaoMestrado;
	private String anoFimDoCursoDescricao;
	//private String temaDissertacao;
	//private String nomeOrientadorDissertacao;
//6. DADOS COMPLETARES-----------------------------------> OK
	private boolean anulado;
	@Temporal(TemporalType.DATE)
	private Date dataAnulado;
	private String observacao;
	private boolean contencioso;
	private String referenciaDiploma;
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	@Temporal(TemporalType.DATE)
	private Date dataAnulamento;
	private boolean fimCurso;
	@Temporal(TemporalType.DATE)
	private Date dataFimCurso;
	private String numeroDocumento;
	// AUDITORIA
	private Integer userCode;
	private String userName;

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

	public String getCursoDescricao() {
		return cursoDescricao;
	}

	public void setCursoDescricao(String cursoDescricao) {
		this.cursoDescricao = cursoDescricao;
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

	public String getNacionalidadeDescricao() {
		return nacionalidadeDescricao;
	}

	public void setNacionalidadeDescricao(String nacionalidadeDescricao) {
		this.nacionalidadeDescricao = nacionalidadeDescricao;
	}

	public Integer getProvincia() {
		return provincia;
	}

	public void setProvincia(Integer provincia) {
		this.provincia = provincia;
	}

	public Integer getProvinciaDescricao() {
		return provinciaDescricao;
	}

	public void setProvinciaDescricao(Integer provinciaDescricao) {
		this.provinciaDescricao = provinciaDescricao;
	}

	public Integer getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}

	public Integer getMunicipioDescricao() {
		return municipioDescricao;
	}

	public void setMunicipioDescricao(Integer municipioDescricao) {
		this.municipioDescricao = municipioDescricao;
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

	public String getPaisDeResidenciaDescricao() {
		return paisDeResidenciaDescricao;
	}

	public void setPaisDeResidenciaDescricao(String paisDeResidenciaDescricao) {
		this.paisDeResidenciaDescricao = paisDeResidenciaDescricao;
	}

	public Integer getProvinciaResidencia() {
		return provinciaResidencia;
	}

	public void setProvinciaResidencia(Integer provinciaResidencia) {
		this.provinciaResidencia = provinciaResidencia;
	}

	public String getProvinciaResidenciaDescricao() {
		return provinciaResidenciaDescricao;
	}

	public void setProvinciaResidenciaDescricao(String provinciaResidenciaDescricao) {
		this.provinciaResidenciaDescricao = provinciaResidenciaDescricao;
	}

	public Integer getMunicipioResidencia() {
		return municipioResidencia;
	}

	public void setMunicipioResidencia(Integer municipioResidencia) {
		this.municipioResidencia = municipioResidencia;
	}

	public String getMunicipioResidenciaDescricao() {
		return municipioResidenciaDescricao;
	}

	public void setMunicipioResidenciaDescricao(String municipioResidenciaDescricao) {
		this.municipioResidenciaDescricao = municipioResidenciaDescricao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailInstitucional() {
		return emailInstitucional;
	}

	public void setEmailInstitucional(String emailInstitucional) {
		this.emailInstitucional = emailInstitucional;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
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

	public Documento getDocumentoIdentificacao() {
		return documentoIdentificacao;
	}

	public void setDocumentoIdentificacao(Documento documentoIdentificacao) {
		this.documentoIdentificacao = documentoIdentificacao;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataEmissaoDocumento() {
		return dataEmissaoDocumento;
	}

	public void setDataEmissaoDocumento(Date dataEmissaoDocumento) {
		this.dataEmissaoDocumento = dataEmissaoDocumento;
	}

	public String getArquivoIdentificacao() {
		return arquivoIdentificacao;
	}

	public void setArquivoIdentificacao(String arquivoIdentificacao) {
		this.arquivoIdentificacao = arquivoIdentificacao;
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

	public String getProfissaoPai() {
		return profissaoPai;
	}

	public void setProfissaoPai(String profissaoPai) {
		this.profissaoPai = profissaoPai;
	}

	public String getProfissaoMae() {
		return profissaoMae;
	}

	public void setProfissaoMae(String profissaoMae) {
		this.profissaoMae = profissaoMae;
	}

	public NecessidadeEspecial getNecessidadeEspecial() {
		return necessidadeEspecial;
	}

	public void setNecessidadeEspecial(NecessidadeEspecial necessidadeEspecial) {
		this.necessidadeEspecial = necessidadeEspecial;
	}

	public boolean isAtestadoMedico() {
		return AtestadoMedico;
	}

	public void setAtestadoMedico(boolean atestadoMedico) {
		AtestadoMedico = atestadoMedico;
	}

	public boolean isCopiaDocumentoIdentificacao() {
		return copiaDocumentoIdentificacao;
	}

	public void setCopiaDocumentoIdentificacao(boolean copiaDocumentoIdentificacao) {
		this.copiaDocumentoIdentificacao = copiaDocumentoIdentificacao;
	}

	public boolean isCopiaCertificado() {
		return CopiaCertificado;
	}

	public void setCopiaCertificado(boolean copiaCertificado) {
		CopiaCertificado = copiaCertificado;
	}

	public boolean isFotografias() {
		return fotografias;
	}

	public void setFotografias(boolean fotografias) {
		this.fotografias = fotografias;
	}

	public boolean isCopiaDocumentoMilitar() {
		return copiaDocumentoMilitar;
	}

	public void setCopiaDocumentoMilitar(boolean copiaDocumentoMilitar) {
		this.copiaDocumentoMilitar = copiaDocumentoMilitar;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public boolean isInactivo() {
		return inactivo;
	}

	public void setInactivo(boolean inactivo) {
		this.inactivo = inactivo;
	}

	public Date getDataInativo() {
		return dataInativo;
	}

	public void setDataInativo(Date dataInativo) {
		this.dataInativo = dataInativo;
	}

	public Date getDataCandidatura() {
		return dataCandidatura;
	}

	public void setDataCandidatura(Date dataCandidatura) {
		this.dataCandidatura = dataCandidatura;
	}

	public Date getDataPrimeiraMatricula() {
		return dataPrimeiraMatricula;
	}

	public void setDataPrimeiraMatricula(Date dataPrimeiraMatricula) {
		this.dataPrimeiraMatricula = dataPrimeiraMatricula;
	}

	public boolean isCursoFinalizado() {
		return cursoFinalizado;
	}

	public void setCursoFinalizado(boolean cursoFinalizado) {
		this.cursoFinalizado = cursoFinalizado;
	}

	public Date getDataFimDoCurso() {
		return dataFimDoCurso;
	}

	public void setDataFimDoCurso(Date dataFimDoCurso) {
		this.dataFimDoCurso = dataFimDoCurso;
	}

	public double getMediaFinal() {
		return mediaFinal;
	}

	public void setMediaFinal(double mediaFinal) {
		this.mediaFinal = mediaFinal;
	}

	public Integer getAnoFimDoCurso() {
		return anoFimDoCurso;
	}

	public void setAnoFimDoCurso(Integer anoFimDoCurso) {
		this.anoFimDoCurso = anoFimDoCurso;
	}

	public String getAnoFimDoCursoDescricao() {
		return anoFimDoCursoDescricao;
	}

	public void setAnoFimDoCursoDescricao(String anoFimDoCursoDescricao) {
		this.anoFimDoCursoDescricao = anoFimDoCursoDescricao;
	}

	public boolean isAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

	public Date getDataAnulado() {
		return dataAnulado;
	}

	public void setDataAnulado(Date dataAnulado) {
		this.dataAnulado = dataAnulado;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean isContencioso() {
		return contencioso;
	}

	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}

	public String getReferenciaDiploma() {
		return referenciaDiploma;
	}

	public void setReferenciaDiploma(String referenciaDiploma) {
		this.referenciaDiploma = referenciaDiploma;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public Integer getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(Integer naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	public String getClassificacaoMestrado() {
		return ClassificacaoMestrado;
	}

	public void setClassificacaoMestrado(String classificacaoMestrado) {
		ClassificacaoMestrado = classificacaoMestrado;
	}

	public String getTemaDissertacao() {
		return temaDissertacao;
	}

	public void setTemaDissertacao(String temaDissertacao) {
		this.temaDissertacao = temaDissertacao;
	}

	public String getNomeOrientadorDissertacao() {
		return nomeOrientadorDissertacao;
	}

	public void setNomeOrientadorDissertacao(String nomeOrientadorDissertacao) {
		this.nomeOrientadorDissertacao = nomeOrientadorDissertacao;
	}
	*/
}
