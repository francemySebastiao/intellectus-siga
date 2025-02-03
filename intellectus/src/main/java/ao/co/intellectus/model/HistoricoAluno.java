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
@Table(name = "t_historico_aluno")
public class HistoricoAluno {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "ano_curricular")
	private int anoCorricular;
	private Boolean extraordinaria;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInscricao;
	@Temporal(TemporalType.DATE)
	private Date dataAnulacao;
	// private Integer anoLectivo;
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	private Boolean aprovado;
	// AVALIAÇÕES
	@Column(name = "primeira_frequencia", nullable = true)
	private Float avaliacao1;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataPrimeiraFrequencia;
	@ManyToOne
	@JoinColumn(name = "codigo_user_primeira_freq")
	private Usuario usuarioPrimeiraFrequencia;
	@Column(name = "segunda_frequencia", nullable = true)
	private Float avaliacao2;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataSegundaFrequencia;
	@ManyToOne
	@JoinColumn(name = "codigo_user_segunda_freq")
	private Usuario usuarioSegundaFrequencia;
	@Column(name = "terceira_frequencia", nullable = true)
	private Float avaliacao3;
	@Temporal(TemporalType.DATE)
	private Date dataTerceiraFrequencia;
	@ManyToOne
	@JoinColumn(name = "codigo_user_terceira_freq")
	private Usuario usuarioTerceiraFrequencia;
	@Column(name = "quarta_frequencia", nullable = true)
	private Float avaliacao4;
	@Temporal(TemporalType.DATE)
	private Date dataQuartaFrequencia;
	@ManyToOne
	@JoinColumn(name = "codigo_user_quarta_freq")
	private Usuario usuarioQuartaFrequencia;
	@Column(nullable = true)
	private Float notaExame;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataExame;
	@ManyToOne
	@JoinColumn(name = "codigo_user_exame")
	private Usuario usuarioExame;
	@Column(nullable = true)
	private Float notaExameOral;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataExameOral;
	@ManyToOne
	@JoinColumn(name = "codigo_user_exame_oral")
	private Usuario usuarioExameOral;
	@Column(nullable = true)
	private Float notaRecurso;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataRecurso;
	@ManyToOne
	@JoinColumn(name = "codigo_user_recurso")
	private Usuario usuarioRecurso;
	@Column(nullable = true)
	private Float notaRecursoOral;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataNotaRecursoOral;
	@ManyToOne
	@JoinColumn(name = "codigo_user_recurso_oral")
	private Usuario usuarioRecursoOral;
	@Column(nullable = true)
	private Float melhoriaNota;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataMelhoria;
	@ManyToOne
	@JoinColumn(name = "codigo_user_melhoria")
	private Usuario usuarioMelhoria;
	@Column(nullable = true)
	private Float melhoriaNotaOral;
	@ManyToOne
	@JoinColumn(name = "codigo_user_melhoria_oral")
	private Usuario usuarioMelhoriaOral;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataMelhoriaOral;
	//
	@Column(nullable = true)
	private Float projectoNota;
	@ManyToOne
	@JoinColumn(name = "codigo_user_melhoria_projecto")
	private Usuario usuarioNotaProjecto;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataNotaProjecto;
	//
	@ManyToOne
	@JoinColumn(name = "codigo_user_extraordinaria")
	private Usuario usuarioExtraordinaria;
	@Column(nullable = true)
	private Float notaExtraodinaria;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataNotaExtraordinaria;
	//
	@Column(nullable = true)
	private Float notaFinal;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataNotaFinal;
	@Column(nullable = true)
	private Float notaFinalContinua;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataNotaFinalContinua;
	@Column(nullable = true)
	private Float notaEpocaEspecial;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataNotaEpocaEspecial;
	@ManyToOne
	@JoinColumn(name = "codigo_user_especial")
	private Usuario usuarioEspecial;
	@Column(nullable = true)
	private Float notaEpocaEspecialOral;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataNotaEpocaEspecialOral;
	@ManyToOne
	@JoinColumn(name = "codigo_user_especial_oral")
	private Usuario usuarioEspecialOral;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataInscricaoRecurso;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataInscricaoEpocaEspecial;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataInscricaoMelhoria;
	@Column(nullable = true)
	private Float notaPratica;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataNotaPratica;
	@ManyToOne
	@JoinColumn(name = "codigo_user_pretica")
	private Usuario usuarioPratica;
	// TABELAS DE CONEXÃO.
	@ManyToOne
	@JoinColumn(name = "codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	@ManyToOne
	@JoinColumn(name = "codigo_matricula")
	private Matricula matricula;
	@ManyToOne
	@JoinColumn(name = "codigo_disciplina")
	private Disciplina disciplina;
	@ManyToOne
	@JoinColumn(name = "codigo_aluno")
	private Aluno aluno;
	private String numeroDeAluno;
	@ManyToOne
	@JoinColumn(name = "codigo_turma")
	private Turma turma;
	@ManyToOne
	@JoinColumn(name = "codigo_curso")
	private Curso curso;
	@Column(nullable = true)
	private Boolean fechada;
	@Column(nullable = true)
	private Float notaFinalRecurso;
	@Column(nullable = true)
	private Float notaFinalMelhoria;
	@Column(nullable = true)
	private Float notaCursoDeVerao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNotaCursoVerao;
	@ManyToOne
	@JoinColumn(name = "codigo_user_verao")
	private Usuario usuarioCursoVerao;
	@Column(nullable = true)
	private Boolean validada;
	// campos que estavam em falta mais que fazem muita diferença
	@Column(nullable = true)
	private Boolean semFrequencia;
	@Column(nullable = true)
	private Boolean podeMelhoria;
	@Column(nullable = true)
	private Boolean podeEpEsp;
	@Column(nullable = true)
	private Boolean pode2epoca;
	@Column(length = 50)
	private String designacao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataMediaFinal;
	
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_inscreveu")
	private Usuario UsuarioInscreveu;

	@ManyToOne
	@JoinColumn(name = "codigo_usuario_alterou")
	private Usuario UsuarioAlterou;

	@ManyToOne
	@JoinColumn(name = "codigo_usuario_validou")
	private Usuario UsuarioValidou;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataValidacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaModificacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAnoCorricular() {
		return anoCorricular;
	}

	public void setAnoCorricular(int anoCorricular) {
		this.anoCorricular = anoCorricular;
	}

	public Boolean isExtraordinaria() {
		return extraordinaria;
	}

	public void setExtraordinaria(Boolean extraordinaria) {
		this.extraordinaria = extraordinaria;
	}

	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public Date getDataAnulacao() {
		return dataAnulacao;
	}

	public void setDataAnulacao(Date dataAnulacao) {
		this.dataAnulacao = dataAnulacao;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}

	public Float getAvaliacao1() {
		return avaliacao1;
	}

	public void setAvaliacao1(Float avaliacao1) {
		this.avaliacao1 = avaliacao1;
	}

	public Date getDataPrimeiraFrequencia() {
		return dataPrimeiraFrequencia;
	}

	public void setDataPrimeiraFrequencia(Date dataPrimeiraFrequencia) {
		this.dataPrimeiraFrequencia = dataPrimeiraFrequencia;
	}

	public Float getAvaliacao2() {
		return avaliacao2;
	}

	public void setAvaliacao2(Float avaliacao2) {
		this.avaliacao2 = avaliacao2;
	}

	public Date getDataSegundaFrequencia() {
		return dataSegundaFrequencia;
	}

	public void setDataSegundaFrequencia(Date dataSegundaFrequencia) {
		this.dataSegundaFrequencia = dataSegundaFrequencia;
	}

	public Float getAvaliacao3() {
		return avaliacao3;
	}

	public void setAvaliacao3(Float avaliacao3) {
		this.avaliacao3 = avaliacao3;
	}

	public Float getAvaliacao4() {
		return avaliacao4;
	}

	public void setAvaliacao4(Float avaliacao4) {
		this.avaliacao4 = avaliacao4;
	}

	public Float getNotaExame() {
		return notaExame;
	}

	public void setNotaExame(Float notaExame) {
		this.notaExame = notaExame;
	}

	public Date getDataExame() {
		return dataExame;
	}

	public void setDataExame(Date dataExame) {
		this.dataExame = dataExame;
	}

	public Float getNotaExameOral() {
		return notaExameOral;
	}

	public void setNotaExameOral(Float notaExameOral) {
		this.notaExameOral = notaExameOral;
	}

	public Date getDataExameOral() {
		return dataExameOral;
	}

	public void setDataExameOral(Date dataExameOral) {
		this.dataExameOral = dataExameOral;
	}

	public Float getNotaRecurso() {
		return notaRecurso;
	}

	public void setNotaRecurso(Float notaRecurso) {
		this.notaRecurso = notaRecurso;
	}

	public Date getDataRecurso() {
		return dataRecurso;
	}

	public void setDataRecurso(Date dataRecurso) {
		this.dataRecurso = dataRecurso;
	}

	public Float getNotaRecursoOral() {
		return notaRecursoOral;
	}

	public void setNotaRecursoOral(Float notaRecursoOral) {
		this.notaRecursoOral = notaRecursoOral;
	}

	public Date getDataNotaRecursoOral() {
		return dataNotaRecursoOral;
	}

	public void setDataNotaRecursoOral(Date dataNotaRecursoOral) {
		this.dataNotaRecursoOral = dataNotaRecursoOral;
	}

	public Float getMelhoriaNota() {
		return melhoriaNota;
	}

	public void setMelhoriaNota(Float melhoriaNota) {
		this.melhoriaNota = melhoriaNota;
	}

	public Date getDataMelhoria() {
		return dataMelhoria;
	}

	public void setDataMelhoria(Date dataMelhoria) {
		this.dataMelhoria = dataMelhoria;
	}

	public Float getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(Float notaFinal) {
		this.notaFinal = notaFinal;
	}

	public Date getDataNotaFinal() {
		return dataNotaFinal;
	}

	public void setDataNotaFinal(Date dataNotaFinal) {
		this.dataNotaFinal = dataNotaFinal;
	}

	public Float getNotaFinalContinua() {
		return notaFinalContinua;
	}

	public void setNotaFinalContinua(Float notaFinalContinua) {
		this.notaFinalContinua = notaFinalContinua;
	}

	public Date getDataNotaFinalContinua() {
		return dataNotaFinalContinua;
	}

	public void setDataNotaFinalContinua(Date dataNotaFinalContinua) {
		this.dataNotaFinalContinua = dataNotaFinalContinua;
	}

	public Float getNotaEpocaEspecial() {
		return notaEpocaEspecial;
	}

	public void setNotaEpocaEspecial(Float notaEpocaEspecial) {
		this.notaEpocaEspecial = notaEpocaEspecial;
	}

	public Date getDataNotaEpocaEspecial() {
		return dataNotaEpocaEspecial;
	}

	public void setDataNotaEpocaEspecial(Date dataNotaEpocaEspecial) {
		this.dataNotaEpocaEspecial = dataNotaEpocaEspecial;
	}

	public Float getNotaEpocaEspecialOral() {
		return notaEpocaEspecialOral;
	}

	public void setNotaEpocaEspecialOral(Float notaEpocaEspecialOral) {
		this.notaEpocaEspecialOral = notaEpocaEspecialOral;
	}

	public Date getDataNotaEpocaEspecialOral() {
		return dataNotaEpocaEspecialOral;
	}

	public void setDataNotaEpocaEspecialOral(Date dataNotaEpocaEspecialOral) {
		this.dataNotaEpocaEspecialOral = dataNotaEpocaEspecialOral;
	}

	public Date getDataInscricaoRecurso() {
		return dataInscricaoRecurso;
	}

	public void setDataInscricaoRecurso(Date dataInscricaoRecurso) {
		this.dataInscricaoRecurso = dataInscricaoRecurso;
	}

	public Date getDataInscricaoEpocaEspecial() {
		return dataInscricaoEpocaEspecial;
	}

	public void setDataInscricaoEpocaEspecial(Date dataInscricaoEpocaEspecial) {
		this.dataInscricaoEpocaEspecial = dataInscricaoEpocaEspecial;
	}

	public Date getDataInscricaoMelhoria() {
		return dataInscricaoMelhoria;
	}

	public void setDataInscricaoMelhoria(Date dataInscricaoMelhoria) {
		this.dataInscricaoMelhoria = dataInscricaoMelhoria;
	}

	public Float getNotaPratica() {
		return notaPratica;
	}

	public void setNotaPratica(Float notaPratica) {
		this.notaPratica = notaPratica;
	}

	public Date getDataNotaPratica() {
		return dataNotaPratica;
	}

	public void setDataNotaPratica(Date dataNotaPratica) {
		this.dataNotaPratica = dataNotaPratica;
	}

	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getNumeroDeAluno() {
		return numeroDeAluno;
	}

	public void setNumeroDeAluno(String numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Boolean isFechada() {
		return fechada;
	}

	public void setFechada(Boolean fechada) {
		this.fechada = fechada;
	}

	public Float getNotaFinalRecurso() {
		return notaFinalRecurso;
	}

	public void setNotaFinalRecurso(Float notaFinalRecurso) {
		this.notaFinalRecurso = notaFinalRecurso;
	}

	public Float getNotaFinalMelhoria() {
		return notaFinalMelhoria;
	}

	public void setNotaFinalMelhoria(Float notaFinalMelhoria) {
		this.notaFinalMelhoria = notaFinalMelhoria;
	}

	public Boolean isValidada() {
		return validada;
	}

	public void setValidada(Boolean validada) {
		this.validada = validada;
	}

	public Date getDataTerceiraFrequencia() {
		return dataTerceiraFrequencia;
	}

	public void setDataTerceiraFrequencia(Date dataTerceiraFrequencia) {
		this.dataTerceiraFrequencia = dataTerceiraFrequencia;
	}

	public Date getDataQuartaFrequencia() {
		return dataQuartaFrequencia;
	}

	public void setDataQuartaFrequencia(Date dataQuartaFrequencia) {
		this.dataQuartaFrequencia = dataQuartaFrequencia;
	}

	public Boolean isSemFrequencia() {
		return semFrequencia;
	}

	public void setSemFrequencia(Boolean semFrequencia) {
		this.semFrequencia = semFrequencia;
	}

	public Boolean isPodeMelhoria() {
		return podeMelhoria;
	}

	public void setPodeMelhoria(Boolean podeMelhoria) {
		this.podeMelhoria = podeMelhoria;
	}

	public Boolean isPodeEpEsp() {
		return podeEpEsp;
	}

	public void setPodeEpEsp(Boolean podeEpEsp) {
		this.podeEpEsp = podeEpEsp;
	}

	public Boolean isPode2epoca() {
		return pode2epoca;
	}

	public void setPode2epoca(Boolean pode2epoca) {
		this.pode2epoca = pode2epoca;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public Usuario getUsuarioInscreveu() {
		return UsuarioInscreveu;
	}

	public void setUsuarioInscreveu(Usuario usuarioInscreveu) {
		UsuarioInscreveu = usuarioInscreveu;
	}

	public Usuario getUsuarioAlterou() {
		return UsuarioAlterou;
	}

	public void setUsuarioAlterou(Usuario usuarioAlterou) {
		UsuarioAlterou = usuarioAlterou;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Float getNotaCursoDeVerao() {
		return notaCursoDeVerao;
	}

	public void setNotaCursoDeVerao(Float notaCursoDeVerao) {
		this.notaCursoDeVerao = notaCursoDeVerao;
	}

	public Usuario getUsuarioPrimeiraFrequencia() {
		return usuarioPrimeiraFrequencia;
	}

	public void setUsuarioPrimeiraFrequencia(Usuario usuarioPrimeiraFrequencia) {
		this.usuarioPrimeiraFrequencia = usuarioPrimeiraFrequencia;
	}

	public Usuario getUsuarioSegundaFrequencia() {
		return usuarioSegundaFrequencia;
	}

	public void setUsuarioSegundaFrequencia(Usuario usuarioSegundaFrequencia) {
		this.usuarioSegundaFrequencia = usuarioSegundaFrequencia;
	}

	public Usuario getUsuarioTerceiraFrequencia() {
		return usuarioTerceiraFrequencia;
	}

	public void setUsuarioTerceiraFrequencia(Usuario usuarioTerceiraFrequencia) {
		this.usuarioTerceiraFrequencia = usuarioTerceiraFrequencia;
	}

	public Usuario getUsuarioQuartaFrequencia() {
		return usuarioQuartaFrequencia;
	}

	public void setUsuarioQuartaFrequencia(Usuario usuarioQuartaFrequencia) {
		this.usuarioQuartaFrequencia = usuarioQuartaFrequencia;
	}

	public Usuario getUsuarioExame() {
		return usuarioExame;
	}

	public void setUsuarioExame(Usuario usuarioExame) {
		this.usuarioExame = usuarioExame;
	}

	public Usuario getUsuarioExameOral() {
		return usuarioExameOral;
	}

	public void setUsuarioExameOral(Usuario usuarioExameOral) {
		this.usuarioExameOral = usuarioExameOral;
	}

	public Usuario getUsuarioRecurso() {
		return usuarioRecurso;
	}

	public void setUsuarioRecurso(Usuario usuarioRecurso) {
		this.usuarioRecurso = usuarioRecurso;
	}

	public Usuario getUsuarioRecursoOral() {
		return usuarioRecursoOral;
	}

	public void setUsuarioRecursoOral(Usuario usuarioRecursoOral) {
		this.usuarioRecursoOral = usuarioRecursoOral;
	}

	public Usuario getUsuarioMelhoria() {
		return usuarioMelhoria;
	}

	public void setUsuarioMelhoria(Usuario usuarioMelhoria) {
		this.usuarioMelhoria = usuarioMelhoria;
	}

	public Usuario getUsuarioEspecial() {
		return usuarioEspecial;
	}

	public void setUsuarioEspecial(Usuario usuarioEspecial) {
		this.usuarioEspecial = usuarioEspecial;
	}

	public Usuario getUsuarioEspecialOral() {
		return usuarioEspecialOral;
	}

	public void setUsuarioEspecialOral(Usuario usuarioEspecialOral) {
		this.usuarioEspecialOral = usuarioEspecialOral;
	}

	public Usuario getUsuarioPratica() {
		return usuarioPratica;
	}

	public void setUsuarioPratica(Usuario usuarioPratica) {
		this.usuarioPratica = usuarioPratica;
	}

	public Date getDataNotaCursoVerao() {
		return dataNotaCursoVerao;
	}

	public void setDataNotaCursoVerao(Date dataNotaCursoVerao) {
		this.dataNotaCursoVerao = dataNotaCursoVerao;
	}

	public Usuario getUsuarioCursoVerao() {
		return usuarioCursoVerao;
	}

	public void setUsuarioCursoVerao(Usuario usuarioCursoVerao) {
		this.usuarioCursoVerao = usuarioCursoVerao;
	}

	public Usuario getUsuarioValidou() {
		return UsuarioValidou;
	}

	public void setUsuarioValidou(Usuario usuarioValidou) {
		UsuarioValidou = usuarioValidou;
	}

	public Date getDataValidacao() {
		return dataValidacao;
	}

	public void setDataValidacao(Date dataValidacao) {
		this.dataValidacao = dataValidacao;
	}

	public Date getDataMediaFinal() {
		return dataMediaFinal;
	}

	public void setDataMediaFinal(Date dataMediaFinal) {
		this.dataMediaFinal = dataMediaFinal;
	}

	public Float getMelhoriaNotaOral() {
		return melhoriaNotaOral;
	}

	public void setMelhoriaNotaOral(Float melhoriaNotaOral) {
		this.melhoriaNotaOral = melhoriaNotaOral;
	}

	public Usuario getUsuarioMelhoriaOral() {
		return usuarioMelhoriaOral;
	}

	public void setUsuarioMelhoriaOral(Usuario usuarioMelhoriaOral) {
		this.usuarioMelhoriaOral = usuarioMelhoriaOral;
	}

	public Date getDataMelhoriaOral() {
		return dataMelhoriaOral;
	}

	public void setDataMelhoriaOral(Date dataMelhoriaOral) {
		this.dataMelhoriaOral = dataMelhoriaOral;
	}

	public Float getProjectoNota() {
		return projectoNota;
	}

	public void setProjectoNota(Float projectoNota) {
		this.projectoNota = projectoNota;
	}

	public Usuario getUsuarioNotaProjecto() {
		return usuarioNotaProjecto;
	}

	public void setUsuarioNotaProjecto(Usuario usuarioNotaProjecto) {
		this.usuarioNotaProjecto = usuarioNotaProjecto;
	}

	public Date getDataNotaProjecto() {
		return dataNotaProjecto;
	}

	public void setDataNotaProjecto(Date dataNotaProjecto) {
		this.dataNotaProjecto = dataNotaProjecto;
	}

	public Usuario getUsuarioExtraordinaria() {
		return usuarioExtraordinaria;
	}

	public void setUsuarioExtraordinaria(Usuario usuarioExtraordinaria) {
		this.usuarioExtraordinaria = usuarioExtraordinaria;
	}

	public Float getNotaExtraodinaria() {
		return notaExtraodinaria;
	}

	public void setNotaExtraodinaria(Float notaExtraodinaria) {
		this.notaExtraodinaria = notaExtraodinaria;
	}

	public Date getDataNotaExtraordinaria() {
		return dataNotaExtraordinaria;
	}

	public void setDataNotaExtraordinaria(Date dataNotaExtraordinaria) {
		this.dataNotaExtraordinaria = dataNotaExtraordinaria;
	}

	public Date getUltimaModificacao() {
		return ultimaModificacao;
	}

	public void setUltimaModificacao(Date ultimaModificacao) {
		this.ultimaModificacao = ultimaModificacao;
	}

}