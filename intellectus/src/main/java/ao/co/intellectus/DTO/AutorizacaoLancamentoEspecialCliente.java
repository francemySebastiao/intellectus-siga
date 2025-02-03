package ao.co.intellectus.DTO;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class AutorizacaoLancamentoEspecialCliente {
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date dataRegistro;
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	@Temporal(TemporalType.DATE)
	private Date dataFim;
	private Integer disciplina;
	private Integer anoLectivo;
	private Integer prova;
	private Integer docente;
	private Integer turma;
	private boolean emCurso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Integer disciplina) {
		this.disciplina = disciplina;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Integer getProva() {
		return prova;
	}

	public void setProva(Integer prova) {
		this.prova = prova;
	}

	public Integer getDocente() {
		return docente;
	}

	public void setDocente(Integer docente) {
		this.docente = docente;
	}

	public Integer getTurma() {
		return turma;
	}

	public void setTurma(Integer turma) {
		this.turma = turma;
	}

	public boolean isEmCurso() {
		return emCurso;
	}

	public void setEmCurso(boolean emCurso) {
		this.emCurso = emCurso;
	}
}
