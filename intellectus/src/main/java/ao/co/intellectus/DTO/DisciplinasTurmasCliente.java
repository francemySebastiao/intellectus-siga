package ao.co.intellectus.DTO;

import java.util.List;

public class DisciplinasTurmasCliente {
    private List<DisciplinaCliente> disciplinas;
    private List<TurmaCliente> turmas;
    private Integer tipoInscricao;
    private Integer anoCorricular;
    private Integer turmaBase;
    private String turmaBaseStr;
    private Integer anoLectivo;
    private Integer duracaoCurso;
    
	public List<DisciplinaCliente> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<DisciplinaCliente> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public List<TurmaCliente> getTurmas() {
		return turmas;
	}
	public void setTurmas(List<TurmaCliente> turmas) {
		this.turmas = turmas;
	}
	public Integer getTipoInscricao() {
		return tipoInscricao;
	}
	public String getTurmaBaseStr() {
		return turmaBaseStr;
	}
	public void setTurmaBaseStr(String turmaBaseStr) {
		this.turmaBaseStr = turmaBaseStr;
	}
	public void setTipoInscricao(Integer tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}
	public Integer getTurmaBase() {
		return turmaBase;
	}
	public void setTurmaBase(Integer turmaBase) {
		this.turmaBase = turmaBase;
	}
	public Integer getAnoCorricular() {
		return anoCorricular;
	}
	public void setAnoCorricular(Integer anoCorricular) {
		this.anoCorricular = anoCorricular;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Integer getDuracaoCurso() {
		return duracaoCurso;
	}
	public void setDuracaoCurso(Integer duracaoCurso) {
		this.duracaoCurso = duracaoCurso;
	}
	
	
}