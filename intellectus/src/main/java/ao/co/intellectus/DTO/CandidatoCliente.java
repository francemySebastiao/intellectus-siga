package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.Sexo;
import ao.co.intellectus.model.Turno;

public class CandidatoCliente {
	private Integer id;
	private String nome;
	private int cursoId;
	private String exame;
	private String bilheteIdentidade;	
	private String semestre;
	private int anoLectivo;	
	private boolean AtestadoMedico;
	private boolean nif;	
	private boolean certificado;
	private String documentoIdentificacao;
	private Sexo sexo;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	private String nomeDoPai;
	private String nomeDaMae;
	private String nacionalidade;
	private boolean fotografia;
	private String provincia;
	private String municipio;
	private String bairro;
	private String rua;
	private String email;
	private String telefone;
	private boolean seriado;
	private Double notaExame;
	@Temporal(TemporalType.DATE)
	private Date dataCandidatura;
	private boolean tipoDeCandidatura;
	private boolean cursoAcesso;
	@Enumerated(EnumType.ORDINAL)
	private Turno turno;

	public CandidatoCliente(){
		
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
	
	public int getCursoId() {
		return cursoId;
	}
	public void setCursoId(int cursoId) {
		this.cursoId = cursoId;
	}
	
	public String getExame() {
		return exame;
	}

	public void setExame(String exame) {
		this.exame = exame;
	}

	public String getBilheteIdentidade() {
		return bilheteIdentidade;
	}
	public void setBilheteIdentidade(String bilheteIdentidade) {
		this.bilheteIdentidade = bilheteIdentidade;
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
	public boolean isCertificado() {
		return certificado;
	}
	public void setCertificado(boolean certificado) {
		this.certificado = certificado;
	}
	
	public String getDocumentoIdentificacao() {
		return documentoIdentificacao;
	}

	public void setDocumentoIdentificacao(String documentoIdentificacao) {
		this.documentoIdentificacao = documentoIdentificacao;
	}

	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public boolean isFotografia() {
		return fotografia;
	}
	public void setFotografia(boolean fotografia) {
		this.fotografia = fotografia;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
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
	public boolean isSeriado() {
		return seriado;
	}
	public void setSeriado(boolean seriado) {
		this.seriado = seriado;
	}
	
	public Double getNotaExame() {
		return notaExame;
	}
	public void setNotaExame(Double notaExame) {
		this.notaExame = notaExame;
	}
	public Date getDataCandidatura() {
		return dataCandidatura;
	}
	public void setDataCandidatura(Date dataCandidatura) {
		this.dataCandidatura = dataCandidatura;
	}

	public boolean isTipoDeCandidatura() {
		return tipoDeCandidatura;
	}

	public void setTipoDeCandidatura(boolean tipoDeCandidatura) {
		this.tipoDeCandidatura = tipoDeCandidatura;
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
}
