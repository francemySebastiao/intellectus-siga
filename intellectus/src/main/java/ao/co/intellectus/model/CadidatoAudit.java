package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import ao.co.intellectus.model.enumeracao.TipoCandidatura;

@Entity
@Table(name = "t_candidato_audit")
public class CadidatoAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer numeroCandidato;
	@NotNull
	private String nome;
	private String exame;
	@Column(name = "nota_exame", nullable = true)
	private Double notaExame;
	private String telefone;
	private String telefone1;
	private String telefone2;
	private String email;
	// DADOS PESSOAIS
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	@Column(nullable = true)
	private Boolean seriado;
	@Column(nullable = true)
	private Boolean certificado;
	@Temporal(TemporalType.DATE)
	private Date dataCandidatura;
	private Boolean fotografia;
	@NotNull
	private String nomeDoPai;
	@NotNull
	private String nomeDaMae;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	@Enumerated(EnumType.STRING)
	private Documento documentoIdentificacao;
	// @NotNull
	private String numeroDocumento;
	private Integer codigoAluno;
//DADOS DO LOCAL DE NASCIMENTO
	@ManyToOne
	@JoinColumn(name = "codigo_comuna")
	private Comuna comuna;
	@ManyToOne
	@JoinColumn(name = "codigo_pais_nacionalidade")
	private Pais nacionalidade;
	@ManyToOne
	@JoinColumn(name = "codigo_provincia_nacimento")
	private Provincia provincia;
	@ManyToOne
	@JoinColumn(name = "codigo_municipio_nascimento")
	private Municipio municipio;
	private String bairro;
	private String morada;
	private String profissao;
//DADOS DE ENDEREÇO
	@ManyToOne
	@JoinColumn(name = "codigo_comuna_residencia")
	private Comuna comunaResidencia;
	@ManyToOne
	@JoinColumn(name = "codigo_provincia_residencia")
	private Provincia provinciaResidencia;
	@ManyToOne
	@JoinColumn(name = "codigo_municipio_residencia")
	private Municipio municipioResidencia;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_curso", nullable = true)
	private Curso curso;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_tipo_escola_ensino_medio")
	private CursoEnsinoMedio tipoEscolaEnsinoMedio;
	private Boolean tipoDeCandidatura;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_instituicao")
	private Instituicao instituicao;
