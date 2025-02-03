package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class InscricaoPorAno {
	private Integer id;
	private Date dataInscricao;
	private Integer anoLectivo;
	private Integer anoLectivoId;
	private String PlanoPagamento;
	private String tipoInscricao;
	private Integer desconto;
	private Integer anoCurricular;
	private String curso;
	private String sigla;
	private boolean anulado;
	private String turma;
	private String usuario;
	private Integer turmaId;
	@Temporal(TemporalType.DATE)
	private Date dataAnulamento;
	private String anoLectivoDescricao;
	
	
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
	public Date getDataInscricao() {
		return dataInscricao;
	}
	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public String getPlanoPagamento() {
		return PlanoPagamento;
	}
	public void setPlanoPagamento(String planoPagamento) {
		PlanoPagamento = planoPagamento;
	}
	public String getTipoInscricao() {
		return tipoInscricao;
	}
	public void setTipoInscricao(String tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}
	public Integer getDesconto() {
		return desconto;
	}
	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public boolean isAnulado() {
		return anulado;
	}
	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}
	public Date getDataAnulamento() {
		return dataAnulamento;
	}
	public void setDataAnulamento(Date dataAnulamento) {
		this.dataAnulamento = dataAnulamento;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public Integer getTurmaId() {
		return turmaId;
	}
	public void setTurmaId(Integer turmaId) {
		this.turmaId = turmaId;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Integer getAnoLectivoId() {
		return anoLectivoId;
	}
	public void setAnoLectivoId(Integer anoLectivoId) {
		this.anoLectivoId = anoLectivoId;
	}
}
