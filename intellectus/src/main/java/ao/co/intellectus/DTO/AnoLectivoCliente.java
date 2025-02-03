package ao.co.intellectus.DTO;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class AnoLectivoCliente {
	private Integer id;
	private int anoLectivo;
	private boolean status;
	private String anoLectivoDescricao;
	/* PRIMEIRO SEMESTRE */
	private Date inicioPrimeiroSemestre;
	private Date fimPrimeiroSemestre;
	private Date inicioExamePrimeiroSemestre;
	private Date fimExamePrimeiroSemestre;
	private Date inicioRecursoPrimeiroSemestre;
	private Date fimRecursoPrimeiroSemestre;
	/* SEGUNDO SEMESTRE */
	private Date inicioSegundoSemestre;
	private Date fimSegundoSemestre;
	private Date inicioExameSegundoSemestre;
	private Date fimExameSegundoSemestre;
	private Date inicioRecursoSegundoSemestre;
	private Date fimRecursoSegundoSemestre;
	// INSTITUICAO DE REFERENCIA...
	@NotNull
	private int instit;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(int anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getAnoLectivoDescricao() {
		return anoLectivoDescricao;
	}

	public void setAnoLectivoDescricao(String anoLectivoDescricao) {
		this.anoLectivoDescricao = anoLectivoDescricao;
	}
	public Date getInicioPrimeiroSemestre() {
		return inicioPrimeiroSemestre;
	}
	public void setInicioPrimeiroSemestre(Date inicioPrimeiroSemestre) {
		this.inicioPrimeiroSemestre = inicioPrimeiroSemestre;
	}
	public Date getFimPrimeiroSemestre() {
		return fimPrimeiroSemestre;
	}
	public void setFimPrimeiroSemestre(Date fimPrimeiroSemestre) {
		this.fimPrimeiroSemestre = fimPrimeiroSemestre;
	}
	public Date getInicioExamePrimeiroSemestre() {
		return inicioExamePrimeiroSemestre;
	}
	public void setInicioExamePrimeiroSemestre(Date inicioExamePrimeiroSemestre) {
		this.inicioExamePrimeiroSemestre = inicioExamePrimeiroSemestre;
	}
	public Date getFimExamePrimeiroSemestre() {
		return fimExamePrimeiroSemestre;
	}
	public void setFimExamePrimeiroSemestre(Date fimExamePrimeiroSemestre) {
		this.fimExamePrimeiroSemestre = fimExamePrimeiroSemestre;
	}
	public Date getInicioRecursoPrimeiroSemestre() {
		return inicioRecursoPrimeiroSemestre;
	}
	public void setInicioRecursoPrimeiroSemestre(Date inicioRecursoPrimeiroSemestre) {
		this.inicioRecursoPrimeiroSemestre = inicioRecursoPrimeiroSemestre;
	}
	public Date getFimRecursoPrimeiroSemestre() {
		return fimRecursoPrimeiroSemestre;
	}
	public void setFimRecursoPrimeiroSemestre(Date fimRecursoPrimeiroSemestre) {
		this.fimRecursoPrimeiroSemestre = fimRecursoPrimeiroSemestre;
	}
	public Date getInicioSegundoSemestre() {
		return inicioSegundoSemestre;
	}
	public void setInicioSegundoSemestre(Date inicioSegundoSemestre) {
		this.inicioSegundoSemestre = inicioSegundoSemestre;
	}
	public Date getFimSegundoSemestre() {
		return fimSegundoSemestre;
	}
	public void setFimSegundoSemestre(Date fimSegundoSemestre) {
		this.fimSegundoSemestre = fimSegundoSemestre;
	}
	public Date getInicioExameSegundoSemestre() {
		return inicioExameSegundoSemestre;
	}
	public void setInicioExameSegundoSemestre(Date inicioExameSegundoSemestre) {
		this.inicioExameSegundoSemestre = inicioExameSegundoSemestre;
	}
	public Date getFimExameSegundoSemestre() {
		return fimExameSegundoSemestre;
	}
	public void setFimExameSegundoSemestre(Date fimExameSegundoSemestre) {
		this.fimExameSegundoSemestre = fimExameSegundoSemestre;
	}
	public Date getInicioRecursoSegundoSemestre() {
		return inicioRecursoSegundoSemestre;
	}
	public void setInicioRecursoSegundoSemestre(Date inicioRecursoSegundoSemestre) {
		this.inicioRecursoSegundoSemestre = inicioRecursoSegundoSemestre;
	}
	public Date getFimRecursoSegundoSemestre() {
		return fimRecursoSegundoSemestre;
	}
	public void setFimRecursoSegundoSemestre(Date fimRecursoSegundoSemestre) {
		this.fimRecursoSegundoSemestre = fimRecursoSegundoSemestre;
	}
	public int getInstit() {
		return instit;
	}
	public void setInstit(int instit) {
		this.instit = instit;
	}	
}