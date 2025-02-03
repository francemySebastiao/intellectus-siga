package ao.co.intellectus.DTO;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class SumarioCliente {
	private Integer id;
    private Integer numeroAula;
    private String sumario;
    private String inicioAula;
    private String fimAula;
    @Temporal(TemporalType.DATE)
    private Date string;
    private Integer turma;
    private Integer disciplina;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumeroAula() {
		return numeroAula;
	}
	public void setNumeroAula(Integer numeroAula) {
		this.numeroAula = numeroAula;
	}
	public String getSumario() {
		return sumario;
	}
	public void setSumario(String sumario) {
		this.sumario = sumario;
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
	public Date getString() {
		return string;
	}
	public void setString(Date string) {
		this.string = string;
	}
	public Integer getTurma() {
		return turma;
	}
	public void setTurma(Integer turma) {
		this.turma = turma;
	}
	public Integer getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Integer disciplina) {
		this.disciplina = disciplina;
	}   
}