package ao.co.intellectus.model.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Turno;

public class TurmaRequest {
	private Integer id;
    private String turma;
	@Enumerated(EnumType.ORDINAL)
    private Turno turno;
	private Integer instituicao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public Integer getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Integer instituicao) {
		this.instituicao = instituicao;
	}

}
