package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_docente")
public class Docente {
	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	@Temporal(TemporalType.DATE)
	private Date dataDeNascimento;
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_documento_identificacao")
	private Documento tipoDocumentoIdentificacao;
	@Column(name="numero_documento_identificacao")
	private String numeroDocumentoIdentificacao;
	@Column(length=3000)
	private String biometria; 
	private boolean estado; 
	private boolean recebeMensal;
	private double valorPorMes;
	private boolean recebePorHora;
	private double valorPorHora;
	private boolean pagaIrt; 
	@Column(name="percentagemIrt")
	private double percentagemIrt;
	private boolean pagaInss;
	@Column(name="percentagem_inss") 
	private double percentagemInss;
	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;
	@Temporal(TemporalType.DATE)
	@Column(name="data_fim_contrato")
	private Date dataFimContrato;
	@Column(name="data_ultima_renovacao")
	@Temporal(TemporalType.DATE)
	private Date dataUltimaRenovacao;
	@Column(name="data_rescisao_contrato")
	@Temporal(TemporalType.DATE)
	private Date dataRescisaoContrato;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	private boolean cv;
	private boolean certificado;
    private String grauAcademico;
    private String areaFormacao;
	@ManyToOne
	@JoinColumn(name="codigo_moeda_remuneracao")
	private Moeda moedaRemuneracao;
	@ManyToOne
	@JoinColumn(name="codigo_pais_nacionalidade")
	private Pais paisNacionalidade;	
	@ManyToOne
	@JoinColumn(name="codigo_tipo_contrato_laboral")
	private TipoContratoLaboral tipoContratoLaboral;
	@Enumerated(EnumType.STRING)
	private Prefixo prefixo;
	private String morada;
	private String localMorada;
	@Temporal(TemporalType.DATE)
	private Date dataEmissaoDocumentoIdentificacao;
	@Temporal(TemporalType.DATE)
	private Date dataValidadeDocumentoIdentificacao;
    private String observacao;
    private boolean publicacao;
    private Integer publicacaoQuantidade;
    private Integer investigacaoQuantidade;
    private Integer teseQuantidade;
    private String telefone;
    private String telefone2;
    private String email;
    @Column(unique= true)
	private String emailInstitucional;
    private Integer numeroFilhos;
    private boolean descontaFaltas;
    private String arquivoDocumentoIdentificacao;
    @ManyToOne
	@JoinColumn(name="codigo_provincia_residencia")
    private Provincia provinciaResidencia;
    @ManyToOne
	@JoinColumn(name="codigo_instituicao")
    private Instituicao instituicao;
    @ManyToOne
	@JoinColumn(name="codigo_municipio_residencia")
    private Municipio municipioResidencia;    
    @Enumerated(EnumType.STRING) 
    private EstadoCivil estadoCivil;
    //NESTA TELA EFETUAMOS A MAGICA ENTRE O USUARIO E O DOCENTE PARA PERMITIR QUE UM USUARIO QUE VENHA DO PORTAL SEJA ENCONTRADO SEU HORARIO
    @ManyToOne
	@JoinColumn(name="codigo_usuario_docente")
    private Usuario usuarioDocente;
 
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

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public Documento getTipoDocumentoIdentificacao() {
		return tipoDocumentoIdentificacao;
	}

	public void setTipoDocumentoIdentificacao(Documento tipoDocumentoIdentificacao) {
		this.tipoDocumentoIdentificacao = tipoDocumentoIdentificacao;
	}

	public String getBiometria() {
		return biometria;
	}

