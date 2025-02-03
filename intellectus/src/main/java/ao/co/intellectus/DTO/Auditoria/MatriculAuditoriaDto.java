package ao.co.intellectus.DTO.Auditoria;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class MatriculAuditoriaDto {
	
	private Integer id;
	private Integer anoCurricular;
	private String anoLectivoMatricula;
	private boolean anulado;
	private String nomeAluno;
	private String nomeUserinscreveu;
	private String nomeUserAlterou;
	private Date dataMatricula;
	private Date dataAlteracao;
	private String userAlterouTipoInscricao;
	private Date dataAlteracaoTipoInscricao;
	private String userAnulou;
	private Date dataAnulamento;
	private String curso;
	private String tipoInscricao;
	private String turmaBase;
	private String planoPagamento;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}
	public String getAnoLectivoMatricula() {
		return anoLectivoMatricula;
	}
	public void setAnoLectivoMatricula(String anoLectivoMatricula) {
		this.anoLectivoMatricula = anoLectivoMatricula;
	}
	public boolean isAnulado() {
		return anulado;
	}
	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getNomeUserinscreveu() {
		return nomeUserinscreveu;
	}
	public void setNomeUserinscreveu(String nomeUserinscreveu) {
		this.nomeUserinscreveu = nomeUserinscreveu;
	}
	public String getNomeUserAlterou() {
		return nomeUserAlterou;
	}
	public void setNomeUseAlterou(String nomeUseAlterou) {
		this.nomeUserAlterou = nomeUseAlterou;
	}
	public Date getDataMatricula() {
		return dataMatricula;
	}
	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public String getUserAlterouTipoInscricao() {
		return userAlterouTipoInscricao;
	}
	public void setUserAlterouTipoInscricao(String userAlterouTipoInscricao) {
		this.userAlterouTipoInscricao = userAlterouTipoInscricao;
	}
	public Date getDataAlteracaoTipoInscricao() {
		return dataAlteracaoTipoInscricao;
	}
	public void setDataAlteracaoTipoInscricao(Date dataAlteracaoTipoInscricao) {
		this.dataAlteracaoTipoInscricao = dataAlteracaoTipoInscricao;
	}
	public String getUserAnulou() {
		return userAnulou;
	}
	public void setUserAnulou(String userAnulou) {
		this.userAnulou = userAnulou;
	}
	public Date getDataAnulamento() {
		return dataAnulamento;
	}
	public void setDataAnulamento(Date dataAnulamento) {
		this.dataAnulamento = dataAnulamento;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getTipoInscricao() {
		return tipoInscricao;
	}
	public void setTipoInscricao(String tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}
	public String getTurmaBase() {
		return turmaBase;
	}
	public void setTurmaBase(String turmaBase) {
		this.turmaBase = turmaBase;
	}
	public String getPlanoPagamento() {
		return planoPagamento;
	}
	public void setPlanoPagamento(String planoPagamento) {
		this.planoPagamento = planoPagamento;
	}
	
	
	
	

}
