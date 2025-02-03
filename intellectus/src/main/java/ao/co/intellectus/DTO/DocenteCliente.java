package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.Documento;
import ao.co.intellectus.model.EstadoCivil;
import ao.co.intellectus.model.Prefixo;
import ao.co.intellectus.model.Sexo;

public class DocenteCliente {
	private Integer id;
	private String nome;
	@Temporal(TemporalType.DATE)
	private Date dataDeNascimento;
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_documento_identificacao")
	private Documento tipoDocumentoIdentificacao;
	@Column(name="numero_documento_identificacao")
	private String numeroDocumentoIdentificacao;
//	@Column(length=3000)
//	private String biometria;
	private boolean atestadoMedico; 
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
	private Date dataFimContrato;
	             //dataFimContrato
	@Temporal(TemporalType.DATE)
	private Date dataUltimaRenovacao;
	@Column(name="data_rescisao_contrato")
	@Temporal(TemporalType.DATE)
	private Date dataRescisaoContrato;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	private boolean cv;
	private boolean certificado;
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
    private Integer numeroFilhos;
    private boolean descontaFaltas;
    private String arquivoDocumentoIdentificacao;
    @Enumerated(EnumType.STRING) 
    private EstadoCivil estadoCivil;
    //Tabelas de ligação.
	private Integer moedaRemuneracao;
	private Integer paisNacionalidade;	
	private Integer tipoContratoLaboral;
    private Integer provinciaResidencia;
    private Integer instituicao;
    private Integer municipioResidencia;
    private String tipoVisto;
    private Integer numeroVisto;
    private String grauAcademico;
    private String areaFormacao;
    private boolean inarees;
    private boolean registroCriminal;
    private boolean atestadoResidencia;
    
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
	public String getNumeroDocumentoIdentificacao() {
		return numeroDocumentoIdentificacao;
	}
	public void setNumeroDocumentoIdentificacao(String numeroDocumentoIdentificacao) {
		this.numeroDocumentoIdentificacao = numeroDocumentoIdentificacao;
	}
//	public String getBiometria() {
//		return biometria;
//	}
//	public void setBiometria(String biometria) {
//		this.biometria = biometria;
//	}
	
	public boolean isAtestadoMedico() {
		return atestadoMedico;
	}
	public void setAtestadoMedico(boolean atestadoMedico) {
		this.atestadoMedico = atestadoMedico;
	}
	public Integer getNumeroVisto() {
		return numeroVisto;
	}
	public void setNumeroVisto(Integer numeroVisto) {
		this.numeroVisto = numeroVisto;
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
	public boolean isInarees() {
		return inarees;
	}
	public void setInarees(boolean inarees) {
		this.inarees = inarees;
	}
	public boolean isAtestadoResidencia() {
		return atestadoResidencia;
	}
	public void setAtestadoResidencia(boolean atestadoResidencia) {
		this.atestadoResidencia = atestadoResidencia;
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
	public double getPercentagemIrt() {
		return percentagemIrt;
	}
	public void setPercentagemIrt(double percentagemIrt) {
		this.percentagemIrt = percentagemIrt;
	}
	public boolean isPagaInss() {
		return pagaInss;
	}
	public void setPagaInss(boolean pagaInss) {
		this.pagaInss = pagaInss;
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
	public Date getDataFimContrato() {
		return dataFimContrato;
	}
	public void setDataFimContrato(Date dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
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
	public Integer getTeseQuantidade() {
		return teseQuantidade;
	}
	public void setTeseQuantidade(Integer teseQuantidade) {
		this.teseQuantidade = teseQuantidade;
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
	public String getArquivoDocumentoIdentificacao() {
		return arquivoDocumentoIdentificacao;
	}
	public void setArquivoDocumentoIdentificacao(String arquivoDocumentoIdentificacao) {
		this.arquivoDocumentoIdentificacao = arquivoDocumentoIdentificacao;
	}
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public Integer getMoedaRemuneracao() {
		return moedaRemuneracao;
	}
	public void setMoedaRemuneracao(Integer moedaRemuneracao) {
		this.moedaRemuneracao = moedaRemuneracao;
	}
	public Integer getPaisNacionalidade() {
		return paisNacionalidade;
	}
	public void setPaisNacionalidade(Integer paisNacionalidade) {
		this.paisNacionalidade = paisNacionalidade;
	}
	public Integer getTipoContratoLaboral() {
		return tipoContratoLaboral;
	}
	public void setTipoContratoLaboral(Integer tipoContratoLaboral) {
		this.tipoContratoLaboral = tipoContratoLaboral;
	}
	public Integer getProvinciaResidencia() {
		return provinciaResidencia;
	}
	public void setProvinciaResidencia(Integer provinciaResidencia) {
		this.provinciaResidencia = provinciaResidencia;
	}
	public Integer getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Integer instituicao) {
		this.instituicao = instituicao;
	}
	public Integer getMunicipioResidencia() {
		return municipioResidencia;
	}
	public void setMunicipioResidencia(Integer municipioResidencia) {
		this.municipioResidencia = municipioResidencia;
	}
	public String getTipoVisto() {
		return tipoVisto;
	}
	public void setTipoVisto(String tipoVisto) {
		this.tipoVisto = tipoVisto;
	}
	public boolean isRegistroCriminal() {
		return registroCriminal;
	}
	public void setRegistroCriminal(boolean registroCriminal) {
		this.registroCriminal = registroCriminal;
	}
}