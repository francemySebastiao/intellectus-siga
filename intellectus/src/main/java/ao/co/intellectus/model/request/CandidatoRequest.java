package ao.co.intellectus.model.request;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import ao.co.intellectus.model.Documento;
import ao.co.intellectus.model.EstadoCivil;
import ao.co.intellectus.model.NecessidadeEspecial;
import ao.co.intellectus.model.Sexo;
import ao.co.intellectus.model.Turno;
import ao.co.intellectus.model.enumeracao.TipoCandidatura;

public class CandidatoRequest {
	private Integer id;
	@NotNull
	private String nome;
	private String exame;
	@Column(name = "nota_exame", nullable = true)
	private Double notaExame;
	@NotNull
	private String telefone;
	private String telefone1;
	private String telefone2;
	//@Email
	private String email;
	// DADOS PESSOAIS
	@Enumerated(EnumType.ORDINAL)
	private Sexo sexo;
	@Enumerated(EnumType.ORDINAL)
	private EstadoCivil estadoCivil;
	@Column(nullable = true)
	private boolean seriado;
	@Column(nullable = true)
	private boolean certificado;
	@Temporal(TemporalType.DATE)
	private Date dataCandidatura;
	private boolean fotografia;
	@NotNull
	private String nomeDoPai;
	@NotNull
	private String nomeDaMae;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nasciemnto")
	private Date dataNascimento;
	@Enumerated(EnumType.STRING)
	private Documento documentoIdentificacao;
	private String numeroDocumento;
	private Integer codigoAluno;
//DADOS DO LOCAL DE NASCIMENTO
	private Integer nacionalidade;
	private Integer provincia;
	private Integer municipio;
	private String bairro;
	private String morada;
	private Integer comuna;
//DADOS DE ENDEREÇO
	private Integer provinciaResidencia;
	private Integer municipioResidencia;
	private Integer comunaResidencia;
	private Integer curso;
	@NotNull
	private Integer anoLectivo;
	private boolean tipoDeCandidatura;
//OUTROS DADOS
	@Temporal(TemporalType.DATE)
	private Date dataEmissaoDocumento;
	@Column(name = "arquivo_identificacao")
	private String arquivoIdentificacao;
	private boolean cursoAcesso;
	@Enumerated(EnumType.ORDINAL)
	private Turno turno;
	private String unidadeOrganica;
	@Enumerated(EnumType.STRING)
	private NecessidadeEspecial necessidadeEducacaoEspecial;
	private String cursoEnsinoMedio;
	private boolean trabalhador;
	private Integer tipoEscolaEnsinoMedio;
	private Integer instituicao;
	private String profissao;
	private String escolaEnsinoMedio;
	// AUDITORIA
	private Integer userCode;
	private String userName;
	private boolean candidatoLicenciado;
	
	@Enumerated(EnumType.STRING)
	private TipoCandidatura tipoCandidatura;
	
	private Boolean renovarEquivalencia;

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

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public boolean isSeriado() {
		return seriado;
	}

	public void setSeriado(boolean seriado) {
		this.seriado = seriado;
	}

	public boolean isCertificado() {
		return certificado;
	}

	public void setCertificado(boolean certificado) {
		this.certificado = certificado;
	}

	public Date getDataCandidatura() {
		return dataCandidatura;
	}

	public void setDataCandidatura(Date dataCandidatura) {
		this.dataCandidatura = dataCandidatura;
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

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public boolean isTipoDeCandidatura() {
		return tipoDeCandidatura;
	}

	public void setTipoDeCandidatura(boolean tipoDeCandidatura) {
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

	public boolean isCursoAcesso() {
		return cursoAcesso;
	}

	public void setCursoAcesso(boolean cursoAcesso) {
		this.cursoAcesso = cursoAcesso;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public String getUnidadeOrganica() {
		return unidadeOrganica;
	}

	public void setUnidadeOrganica(String unidadeOrganica) {
		this.unidadeOrganica = unidadeOrganica;
	}

	public NecessidadeEspecial getNecessidadeEducacaoEspecial() {
		return necessidadeEducacaoEspecial;
	}

	public void setNecessidadeEducacaoEspecial(NecessidadeEspecial necessidadeEducacaoEspecial) {
		this.necessidadeEducacaoEspecial = necessidadeEducacaoEspecial;
	}

	public Integer getTipoEscolaEnsinoMedio() {
		return tipoEscolaEnsinoMedio;
	}

	public void setTipoEscolaEnsinoMedio(Integer tipoEscolaEnsinoMedio) {
		this.tipoEscolaEnsinoMedio = tipoEscolaEnsinoMedio;
	}

	public String getCursoEnsinoMedio() {
		return cursoEnsinoMedio;
	}

	public void setCursoEnsinoMedio(String cursoEnsinoMedio) {
		this.cursoEnsinoMedio = cursoEnsinoMedio;
	}

	public boolean isTrabalhador() {
		return trabalhador;
	}

	public void setTrabalhador(boolean trabalhador) {
		this.trabalhador = trabalhador;
	}

	public Integer getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Integer instituicao) {
		this.instituicao = instituicao;
	}

	public Integer getComunaResidencia() {
		return comunaResidencia;
	}

	public void setComunaResidencia(Integer comunaResidencia) {
		this.comunaResidencia = comunaResidencia;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getEscolaEnsinoMedio() {
		return escolaEnsinoMedio;
	}

	public void setEscolaEnsinoMedio(String escolaEnsinoMedio) {
		this.escolaEnsinoMedio = escolaEnsinoMedio;
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

	public boolean isCandidatoLicenciado() {
		return candidatoLicenciado;
	}

	public void setCandidatoLicenciado(boolean candidatoLicenciado) {
		this.candidatoLicenciado = candidatoLicenciado;
	}

	public Integer getComuna() {
		return comuna;
	}

	public void setComuna(Integer comuna) {
		this.comuna = comuna;
	}

	public TipoCandidatura getTipoCandidatura() {
		return tipoCandidatura;
	}

	public void setTipoCandidatura(TipoCandidatura tipoCandidatura) {
		this.tipoCandidatura = tipoCandidatura;
	}

	public Boolean getRenovarEquivalencia() {
		return renovarEquivalencia;
	}

	public void setRenovarEquivalencia(Boolean renovarEquivalencia) {
		this.renovarEquivalencia = renovarEquivalencia;
	}

}