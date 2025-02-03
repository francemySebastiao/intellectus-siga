package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.Situacao;
import ao.co.intellectus.model.TipoProvaExtraOrdinaria;

public class DisciplinaExtraordinariaCliente {
	private Integer id;
	private Integer idMatricula;
	private Integer anoCorricular;
	private String descricao;
	private String tipo;
	private boolean paraInscricao;
	private Integer turma;
	@Temporal(TemporalType.DATE)
	private Date data;
	@Enumerated(EnumType.STRING)
	private TipoProvaExtraOrdinaria tipoProva;
	
	@Enumerated(EnumType.STRING)
	private TipoProvaExtraOrdinaria prova;
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	private boolean matriculado;
	private Integer anoLectivo;
	private String anoLectivoDescricao;
	private Float notaFinal;
	private Integer anoCorricularAtual;

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
	public Integer getAnoCorricular() {
		return anoCorricular;
	}
	public void setAnoCorricular(Integer anoCorricular) {
		this.anoCorricular = anoCorricular;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isParaInscricao() {
		return paraInscricao;
	}
	public void setParaInscricao(boolean paraInscricao) {
		this.paraInscricao = paraInscricao;
	}
	public TipoProvaExtraOrdinaria getTipoProva() {
		return tipoProva;
	}
	public void setTipoProva(TipoProvaExtraOrdinaria tipoProva) {
		this.tipoProva = tipoProva;
	}
	public Integer getTurma() {
		return turma;
	}
	public void setTurma(Integer turma) {
		this.turma = turma;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Integer getIdMatricula() {
		return idMatricula;
	}
	public void setIdMatricula(Integer idMatricula) {
		this.idMatricula = idMatricula;
	}
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	public boolean isMatriculado() {
		return matriculado;
	}
	public void setMatriculado(boolean matriculado) {
		this.matriculado = matriculado;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Integer getAnoCorricularAtual() {
		return anoCorricularAtual;
	}
	public void setAnoCorricularAtual(Integer anoCorricularAtual) {
		this.anoCorricularAtual = anoCorricularAtual;
	}
	public Float getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(Float notaFinal) {
		this.notaFinal = notaFinal;
	}
	public TipoProvaExtraOrdinaria getProva() {
		return prova;
	}
	public void setProva(TipoProvaExtraOrdinaria prova) {
		this.prova = prova;
	} 
}