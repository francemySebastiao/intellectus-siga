package ao.co.intellectus.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.TipoDisciplina;

public class DisciplinaMatricula {
	private Integer id;
	private String descricao;
	private Integer anoCorricular;
	private boolean concluida;
	@Enumerated(EnumType.STRING)
	private TipoDisciplina tipoDisciplina;
	private Integer anoLectivo;
	private String anoLectivoDescricao;
	private boolean aprovado;
	private String nota;
	

	public String getAnoLectivoDescricao() {
		return anoLectivoDescricao;
	}

	public void setAnoLectivoDescricao(String anoLectivoDescricao) {
		this.anoLectivoDescricao = anoLectivoDescricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isConcluida() {
		return concluida;
	}

	public void setConcluida(boolean concluida) {
		this.concluida = concluida;
	}

	public Integer getAnoCorricular() {
		return anoCorricular;
	}

	public void setAnoCorricular(Integer anoCorricular) {
		this.anoCorricular = anoCorricular;
	}

	public TipoDisciplina getTipoDisciplina() {
		return tipoDisciplina;
	}

	public void setTipoDisciplina(TipoDisciplina tipoDisciplina) {
		this.tipoDisciplina = tipoDisciplina;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}
	
	
}