//OUTROS DADOS
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emissao_documento")
	private Date dataEmissaoDocumento;
	@Column(name = "arquivo_identificacao")
	private String arquivoIdentificacao;
	private Boolean cursoAcesso;
	@Enumerated(EnumType.STRING)
	private Turno turno;
	@Enumerated(EnumType.STRING)
	private NecessidadeEspecial necessidadeEducacaoEspecial;
	private String escolaEnsinoMedio;
	@Column(name = "nota_final", nullable = true)
	private Integer notaFinal;
	private String cursoEnsinoMedio;
	@Column(nullable = true)
	private Boolean trabalhador;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_criou", nullable = true)
	private Usuario usuario;
	@Column(nullable = true)
	private Boolean candidatoLicenciado;
	@Column(nullable = true)
	private Boolean mensagemEnviada;
	@Column(nullable = true)
	private Boolean emailEnviado;
	@Column(nullable = true)
	private Boolean inscricaoOnline;
	private String nif;
	@Enumerated(EnumType.STRING)
	private TipoCandidatura tipoCandidatura;
	@Enumerated(EnumType.STRING)
	private Grau grau;
	@Column(name="NUMERO_DE_ALUNO")
	private Integer numeroAluno;
	@JoinColumn(name = "codigo_usuario_alterou_nota_final", nullable = true)
	private Integer codigoUsuarioAlterouNotaFinal;
	
	public Integer getCodigoUsuarioAlterouNotaFinal() {
		return codigoUsuarioAlterouNotaFinal;
	}

	public void setCodigoUsuarioAlterouNotaFinal(Integer codigoUsuarioAlterouNotaFinal) {
		this.codigoUsuarioAlterouNotaFinal = codigoUsuarioAlterouNotaFinal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getExame() {
		return exame;
	}

	public void setExame(String exame) {
		this.exame = exame;
	}

	public Double getNotaExame() {
		return notaExame;
	}

	public void setNotaExame(Double notaExame) {
		this.notaExame = notaExame;
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

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
    
	public Integer getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(Integer notaFinal) {
		this.notaFinal = notaFinal;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Boolean isSeriado() {
		return seriado;
	}

	public void setSeriado(Boolean seriado) {
		this.seriado = seriado;
	}

	public Boolean isCertificado() {
		return certificado;
	}

	public void setCertificado(Boolean certificado) {
		this.certificado = certificado;
	}

	public Date getDataCandidatura() {
		return dataCandidatura;
	}

	public void setDataCandidatura(Date dataCandidatura) {
		this.dataCandidatura = dataCandidatura;
	}

	public Boolean isFotografia() {
		return fotografia;
	}

	public void setFotografia(Boolean fotografia) {
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Documento getDocumentoIdentificacao() {
		return documentoIdentificacao;
	}

	public void setDocumentoIdentificacao(Documento documentoIdentificacao) {
		this.documentoIdentificacao = documentoIdentificacao;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Integer getCodigoAluno() {
		return codigoAluno;
	}

	public void setCodigoAluno(Integer codigoAluno) {
		this.codigoAluno = codigoAluno;
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Boolean isTipoDeCandidatura() {
		return tipoDeCandidatura;
	}

	public void setTipoDeCandidatura(Boolean tipoDeCandidatura) {
		this.tipoDeCandidatura = tipoDeCandidatura;
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

	public Boolean isCursoAcesso() {
		return cursoAcesso;
	}

	public void setCursoAcesso(Boolean cursoAcesso) {
		this.cursoAcesso = cursoAcesso;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public NecessidadeEspecial getNecessidadeEducacaoEspecial() {
		return necessidadeEducacaoEspecial;
	}

	public void setNecessidadeEducacaoEspecial(NecessidadeEspecial necessidadeEducacaoEspecial) {
		this.necessidadeEducacaoEspecial = necessidadeEducacaoEspecial;
	}

	public String getEscolaEnsinoMedio() {
		return escolaEnsinoMedio;
	}

	public void setEscolaEnsinoMedio(String escolaEnsinoMedio) {
		this.escolaEnsinoMedio = escolaEnsinoMedio;
	}

	public Boolean isTrabalhador() {
		return trabalhador;
	}

	public void setTrabalhador(Boolean trabalhador) {
		this.trabalhador = trabalhador;
	}

	public Integer getNumeroCandidato() {
		return numeroCandidato;
	}

	public void setNumeroCandidato(Integer numeroCandidato) {
		this.numeroCandidato = numeroCandidato;
	}

	public CursoEnsinoMedio getTipoEscolaEnsinoMedio() {
		return tipoEscolaEnsinoMedio;
	}

	public void setTipoEscolaEnsinoMedio(CursoEnsinoMedio tipoEscolaEnsinoMedio) {
		this.tipoEscolaEnsinoMedio = tipoEscolaEnsinoMedio;
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

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Comuna getComunaResidencia() {
		return comunaResidencia;
	}

	public void setComunaResidencia(Comuna comunaResidencia) {
		this.comunaResidencia = comunaResidencia;
	}

	public String getCursoEnsinoMedio() {
		return cursoEnsinoMedio;
	}

	public void setCursoEnsinoMedio(String cursoEnsinoMedio) {
		this.cursoEnsinoMedio = cursoEnsinoMedio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean isCandidatoLicenciado() {
		return candidatoLicenciado;
	}

	public void setCandidatoLicenciado(Boolean candidatoLicenciado) {
		this.candidatoLicenciado = candidatoLicenciado;
	}

	public Boolean isMensagemEnviada() {
		return mensagemEnviada;
	}

	public void setMensagemEnviada(Boolean mensagemEnviada) {
		this.mensagemEnviada = mensagemEnviada;
	}

	public Boolean isInscricaoOnline() {
		return inscricaoOnline;
	}

	public void setInscricaoOnline(Boolean inscricaoOnline) {
		this.inscricaoOnline = inscricaoOnline;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public Boolean isEmailEnviado() {
		return emailEnviado;
	}

	public void setEmailEnviado(Boolean emailEnviado) {
		this.emailEnviado = emailEnviado;
	}

	public TipoCandidatura getTipoCandidatura() {
		return tipoCandidatura;
	}

	public void setTipoCandidatura(TipoCandidatura tipoCandidatura) {
		this.tipoCandidatura = tipoCandidatura;
	}

	public Comuna getComuna() {
		return comuna;
	}

	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}

	public Grau getGrau() {
		return grau;
	}

	public void setGrau(Grau grau) {
		this.grau = grau;
	}

	public Integer getNumeroAluno() {
		return numeroAluno;
	}

	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
	}

	public Boolean getSeriado() {
		return seriado;
	}

	public Boolean getCertificado() {
		return certificado;
	}

	public Boolean getFotografia() {
		return fotografia;
	}

	public Boolean getTipoDeCandidatura() {
		return tipoDeCandidatura;
	}

	public Boolean getCursoAcesso() {
		return cursoAcesso;
	}

	public Boolean getTrabalhador() {
		return trabalhador;
	}

	public Boolean getCandidatoLicenciado() {
		return candidatoLicenciado;
	}

	public Boolean getMensagemEnviada() {
		return mensagemEnviada;
	}

	public Boolean getEmailEnviado() {
		return emailEnviado;
	}

	public Boolean getInscricaoOnline() {
		return inscricaoOnline;
	}
}