	public void setBiometria(String biometria) {
		this.biometria = biometria;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public boolean isRecebeMensal() {
		return recebeMensal;
	}

	public void setRecebeMensal(boolean recebeMensal) {
		this.recebeMensal = recebeMensal;
	}

	public double getValorPorMes() {
		return valorPorMes;
	}

	public void setValorPorMes(double valorPorMes) {
		this.valorPorMes = valorPorMes;
	}

	public boolean isRecebePorHora() {
		return recebePorHora;
	}

	public void setRecebePorHora(boolean recebePorHora) {
		this.recebePorHora = recebePorHora;
	}

	public double getValorPorHora() {
		return valorPorHora;
	}

	public void setValorPorHora(double valorPorHora) {
		this.valorPorHora = valorPorHora;
	}

	public boolean isPagaIrt() {
		return pagaIrt;
	}

	public void setPagaIrt(boolean pagaIrt) {
		this.pagaIrt = pagaIrt;
	}
	public boolean isPagaInss() {
		return pagaInss;
	}

	public void setPagaInss(boolean pagaInss) {
		this.pagaInss = pagaInss;
	}
	public double getPercentagemIrt() {
		return percentagemIrt;
	}

	public void setPercentagemIrt(double percentagemIrt) {
		this.percentagemIrt = percentagemIrt;
	}

	public double getPercentagemInss() {
		return percentagemInss;
	}

	public void setPercentagemInss(double percentagemInss) {
		this.percentagemInss = percentagemInss;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public boolean isCv() {
		return cv;
	}

	public void setCv(boolean cv) {
		this.cv = cv;
	}

	public boolean isCertificado() {
		return certificado;
	}

	public void setCertificado(boolean certificado) {
		this.certificado = certificado;
	}
	public Moeda getMoedaRemuneracao() {
		return moedaRemuneracao;
	}

	public void setMoedaRemuneracao(Moeda moedaRemuneracao) {
		this.moedaRemuneracao = moedaRemuneracao;
	}

	public Pais getPaisNacionalidade() {
		return paisNacionalidade;
	}

	public void setPaisNacionalidade(Pais paisNacionalidade) {
		this.paisNacionalidade = paisNacionalidade;
	}
	public Date getDataUltimaRenovacao() {
		return dataUltimaRenovacao;
	}

	public void setDataUltimaRenovacao(Date dataUltimaRenovacao) {
		this.dataUltimaRenovacao = dataUltimaRenovacao;
	}

	public Date getDataRescisaoContrato() {
		return dataRescisaoContrato;
	}

	public void setDataRescisaoContrato(Date dataRescisaoContrato) {
		this.dataRescisaoContrato = dataRescisaoContrato;
	}

	public String getNumeroDocumentoIdentificacao() {
		return numeroDocumentoIdentificacao;
	}

	public void setNumeroDocumentoIdentificacao(String numeroDocumentoIdentificacao) {
		this.numeroDocumentoIdentificacao = numeroDocumentoIdentificacao;
	}

	public TipoContratoLaboral getTipoContratoLaboral() {
		return tipoContratoLaboral;
	}

	public void setTipoContratoLaboral(TipoContratoLaboral tipoContratoLaboral) {
		this.tipoContratoLaboral = tipoContratoLaboral;
	}

	public Prefixo getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(Prefixo prefixo) {
		this.prefixo = prefixo;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public String getLocalMorada() {
		return localMorada;
	}

	public void setLocalMorada(String localMorada) {
		this.localMorada = localMorada;
	}

	public Date getDataEmissaoDocumentoIdentificacao() {
		return dataEmissaoDocumentoIdentificacao;
	}

	public void setDataEmissaoDocumentoIdentificacao(Date dataEmissaoDocumentoIdentificacao) {
		this.dataEmissaoDocumentoIdentificacao = dataEmissaoDocumentoIdentificacao;
	}

	public Date getDataValidadeDocumentoIdentificacao() {
		return dataValidadeDocumentoIdentificacao;
	}
	public void setDataValidadeDocumentoIdentificacao(Date dataValidadeDocumentoIdentificacao) {
		this.dataValidadeDocumentoIdentificacao = dataValidadeDocumentoIdentificacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean isPublicacao() {
		return publicacao;
	}

	public void setPublicacao(boolean publicacao) {
		this.publicacao = publicacao;
	}

	public Integer getPublicacaoQuantidade() {
		return publicacaoQuantidade;
	}

	public void setPublicacaoQuantidade(Integer publicacaoQuantidade) {
		this.publicacaoQuantidade = publicacaoQuantidade;
	}

	public Integer getInvestigacaoQuantidade() {
		return investigacaoQuantidade;
	}

	public void setInvestigacaoQuantidade(Integer investigacaoQuantidade) {
		this.investigacaoQuantidade = investigacaoQuantidade;
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

	public Integer getNumeroFilhos() {
		return numeroFilhos;
	}

	public void setNumeroFilhos(Integer numeroFilhos) {
		this.numeroFilhos = numeroFilhos;
	}

	public boolean isDescontaFaltas() {
		return descontaFaltas;
	}

	public void setDescontaFaltas(boolean descontaFaltas) {
		this.descontaFaltas = descontaFaltas;
	}
	public Provincia getProvinciaResidencia() {
		return provinciaResidencia;
	}

	public void setProvinciaResidencia(Provincia provinciaResidencia) {
		this.provinciaResidencia = provinciaResidencia;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Municipio getMunicipioResidencia() {
		return municipioResidencia;
	}

	public void setMunicipioResidencia(Municipio municipioResidencia) {
		this.municipioResidencia = municipioResidencia;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Integer getTeseQuantidade() {
		return teseQuantidade;
	}

	public void setTeseQuantidade(Integer teseQuantidade) {
		this.teseQuantidade = teseQuantidade;
	}

	public String getArquivoDocumentoIdentificacao() {
		return arquivoDocumentoIdentificacao;
	}

	public void setArquivoDocumentoIdentificacao(String arquivoDocumentoIdentificacao) {
		this.arquivoDocumentoIdentificacao = arquivoDocumentoIdentificacao;
	}

	public Date getDataFimContrato() {
		return dataFimContrato;
	}

	public void setDataFimContrato(Date dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}

	public Usuario getUsuarioDocente() {
		return usuarioDocente;
	}

	public void setUsuarioDocente(Usuario usuarioDocente) {
		this.usuarioDocente = usuarioDocente;
	}

	public String getGrauAcademico() {
		return grauAcademico;
	}

	public void setGrauAcademico(String grauAcademico) {
		this.grauAcademico = grauAcademico;
	}

	public String getAreaFormacao() {
		return areaFormacao;
	}

	public void setAreaFormacao(String areaFormacao) {
		this.areaFormacao = areaFormacao;
	}
	
}