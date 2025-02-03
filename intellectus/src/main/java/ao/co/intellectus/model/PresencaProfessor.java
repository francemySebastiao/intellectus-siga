package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_presenca_professor")
public class PresencaProfessor {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer desconto;
	private boolean sumario;
	@Temporal(TemporalType.DATE)
	private Date data;
	@Temporal(TemporalType.TIMESTAMP)
    private Date horaEntrada;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaSaida;
	private String horaInicio;
	private String horaFim;
	@ManyToOne
    @JoinColumn(name="codigo_leciona")
    private Leciona leciona;
	@ManyToOne
    @JoinColumn(name="codigo_curso")
    private Curso curso;
	@ManyToOne
    @JoinColumn(name="codigo_disciplina")
    private Disciplina disciplina;
	@ManyToOne
    @JoinColumn(name="codigo_turma")
    private Turma turma;
	@ManyToOne
    @JoinColumn(name="codigo_sala")
    private Sala sala;
	@ManyToOne
    @JoinColumn(name="codigo_docente")
	private Docente docente;
	@ManyToOne
    @JoinColumn(name="codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	private String descricaoSumario;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDesconto() {
		return desconto;
	}
	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}
	public boolean isSumario() {
		return sumario;
	}
	public void setSumario(boolean sumario) {
		this.sumario = sumario;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
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
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}
	public Leciona getLeciona() {
		return leciona;
	}
	public void setLeciona(Leciona leciona) {
		this.leciona = leciona;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
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
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Docente getDocente() {
		return docente;
	}
	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public String getDescricaoSumario() {
		return descricaoSumario; 
	}
	public void setDescricaoSumario(String descricaoSumario) {
		this.descricaoSumario = descricaoSumario;
	}
}