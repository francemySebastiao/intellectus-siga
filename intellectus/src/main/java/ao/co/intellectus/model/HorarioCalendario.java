package ao.co.intellectus.model;

import java.util.Date;

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
@Table(name="t_horario_calendario")
public class HorarioCalendario {
	@Id
	@GeneratedValue
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date data;
	private Integer ano;
	private Integer diaSemana;
	private boolean diaUtil;
	private boolean finalDeSemana;	
	@Enumerated(EnumType.STRING)
	private Semestre semestre;
	@Enumerated(EnumType.STRING)
	private Semana diaSemanaNome; 
	@ManyToOne
	@JoinColumn(name="codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	@ManyToOne
	@JoinColumn(name="codigo_instituicao")
	private Instituicao instituicao;
	@ManyToOne
	@JoinColumn(name="codigo_tipo_dia")
	private TipoDia tipoDia;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Instituicao getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	public TipoDia getTipoDia() {
		return tipoDia;
	}
	public void setTipoDia(TipoDia tipoDia) {
		this.tipoDia = tipoDia;
	}
	public Integer getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(Integer diaSemana) {
		this.diaSemana = diaSemana;
	}
	public Semana getDiaSemanaNome() {
		return diaSemanaNome;
	}
	public void setDiaSemanaNome(Semana diaSemanaNome) {
		this.diaSemanaNome = diaSemanaNome;
	}
	public void setDiaSemanaNome(String diaSemanaNome) {
		if(diaSemanaNome.equals("SABADO"))
		  this.diaSemanaNome = Semana.SABADO;
		if(diaSemanaNome.equals("DOMINGO"))
			this.diaSemanaNome = Semana.DOMINGO;
		if(diaSemanaNome.equals("SEGUNDA"))
			this.diaSemanaNome = Semana.SEGUNDA;
		if(diaSemanaNome.equals("TERCA"))
			this.diaSemanaNome = Semana.TERCA;
		if(diaSemanaNome.equals("QUARTA"))
			this.diaSemanaNome = Semana.QUARTA;
		if(diaSemanaNome.equals("QUINTA"))
			this.diaSemanaNome = Semana.QUINTA;
		if(diaSemanaNome.equals("SEXTA"))
			this.diaSemanaNome = Semana.SEXTA;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public boolean isDiaUtil() {
		return diaUtil;
	}
	public void setDiaUtil(boolean diaUtil) {
		this.diaUtil = diaUtil;
	}
	public boolean isFinalDeSemana() { 
		return finalDeSemana;
	}
	public void setFinalDeSemana(boolean finalDeSemana) {
		this.finalDeSemana = finalDeSemana;
	}
}