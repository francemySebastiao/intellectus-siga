package ao.co.intellectus.DTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.Documento;
import ao.co.intellectus.model.EstadoCivil;
import ao.co.intellectus.model.NecessidadeEspecial;
import ao.co.intellectus.model.Sexo;

public class AlunoForSolicitacaoCliente {
	private Integer id;
	private String numeroDeAluno;
//1. DADOS PESSOAIS ---------------------------------->   OK
    private String nome;
	private String curso;
	private Integer cursoId;
    @Enumerated(EnumType.STRING) 
	private Sexo sexo; 
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;    
//2. DADOS DE NASCIMENTO------------------------------>   OK
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Date dataNascimento;
//3. DADOS DE CONTACTOS------------------------------->   OK
	private String municipioResidencia;
	private String email;
	private String telefone;
	private String telefone1;
	private String telefone2; 
//4. DADOS DE IDENTIFICAÇÃO-------------------------->   OK
	@Enumerated(EnumType.STRING)
	private Documento documentoIdentificacao;
	@Temporal(TemporalType.DATE)
	private Date dataEmissaoDocumento;
	private String numeroDocumento;
	private String nomeDoPai;
	private String nomeDaMae;
	private String profissaoPai;
	private String profissaoMae;
	@Enumerated(EnumType.STRING)
	private NecessidadeEspecial necessidadeEducacaoEspecial;
//5. DADOS DA SITUAÇÃO ACADEMICA----------------------->  OK
	private boolean atestadoMedico;
	private boolean copiaDocumentoIdentificacao;
	private boolean CopiaCertificado;
	private boolean fotografias;
	private boolean copiaDocumentoMilitar;	
	private String nif;
	private boolean inactivo;
//6. DADOS COMPLETARES-----------------------------------> OK
	private boolean contencioso;
	private boolean fimCurso;
	@Temporal(TemporalType.DATE)
	private Date dataFimDoCurso;
	private Double mediaFinal;
	private List<InscricaoPorAno> inscricaoPorAno;
	@Temporal(TemporalType.DATE)
	private Date dataRegistroDiploma;
	private String numeroCertificado;
	private String grau;
	
	public String getGrau() {
		return grau;
	}
	public void setGrau(String grau) {
		this.grau = grau;
	}
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
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
	public String getMunicipioResidencia() {
		return municipioResidencia;
	}
	public void setMunicipioResidencia(String municipioResidencia) {
		this.municipioResidencia = municipioResidencia;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public boolean isAtestadoMedico() {
		return atestadoMedico;
	}
	public void setAtestadoMedico(boolean atestadoMedico) {
		this.atestadoMedico = atestadoMedico;
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
	public boolean isContencioso() {
		return contencioso;
	}
	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}
	public boolean isFimCurso() {
		return fimCurso;
	}
	public void setFimCurso(boolean fimCurso) {
		this.fimCurso = fimCurso;
	}
	public List<InscricaoPorAno> getInscricaoPorAno() {
		return inscricaoPorAno;
	}
	public void setInscricaoPorAno(List<InscricaoPorAno> inscricaoPorAno) {
		this.inscricaoPorAno = inscricaoPorAno;
	}
	public Date getDataFimDoCurso() {
		return dataFimDoCurso;
	}
	public void setDataFimDoCurso(Date dataFimDoCurso) {
		this.dataFimDoCurso = dataFimDoCurso;
	}
	public Double getMediaFinal() {
		return mediaFinal;
	}
	public void setMediaFinal(Double mediaFinal) {
		this.mediaFinal = mediaFinal;
	}
	public Date getDataRegistroDiploma() {
		return dataRegistroDiploma;
	}
	public void setDataRegistroDiploma(Date dataRegistroDiploma) {
		this.dataRegistroDiploma = dataRegistroDiploma;
	}
	public String getNumeroCertificado() {
		return numeroCertificado;
	}
	public void setNumeroCertificado(String numeroCertificado) {
		this.numeroCertificado = numeroCertificado;
	}
	public Integer getCursoId() {
		return cursoId;
	}
	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}
}
