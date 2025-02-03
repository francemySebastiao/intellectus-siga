package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ao.co.intellectus.model.enumeracao.TipoCandidatura;

@Entity
@Table(name = "t_aluno")
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	// @Column(unique=true)
	private String numeroDeAluno;
	private String numeroMecanografico;
//1. DADOS PESSOAIS ---------------------------------->   OK
	private String nome;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_curso", nullable = true)
	private Curso curso;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
//2. DADOS DE NASCIMENTO------------------------------>   OK
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	@ManyToOne
	@JoinColumn(name = "codigo_pais_nacionalidade")
	private Pais nacionalidade;
	@ManyToOne
	@JoinColumn(name = "codigo_provincia_nascimento")
	private Provincia provincia; // novo
	@ManyToOne
	@JoinColumn(name = "codigo_municipio_nascimento") // novo
	private Municipio municipio;
//3. DADOS DE CONTACTOS------------------------------->   OK
	private String morada;
	private String bairro;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_pais_residencia", nullable = true)
	private Pais paisDeResidencia;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_provincia_residencia", nullable = true)
	private Provincia provinciaResidencia;
	@ManyToOne
	@JoinColumn(name = "codigo_municio_residencia")
	private Municipio municipioResidencia;
	private String email;
	private String emailInstitucional;
	private String telefone;
	private String telefone1;
	private String telefone2;
	private String telefoneOnline;
	private Boolean isTrabalhador;
	private String profissao;
	private String empresa;
	@Enumerated(EnumType.STRING)
	private TipoEmpresa tipoEmpresa;
//4. DADOS DE IDENTIFICAÇÃO-------------------------->   OK
	@Column(name = "documento_indentificacao")
	@Enumerated(EnumType.STRING)
	private Documento documentoIdentificacao;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emissao_identidade")
	private Date dataEmissaoDocumento;
	@Column(name = "numero_documento_identificacao")
	private String numeroDocumento;
	@Column(name = "arquivo_identificacao")
	private String arquivoIdentificacao;
	@Column(name = "nome_do_pai")
	private String nomeDoPai;
	@Column(name = "nome_da_mae")
	private String nomeDaMae;
	private String profissaoPai;
	private String profissaoMae;
	@Enumerated(EnumType.STRING)
	private NecessidadeEspecial necessidadeEducacaoEspecial;
