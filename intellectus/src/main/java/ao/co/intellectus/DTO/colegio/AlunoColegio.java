package ao.co.intellectus.DTO.colegio;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Documento;
import ao.co.intellectus.model.NecessidadeEspecial;
import ao.co.intellectus.model.Sexo;

public class AlunoColegio {
	private Integer id;
	private String nome;
	private String numeroDeAluno;
	private String telefone;
	private String telefone1; // novo
	private String telefone2; // novo
	private String email;
	@Enumerated(EnumType.STRING)
	private Documento documentoIdentificacao;
	private String numeroDocumento;
	private Date dataNascimento;
	private Integer nacionalidade;
	private String nomeDoPai;
	private Integer nacionalidadeDoPai; // Adicionado para o CGS
	private String moradaDoPai; // Adicionado para o CGS
	private String telefoneDoPai; // Adicionado para o CGS
	private String telefone1DoPai; // Adicionado para o CGS
	private String emailDoPai; // Adicionado para o CGS
	private String nomeDaMae;
	private Integer nacionalidadeDaMae; // Adicionado para o CGS
	private String moradaDaMae; // Adicionado para o CGS
	private String telefoneDaMae; // Adicionado para o CGS
	private String telefone1DaMae; // Adicionado para o CGS
	private String emailDaMae; // Adicionado para o CGS
	private String nacionalidadeEncarregado; // Adicionado para o CGS
	private String telefoneEncarregado;
	private String telefone1Encarregado; // Adicionado para o CGS
	private String emailEncarregado; // Adicionado para o CGS
	private String numeroDocumentoEncarregado; // Adicionado para o CGS
	private String GrauParentesco; // Adicionado para o CGS
	private String morada;
	private Boolean contencioso;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	// private semestre;
	private Integer provinciaResidencia;
	@Enumerated(EnumType.STRING)
	private NecessidadeEspecial necessidadeEducacaoEspecial;
	private String nomeEncarregado;
	private String moradaDaEncarregado;
	private Boolean copiaDocumentoIdentificacao;
	private Boolean copiaDocumentoMilitar;
	private Boolean fimCurso;
	private Boolean fotografias;
	private Boolean inactivo;
	private Boolean isTrabalhador;
	private Boolean primeiraMatricula;
	private String userCode;
	
	//DADOS DE MATRICULA
	private Integer turma;
	private Integer curso;
	
	//DADOS FINANCEIROS
	

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

	public String getNomeDoPai() {
		return nomeDoPai;
	}

	public void setNomeDoPai(String nomeDoPai) {
		this.nomeDoPai = nomeDoPai;
	}

	public String getMoradaDoPai() {
		return moradaDoPai;
	}

