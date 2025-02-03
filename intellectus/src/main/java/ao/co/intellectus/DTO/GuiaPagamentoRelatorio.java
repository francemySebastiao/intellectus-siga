package ao.co.intellectus.DTO;

import java.util.Date;
import java.util.List;

public class GuiaPagamentoRelatorio {
	private String numeroContribuinte;
	private String instituicao;
	private String grupoOwner;
	private String endereco;
	private String numeroGuia;
	private String nomeCompletoAluno;
	private Integer numeroAluno;
	private double montanteTotal;
	private Date dataEmissao;
	private Date dataVencimento;
	private double refUsd;
	private String emitidoPor;
	private String tipo;
	private String tipoAccao;
	private String usuarioAccao;
	private String formaPagamento;
	//dados do aluno.
	private String curso;
	private String turma;
	private String morada;
	private Integer anoCurricular;
	
	private List<DetalhePagamento> detalhesPagamento;
	public String getNumeroContribuinte() {
		return numeroContribuinte;
	}
	public void setNumeroContribuinte(String numeroContribuinte) {
		this.numeroContribuinte = numeroContribuinte;
	}
	public String getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
	public String getGrupoOwner() {
		return grupoOwner;
	}
	public void setGrupoOwner(String grupoOwner) {
		this.grupoOwner = grupoOwner;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumeroGuia() {
		return numeroGuia;
	}
	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}
	public String getNomeCompletoAluno() {
		return nomeCompletoAluno;
	}
	public void setNomeCompletoAluno(String nomeCompletoAluno) {
		this.nomeCompletoAluno = nomeCompletoAluno;
	}
	public Integer getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
	}
	public double getMontanteTotal() {
		return montanteTotal;
	}
	public void setMontanteTotal(double montanteTotal) {
		this.montanteTotal = montanteTotal;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public double getRefUsd() {
		return refUsd;
	}
	public void setRefUsd(double refUsd) {
		this.refUsd = refUsd;
	}
	public String getEmitidoPor() {
		return emitidoPor;
	}
	public void setEmitidoPor(String emitidoPor) {
		this.emitidoPor = emitidoPor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public List<DetalhePagamento> getDetalhesPagamento() {
		return detalhesPagamento;
	}
	public void setDetalhesPagamento(List<DetalhePagamento> detalhesPagamento) {
		this.detalhesPagamento = detalhesPagamento;
	}
	public String getTipoAccao() {
		return tipoAccao;
	}
	public void setTipoAccao(String tipoAccao) {
		this.tipoAccao = tipoAccao;
	}
	public String getUsuarioAccao() {
		return usuarioAccao;
	}
	public void setUsuarioAccao(String usuarioAccao) {
		this.usuarioAccao = usuarioAccao;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public String getMorada() {
		return morada;
	}
	public void setMorada(String morada) {
		this.morada = morada;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}
}