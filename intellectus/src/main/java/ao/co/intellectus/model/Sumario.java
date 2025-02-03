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
@Table(name="t_sumario")
public class Sumario {
	@Id
	@GeneratedValue 
	private Long id;
    @Column(length=2000)
    private String sumario;
    @Enumerated(EnumType.STRING)
    @Column(nullable=true)
    private Semestre semestre;
    @Column(name="hora_inicio_aula")
    private String inicioAula;
    @Column(name="hora_fim_aula")
    private String fimAula;
    @ManyToOne
    @JoinColumn(name="codigo_turma")
    private Turma turma;
    @ManyToOne
    @JoinColumn(name="codigo_disciplina")
    private Disciplina disciplina;
	@ManyToOne
	@JoinColumn(name = "codigo_docente")
	private Docente docente;
	@ManyToOne
	@JoinColumn(name = "codigo_sala")
	private Sala sala;
	@ManyToOne
	@JoinColumn(name = "codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEfectivaAula;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaEfectivaEntrada;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaEfectivaSaida;
	@ManyToOne
	@JoinColumn(name = "codigo_intervalo")
	private Intervalo intervalo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Intervalo getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(Intervalo intervalo) {
		this.intervalo = intervalo;
	}
	public String getSumario() { 
		return sumario;
	}
	public void setSumario(String sumario) {
		this.sumario = sumario;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public String getInicioAula() {
		return inicioAula;
	}
	public void setInicioAula(String inicioAula) {
		this.inicioAula = inicioAula;
	}
	public String getFimAula() {
		return fimAula;
	}
	public void setFimAula(String fimAula) {
		this.fimAula = fimAula;
	}
	
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
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
	public Date getDataEfectivaAula() {
		return dataEfectivaAula;
	}
	public void setDataEfectivaAula(Date dataEfectivaAula) {
		this.dataEfectivaAula = dataEfectivaAula;
	}
	public Date getHoraEfectivaEntrada() {
		return horaEfectivaEntrada;
	}
	public void setHoraEfectivaEntrada(Date horaEfectivaEntrada) {
		this.horaEfectivaEntrada = horaEfectivaEntrada;
	}
	public Date getHoraEfectivaSaida() {
		return horaEfectivaSaida;
	}
	public void setHoraEfectivaSaida(Date horaEfectivaSaida) {
		this.horaEfectivaSaida = horaEfectivaSaida;
	}   
	
}