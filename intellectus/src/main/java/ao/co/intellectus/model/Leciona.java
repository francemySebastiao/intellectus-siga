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
@Table(name = "t_leciona")
public class Leciona {
	@Id 
	@GeneratedValue
	private Integer id;
	private Integer anoCurricular;
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	@Enumerated(EnumType.STRING)
	private TipoAula tipoAula;
	@Enumerated(EnumType.STRING)
	private Semana diaSemana;
	private Integer diaSemanaInteiro;
	@Enumerated(EnumType.STRING)
	private Semestre semestre;
	@ManyToOne
	@JoinColumn(name = "codigo_disciplina")
	private Disciplina disciplina;
	@ManyToOne
	@JoinColumn(name = "codigo_turma")
	private Turma turma;
	@ManyToOne
	@JoinColumn(name = "codigo_curso")
	private Curso curso;
	@ManyToOne
	@JoinColumn(name = "codigo_docente")
	private Docente docente;
	@ManyToOne
	@JoinColumn(name = "codigo_sala")
	private Sala sala;
	@ManyToOne
	@JoinColumn(name = "codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	@ManyToOne
	@JoinColumn(name = "codigo_intervalo")
	private Intervalo intervalo;
	@ManyToOne
	@JoinColumn(name = "codigo_instituicao")
	private Instituicao instituicao;
	@Temporal(TemporalType.DATE)
	private Date diaAula;
	//DADOS DE ASSIDUIDADE
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaEntrada;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaSaida;
	@Column(nullable=true)
	private boolean sumarioEmitido;
	private Integer descontoMinutos;
	@Column(nullable=true)
	private boolean justificado;
	private Integer minutosJustificados;
	private Integer descontoMinutoFinal;
	@Enumerated(EnumType.STRING)
	private EstadoAssiduidade estadoAssiduidade;
	
	public Integer getId() {
		return id;
	} 
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public TipoAula getTipoAula() {
		return tipoAula;
	}
	public void setTipoAula(TipoAula tipoAula) {
		this.tipoAula = tipoAula;
	}
	public Semana getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(Semana diaSemana) {
		this.diaSemana = diaSemana;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
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
	public Docente getDocente() {
		return docente;
	}
	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Intervalo getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(Intervalo intervalo) {
		this.intervalo = intervalo;
	}
	public Instituicao getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	public Date getDiaAula() {
		return diaAula;
	}
	public void setDiaAula(Date diaAula) {
		this.diaAula = diaAula;
	}
	public Date getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public Date getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}
	public boolean isSumarioEmitido() {
		return sumarioEmitido;
	}
	public void setSumarioEmitido(boolean sumarioEmitido) {
		this.sumarioEmitido = sumarioEmitido;
	}
	public Integer getDescontoMinutos() {
		return descontoMinutos;
	}
	public void setDescontoMinutos(Integer descontoMinutos) {
		this.descontoMinutos = descontoMinutos;
	}
	public boolean isJustificado() {
		return justificado;
	}
	public void setJustificado(boolean justificado) {
		this.justificado = justificado;
	}
	public Integer getMinutosJustificados() {
		return minutosJustificados;
	}
	public void setMinutosJustificados(Integer minutosJustificados) {
		this.minutosJustificados = minutosJustificados;
	}
	public Integer getDescontoMinutoFinal() {
		return descontoMinutoFinal;
	}
	public void setDescontoMinutoFinal(Integer descontoMinutoFinal) {
		this.descontoMinutoFinal = descontoMinutoFinal;
	}
	public Integer getDiaSemanaInteiro() {
		return diaSemanaInteiro;
	}
	public void setDiaSemanaInteiro(Integer diaSemanaInteiro) {
		this.diaSemanaInteiro = diaSemanaInteiro;
	}
	public EstadoAssiduidade getEstadoAssiduidade() {
		return estadoAssiduidade;
	}
	public void setEstadoAssiduidade(EstadoAssiduidade estadoAssiduidade) {
		this.estadoAssiduidade = estadoAssiduidade;
	}
}