//5. DADOS DA SITUAÇÃO ACADEMICA----------------------->  OK
	@Column(name = "atestado_medico", nullable = true)
	private Boolean atestadoMedico;
	private Boolean copiaDocumentoIdentificacao;
	@Column(nullable = true)
	private Boolean CopiaCertificado;
	private Boolean fotografias;
	private Boolean copiaDocumentoMilitar;
	@Column(name = "nif", nullable = true)
	private String nif;
	private Boolean inactivo;
	@Temporal(TemporalType.DATE)
	private Date dataInativo;
	@Temporal(TemporalType.DATE)
	private Date dataCandidatura;
	@Temporal(TemporalType.DATE)
	private Date dataFimDoCurso;
	@Column(nullable = true)
	private Long mediaFinal;
	@ManyToOne
	@JoinColumn(name = "codigo_ano_lectivo_fim_curso")
	private AnoLectivo anoFimDoCurso;
	/*private String classificacaMestrado;
	private String temaDissertacao;
	private String nomeOrientadorDissertacao;*/
	// 6. DADOS COMPLETARES-----------------------------------> OK
	private String observacao;
	private Boolean contencioso;
	private String referenciaDiploma;
	private String diploma;
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	private Boolean fimCurso;
	@ManyToOne
	@JoinColumn(name = "codigo_instituicao")
	private Instituicao instituicao;
	@ManyToOne
	@JoinColumn(name = "codigo_tipo_bolsa")
	private TipoBolsa tipoBolsa;
	private String entidadePagadora;
	@Enumerated(EnumType.STRING)
	private TipoCandidatura tipoCandidatura; // TEM QUE SE CRIAR UM SCRIPT PARA ALTERAR O FLUXO DE DADOS DESTE CAMPO
	@Column(nullable = false)
	private Boolean primeiraMatricula;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Transient
	private byte[] foto;
	@Temporal(TemporalType.DATE)
	private Date dataRegistroDiploma;

	@Column(nullable = true)
	private Boolean inscrito;
	@Enumerated(EnumType.STRING)
	@Transient
	private Permissao permissao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaModificacao;

	// AUDITORIA
	@ManyToOne
	@JoinColumn(name = "usuario_ultima_modificacao")
	private Usuario usuario;

	// Dados do Candidato
	private Boolean cursoAcesso;

	// Dados do Colegéio
	private String grauParentesco;
	private String numeroDocumentoEncarregado;
	@ManyToOne
	@JoinColumn(name = "codigo_nacionalidadeDoPai")
	private Pais nacionalidadeDoPai;
	@ManyToOne
	@JoinColumn(name = "codigo_nacionalidadeDaMae")
	private Pais nacionalidadeDaMae;
	private String nacionalidadeEncarregado;
	private String moradaDoPai;
	private String moradaDaMae;
	private String moradaDaEncarregado;
	private String nomeEncarregado;
	private String telefoneDoPai;
	private String telefone1DoPai;
	private String telefoneDaMae;
	private String telefone1DaMae;
	private String telefone1Encarregado;
	private String telefoneEncarregado;
	private String emailDoPai;
	private String emailDaMae;
	private String emailEncarregado;

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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
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

	public Pais getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(Pais nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
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

	public Pais getPaisDeResidencia() {
		return paisDeResidencia;
	}

	public void setPaisDeResidencia(Pais paisDeResidencia) {
		this.paisDeResidencia = paisDeResidencia;
	}

	public Provincia getProvinciaResidencia() {
		return provinciaResidencia;
	}

	public void setProvinciaResidencia(Provincia provinciaResidencia) {
		this.provinciaResidencia = provinciaResidencia;
	}

	public Municipio getMunicipioResidencia() {
		return municipioResidencia;
	}

	public void setMunicipioResidencia(Municipio municipioResidencia) {
		this.municipioResidencia = municipioResidencia;
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

	public Boolean isTrabalhador() {
		return isTrabalhador;
	}

	public void setTrabalhador(Boolean isTrabalhador) {
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

	public Date getDataEmissaoDocumento() {
		return dataEmissaoDocumento;
	}

	public void setDataEmissaoDocumento(Date dataEmissaoDocumento) {
		this.dataEmissaoDocumento = dataEmissaoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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

	public NecessidadeEspecial getNecessidadeEducacaoEspecial() {
		return necessidadeEducacaoEspecial;
	}

	public void setNecessidadeEducacaoEspecial(NecessidadeEspecial necessidadeEducacaoEspecial) {
		this.necessidadeEducacaoEspecial = necessidadeEducacaoEspecial;
	}

	public Boolean isAtestadoMedico() {
		return atestadoMedico;
	}

	public void setAtestadoMedico(Boolean atestadoMedico) {
		this.atestadoMedico = atestadoMedico;
	}

	public Boolean isCopiaDocumentoIdentificacao() {
		return copiaDocumentoIdentificacao;
	}

	public void setCopiaDocumentoIdentificacao(Boolean copiaDocumentoIdentificacao) {
		this.copiaDocumentoIdentificacao = copiaDocumentoIdentificacao;
	}

	public Boolean isCopiaCertificado() {
		return CopiaCertificado;
	}

	public void setCopiaCertificado(Boolean copiaCertificado) {
		CopiaCertificado = copiaCertificado;
	}

	public Boolean isFotografias() {
		return fotografias;
	}

	public void setFotografias(Boolean fotografias) {
		this.fotografias = fotografias;
	}

	public Boolean isCopiaDocumentoMilitar() {
		return copiaDocumentoMilitar;
	}

	public void setCopiaDocumentoMilitar(Boolean copiaDocumentoMilitar) {
		this.copiaDocumentoMilitar = copiaDocumentoMilitar;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public Boolean isInactivo() {
		return inactivo;
	}

	public void setInactivo(Boolean inactivo) {
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

	public Date getDataFimDoCurso() {
		return dataFimDoCurso;
	}

	public void setDataFimDoCurso(Date dataFimDoCurso) {
		this.dataFimDoCurso = dataFimDoCurso;
	}

	public Long getMediaFinal() {
		return mediaFinal;
	}

	public void setMediaFinal(Long mediaFinal) {
		this.mediaFinal = mediaFinal;
	}

	public AnoLectivo getAnoFimDoCurso() {
		return anoFimDoCurso;
	}

	public void setAnoFimDoCurso(AnoLectivo anoFimDoCurso) {
		this.anoFimDoCurso = anoFimDoCurso;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean isContencioso() {
		return contencioso;
	}

	public void setContencioso(Boolean contencioso) {
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

	public Boolean isFimCurso() {
		return fimCurso;
	}

	public void setFimCurso(Boolean fimCurso) {
		this.fimCurso = fimCurso;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public TipoEmpresa getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public TipoBolsa getTipoBolsa() {
		return tipoBolsa;
	}

	public void setTipoBolsa(TipoBolsa tipoBolsa) {
		this.tipoBolsa = tipoBolsa;
	}

	public String getEntidadePagadora() {
		return entidadePagadora;
	}

	public void setEntidadePagadora(String entidadePagadora) {
		this.entidadePagadora = entidadePagadora;
	}

	public TipoCandidatura getTipoCandidatura() {
		return tipoCandidatura;
	}

	public void setTipoCandidatura(TipoCandidatura tipoCandidatura) {
		this.tipoCandidatura = tipoCandidatura;
	}

	public Boolean isPrimeiraMatricula() {
		return primeiraMatricula;
	}

	public void setPrimeiraMatricula(Boolean primeiraMatricula) {
		this.primeiraMatricula = primeiraMatricula;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Date getDataRegistroDiploma() {
		return dataRegistroDiploma;
	}

	public void setDataRegistroDiploma(Date dataRegistroDiploma) {
		this.dataRegistroDiploma = dataRegistroDiploma;
	}

	public Boolean isInscrito() {
		return inscrito;
	}

	public void setInscrito(Boolean inscrito) {
		this.inscrito = inscrito;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public String getDiploma() {
		return diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

	public Date getUltimaModificacao() {
		return ultimaModificacao;
	}

	public void setUltimaModificacao(Date ultimaModificacao) {
		this.ultimaModificacao = ultimaModificacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getCursoAcesso() {
		return cursoAcesso;
	}

	public void setCursoAcesso(Boolean cursoAcesso) {
		this.cursoAcesso = cursoAcesso;
	}

	public String getGrauParentesco() {
		return grauParentesco;
	}

	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
	}

	public String getNumeroDocumentoEncarregado() {
		return numeroDocumentoEncarregado;
	}

	public void setNumeroDocumentoEncarregado(String numeroDocumentoEncarregado) {
		this.numeroDocumentoEncarregado = numeroDocumentoEncarregado;
	}

	public String getNacionalidadeEncarregado() {
		return nacionalidadeEncarregado;
	}

	public void setNacionalidadeEncarregado(String nacionalidadeEncarregado) {
		this.nacionalidadeEncarregado = nacionalidadeEncarregado;
	}

	public String getMoradaDoPai() {
		return moradaDoPai;
	}

	public void setMoradaDoPai(String moradaDoPai) {
		this.moradaDoPai = moradaDoPai;
	}

	public String getMoradaDaMae() {
		return moradaDaMae;
	}

	public void setMoradaDaMae(String moradaDaMae) {
		this.moradaDaMae = moradaDaMae;
	}

	public String getMoradaDaEncarregado() {
		return moradaDaEncarregado;
	}

	public void setMoradaDaEncarregado(String moradaDaEncarregado) {
		this.moradaDaEncarregado = moradaDaEncarregado;
	}

	public String getNomeEncarregado() {
		return nomeEncarregado;
	}

	public void setNomeEncarregado(String nomeEncarregado) {
		this.nomeEncarregado = nomeEncarregado;
	}

	public String getTelefoneDoPai() {
		return telefoneDoPai;
	}

	public void setTelefoneDoPai(String telefoneDoPai) {
		this.telefoneDoPai = telefoneDoPai;
	}

	public String getTelefone1DoPai() {
		return telefone1DoPai;
	}

	public void setTelefone1DoPai(String telefone1DoPai) {
		this.telefone1DoPai = telefone1DoPai;
	}

	public String getTelefoneDaMae() {
		return telefoneDaMae;
	}

	public void setTelefoneDaMae(String telefoneDaMae) {
		this.telefoneDaMae = telefoneDaMae;
	}

	public String getTelefone1DaMae() {
		return telefone1DaMae;
	}

	public void setTelefone1DaMae(String telefone1DaMae) {
		this.telefone1DaMae = telefone1DaMae;
	}

	public String getTelefone1Encarregado() {
		return telefone1Encarregado;
	}

	public void setTelefone1Encarregado(String telefone1Encarregado) {
		this.telefone1Encarregado = telefone1Encarregado;
	}

	public String getTelefoneEncarregado() {
		return telefoneEncarregado;
	}

	public void setTelefoneEncarregado(String telefoneEncarregado) {
		this.telefoneEncarregado = telefoneEncarregado;
	}

	public String getEmailDoPai() {
		return emailDoPai;
	}

	public void setEmailDoPai(String emailDoPai) {
		this.emailDoPai = emailDoPai;
	}

	public String getEmailDaMae() {
		return emailDaMae;
	}

	public void setEmailDaMae(String emailDaMae) {
		this.emailDaMae = emailDaMae;
	}

	public String getEmailEncarregado() {
		return emailEncarregado;
	}

	public void setEmailEncarregado(String emailEncarregado) {
		this.emailEncarregado = emailEncarregado;
	}

	public Boolean getIsTrabalhador() {
		return isTrabalhador;
	}

	public void setIsTrabalhador(Boolean isTrabalhador) {
		this.isTrabalhador = isTrabalhador;
	}

	public Pais getNacionalidadeDoPai() {
		return nacionalidadeDoPai;
	}

	public void setNacionalidadeDoPai(Pais nacionalidadeDoPai) {
		this.nacionalidadeDoPai = nacionalidadeDoPai;
	}

	public Pais getNacionalidadeDaMae() {
		return nacionalidadeDaMae;
	}

	public void setNacionalidadeDaMae(Pais nacionalidadeDaMae) {
		this.nacionalidadeDaMae = nacionalidadeDaMae;
	}

	public Boolean getAtestadoMedico() {
		return atestadoMedico;
	}

	public Boolean getCopiaDocumentoIdentificacao() {
		return copiaDocumentoIdentificacao;
	}

	public Boolean getCopiaCertificado() {
		return CopiaCertificado;
	}

	public Boolean getFotografias() {
		return fotografias;
	}

	public Boolean getCopiaDocumentoMilitar() {
		return copiaDocumentoMilitar;
	}

	public Boolean getInactivo() {
		return inactivo;
	}

	public Boolean getContencioso() {
		return contencioso;
	}

	public Boolean getFimCurso() {
		return fimCurso;
	}

	public Boolean getPrimeiraMatricula() {
		return primeiraMatricula;
	}

	public Boolean getInscrito() {
		return inscrito;
	}

	public String getTelefoneOnline() {
		return telefoneOnline;
	}

	public void setTelefoneOnline(String telefoneOnline) {
		this.telefoneOnline = telefoneOnline;
	}

	/*
	public String getClassificacaMestrado() {
		return classificacaMestrado;
	}

	public void setClassificacaMestrado(String classificacaMestrado) {
		this.classificacaMestrado = classificacaMestrado;
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
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}