	public void setMoradaDoPai(String moradaDoPai) {
		this.moradaDoPai = moradaDoPai;
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

	public String getEmailDoPai() {
		return emailDoPai;
	}

	public void setEmailDoPai(String emailDoPai) {
		this.emailDoPai = emailDoPai;
	}

	public String getNomeDaMae() {
		return nomeDaMae;
	}

	public void setNomeDaMae(String nomeDaMae) {
		this.nomeDaMae = nomeDaMae;
	}

	public Integer getNacionalidadeDoPai() {
		return nacionalidadeDoPai;
	}

	public void setNacionalidadeDoPai(Integer nacionalidadeDoPai) {
		this.nacionalidadeDoPai = nacionalidadeDoPai;
	}

	public Integer getNacionalidadeDaMae() {
		return nacionalidadeDaMae;
	}

	public void setNacionalidadeDaMae(Integer nacionalidadeDaMae) {
		this.nacionalidadeDaMae = nacionalidadeDaMae;
	}

	public String getMoradaDaMae() {
		return moradaDaMae;
	}

	public void setMoradaDaMae(String moradaDaMae) {
		this.moradaDaMae = moradaDaMae;
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

	public String getEmailDaMae() {
		return emailDaMae;
	}

	public void setEmailDaMae(String emailDaMae) {
		this.emailDaMae = emailDaMae;
	}

	public String getNacionalidadeEncarregado() {
		return nacionalidadeEncarregado;
	}

	public void setNacionalidadeEncarregado(String nacionalidadeEncarregado) {
		this.nacionalidadeEncarregado = nacionalidadeEncarregado;
	}

	public String getTelefoneEncarregado() {
		return telefoneEncarregado;
	}

	public void setTelefoneEncarregado(String telefoneEncarregado) {
		this.telefoneEncarregado = telefoneEncarregado;
	}

	public String getTelefone1Encarregado() {
		return telefone1Encarregado;
	}

	public void setTelefone1Encarregado(String telefone1Encarregado) {
		this.telefone1Encarregado = telefone1Encarregado;
	}

	public String getEmailEncarregado() {
		return emailEncarregado;
	}

	public void setEmailEncarregado(String emailEncarregado) {
		this.emailEncarregado = emailEncarregado;
	}

	public String getNumeroDocumentoEncarregado() {
		return numeroDocumentoEncarregado;
	}

	public void setNumeroDocumentoEncarregado(String numeroDocumentoEncarregado) {
		this.numeroDocumentoEncarregado = numeroDocumentoEncarregado;
	}

	public String getGrauParentesco() {
		return GrauParentesco;
	}

	public void setGrauParentesco(String grauParentesco) {
		GrauParentesco = grauParentesco;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public Boolean getContencioso() {
		return contencioso;
	}

	public void setContencioso(Boolean contencioso) {
		this.contencioso = contencioso;
	}

	public Integer getProvinciaResidencia() {
		return provinciaResidencia;
	}

	public void setProvinciaResidencia(Integer provinciaResidencia) {
		this.provinciaResidencia = provinciaResidencia;
	}

	public NecessidadeEspecial getNecessidadeEducacaoEspecial() {
		return necessidadeEducacaoEspecial;
	}

	public void setNecessidadeEducacaoEspecial(NecessidadeEspecial necessidadeEducacaoEspecial) {
		this.necessidadeEducacaoEspecial = necessidadeEducacaoEspecial;
	}

	public String getNomeEncarregado() {
		return nomeEncarregado;
	}

	public void setNomeEncarregado(String nomeEncarregado) {
		this.nomeEncarregado = nomeEncarregado;
	}

	public String getMoradaDaEncarregado() {
		return moradaDaEncarregado;
	}

	public void setMoradaDaEncarregado(String moradaDaEncarregado) {
		this.moradaDaEncarregado = moradaDaEncarregado;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Boolean getCopiaDocumentoIdentificacao() {
		return copiaDocumentoIdentificacao;
	}

	public void setCopiaDocumentoIdentificacao(Boolean copiaDocumentoIdentificacao) {
		this.copiaDocumentoIdentificacao = copiaDocumentoIdentificacao;
	}

	public Boolean getCopiaDocumentoMilitar() {
		return copiaDocumentoMilitar;
	}

	public void setCopiaDocumentoMilitar(Boolean copiaDocumentoMilitar) {
		this.copiaDocumentoMilitar = copiaDocumentoMilitar;
	}

	public Boolean getFimCurso() {
		return fimCurso;
	}

	public void setFimCurso(Boolean fimCurso) {
		this.fimCurso = fimCurso;
	}

	public Boolean getFotografias() {
		return fotografias;
	}

	public void setFotografias(Boolean fotografias) {
		this.fotografias = fotografias;
	}

	public Boolean getInactivo() {
		return inactivo;
	}

	public void setInactivo(Boolean inactivo) {
		this.inactivo = inactivo;
	}

	public Boolean getIsTrabalhador() {
		return isTrabalhador;
	}

	public void setIsTrabalhador(Boolean isTrabalhador) {
		this.isTrabalhador = isTrabalhador;
	}

	public Boolean getPrimeiraMatricula() {
		return primeiraMatricula;
	}

	public void setPrimeiraMatricula(Boolean primeiraMatricula) {
		this.primeiraMatricula = primeiraMatricula;
	}

	public String getNumeroDeAluno() {
		return numeroDeAluno;
	}

	public void setNumeroDeAluno(String numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}

	public Integer getTurma() {
		return turma;
	}

	public void setTurma(Integer turma) {
		this.turma = turma;
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
	
}
