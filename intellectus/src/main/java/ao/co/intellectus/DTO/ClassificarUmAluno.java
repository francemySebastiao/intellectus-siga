package ao.co.intellectus.DTO;

import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.Prova;
import ao.co.intellectus.model.TipoDisciplina;

public class ClassificarUmAluno {
	private Integer id;
	private Integer numeroDeAluno;
	private String nome;
	private String usuarioAv1;
	private Float primeiraAvaliacao;
	@Temporal(TemporalType.DATE)
	private Date dataPrimeiraAvaliacao;
	private String usuarioAv2;
	private Float segundaAvaliacao ;
	@Temporal(TemporalType.DATE)
	private Date dataSegundaAvaliacao;
	private Float exame;
	private String usuarioExame;
	@Temporal(TemporalType.DATE)
	private Date dataExame;
	private Float exameOral;
	@Temporal(TemporalType.DATE)
	private Date dataOralExame;
	private Float recurso;
	private String usuarioRecurso;
	@Temporal(TemporalType.DATE)
	private Date dataRecurso;
	private Float recursoOral;
	private String usuarioRecursoOral;
	@Temporal(TemporalType.DATE)
	private Date dataRecursoOral;
	private Float epocaEspecial;
	private String usuarioEpocaEspecial;
	@Temporal(TemporalType.DATE)
	private Date dataEspecial;
	private Float melhorNota;
	private String usuarioMelhoria;
	@Temporal(TemporalType.DATE)
	private Date dataMelhoriaNota;
	private String usuarioPrimeiroTrabaho;
	private Float primeiroTrabalho;
	@Temporal(TemporalType.DATE)
	private Date dataPrimeiroTrabalho;
	private String usuarioSegundoTrabaho;
	private Float segundoTrabalho;
	@Temporal(TemporalType.DATE)
	private Date dataSegundoTrabalho;
	private String usuarioPratica;
	private String usuarioExameOral;
	
	public String getUsuarioAv1() {
		return usuarioAv1;
	}
	public void setUsuarioAv1(String usuarioAv1) {
		this.usuarioAv1 = usuarioAv1;
	}
	public String getUsuarioAv2() {
		return usuarioAv2;
	}
	public void setUsuarioAv2(String usuarioAv2) {
		this.usuarioAv2 = usuarioAv2;
	}
	public String getUsuarioExame() {
		return usuarioExame;
	}
	public void setUsuarioExame(String usuarioExame) {
		this.usuarioExame = usuarioExame;
	}
	public String getUsuarioRecurso() {
		return usuarioRecurso;
	}
	public void setUsuarioRecurso(String usuarioRecurso) {
		this.usuarioRecurso = usuarioRecurso;
	}
	public String getUsuarioRecursoOral() {
		return usuarioRecursoOral;
	}
	public void setUsuarioRecursoOral(String usuarioRecursoOral) {
		this.usuarioRecursoOral = usuarioRecursoOral;
	}
	public String getUsuarioEpocaEspecial() {
		return usuarioEpocaEspecial;
	}
	public void setUsuarioEpocaEspecial(String usuarioEpocaEspecial) {
		this.usuarioEpocaEspecial = usuarioEpocaEspecial;
	}
	public String getUsuarioMelhoria() {
		return usuarioMelhoria;
	}
	public void setUsuarioMelhoria(String usuarioMelhoria) {
		this.usuarioMelhoria = usuarioMelhoria;
	}
	public String getUsuarioPrimeiroTrabaho() {
		return usuarioPrimeiroTrabaho;
	}
	public void setUsuarioPrimeiroTrabaho(String usuarioPrimeiroTrabaho) {
		this.usuarioPrimeiroTrabaho = usuarioPrimeiroTrabaho;
	}
	public String getUsuarioSegundoTrabaho() {
		return usuarioSegundoTrabaho;
	}
	public void setUsuarioSegundoTrabaho(String usuarioSegundoTrabaho) {
		this.usuarioSegundoTrabaho = usuarioSegundoTrabaho;
	}
	public String getUsuarioPratica() {
		return usuarioPratica;
	}
	public void setUsuarioPratica(String usuarioPratica) {
		this.usuarioPratica = usuarioPratica;
	}
	public String getUsuarioVerao() {
		return usuarioVerao;
	}
	public void setUsuarioVerao(String usuarioVerao) {
		this.usuarioVerao = usuarioVerao;
	}
	private Float pratica;
	@Temporal(TemporalType.DATE)
	private Date dataPratica;
	private Float verao;
	private String usuarioVerao;
	@Temporal(TemporalType.DATE)
	private Date dataVerao;
    private Float notaFinalContinua;
	@Temporal(TemporalType.DATE)
	private Date dataNotaFinalContinua;
	private Float notaFinal;
	@Temporal(TemporalType.DATE)
	private Date dataNotaFinal;
	private boolean validada;
	@Enumerated(EnumType.STRING)
	private TipoDisciplina tipoDisciplina;
	private List<Prova> provas;
	private boolean semFrequencia;
	
	//NOVO
	//private Integer usuarioExtraordinaria;
	private Float notaExtraodinaria;
	@Temporal(TemporalType.DATE)
	private Date dataNotaExtraordinaria;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Float getPrimeiraAvaliacao() {
		return primeiraAvaliacao;
	}
	public void setPrimeiraAvaliacao(Float primeiraAvaliacao) {
		this.primeiraAvaliacao = primeiraAvaliacao;
	}
	public Date getDataPrimeiraAvaliacao() {
		return dataPrimeiraAvaliacao;
	}
	public void setDataPrimeiraAvaliacao(Date dataPrimeiraAvaliacao) {
		this.dataPrimeiraAvaliacao = dataPrimeiraAvaliacao;
	}
	public Float getSegundaAvaliacao() {
		return segundaAvaliacao;
	}
	public void setSegundaAvaliacao(Float segundaAvaliacao) {
		this.segundaAvaliacao = segundaAvaliacao;
	}
	public Date getDataSegundaAvaliacao() {
		return dataSegundaAvaliacao;
	}
	public void setDataSegundaAvaliacao(Date dataSegundaAvaliacao) {
		this.dataSegundaAvaliacao = dataSegundaAvaliacao;
	}
	public Float getExame() {
		return exame;
	}
	public void setExame(Float exame) {
		this.exame = exame;
	}
	public Date getDataExame() {
		return dataExame;
	}
	public void setDataExame(Date dataExame) {
		this.dataExame = dataExame;
	}
	public Float getExameOral() {
		return exameOral;
	}
	public void setExameOral(Float exameOral) {
		this.exameOral = exameOral;
	}
	public Date getDataOralExame() {
		return dataOralExame;
	}
	public void setDataOralExame(Date dataOralExame) {
		this.dataOralExame = dataOralExame;
	}
	public Float getRecurso() {
		return recurso;
	}
	public void setRecurso(Float recurso) {
		this.recurso = recurso;
	}
	public Date getDataRecurso() {
		return dataRecurso;
	}
	public void setDataRecurso(Date dataRecurso) {
		this.dataRecurso = dataRecurso;
	}
	public Float getRecursoOral() {
		return recursoOral;
	}
	public void setRecursoOral(Float recursoOral) {
		this.recursoOral = recursoOral;
	}
	public Date getDataRecursoOral() {
		return dataRecursoOral;
	}
	public void setDataRecursoOral(Date dataRecursoOral) {
		this.dataRecursoOral = dataRecursoOral;
	}
	public Float getEpocaEspecial() {
		return epocaEspecial;
	}
	public void setEpocaEspecial(Float epocaEspecial) {
		this.epocaEspecial = epocaEspecial;
	}
	public Date getDataEspecial() {
		return dataEspecial;
	}
	public void setDataEspecial(Date dataEspecial) {
		this.dataEspecial = dataEspecial;
	}
	public Float getMelhorNota() {
		return melhorNota;
	}
	public void setMelhorNota(Float melhorNota) {
		this.melhorNota = melhorNota;
	}
	public Date getDataMelhoriaNota() {
		return dataMelhoriaNota;
	}
	public void setDataMelhoriaNota(Date dataMelhoriaNota) {
		this.dataMelhoriaNota = dataMelhoriaNota;
	}
	public Float getPrimeiroTrabalho() {
		return primeiroTrabalho;
	}
	public void setPrimeiroTrabalho(Float primeiroTrabalho) {
		this.primeiroTrabalho = primeiroTrabalho;
	}
	public Date getDataPrimeiroTrabalho() {
		return dataPrimeiroTrabalho;
	}
	public void setDataPrimeiroTrabalho(Date dataPrimeiroTrabalho) {
		this.dataPrimeiroTrabalho = dataPrimeiroTrabalho;
	}
	public Float getSegundoTrabalho() {
		return segundoTrabalho;
	}
	public void setSegundoTrabalho(Float segundoTrabalho) {
		this.segundoTrabalho = segundoTrabalho;
	}
	public Date getDataSegundoTrabalho() {
		return dataSegundoTrabalho;
	}
	public void setDataSegundoTrabalho(Date dataSegundoTrabalho) {
		this.dataSegundoTrabalho = dataSegundoTrabalho;
	}
	public Float getPratica() {
		return pratica;
	}
	public void setPratica(Float pratica) {
		this.pratica = pratica;
	}
	public Date getDataPratica() {
		return dataPratica;
	}
	public void setDataPratica(Date dataPratica) {
		this.dataPratica = dataPratica;
	}
	public Float getVerao() {
		return verao;
	}
	public void setVerao(Float verao) {
		this.verao = verao;
	}
	public Date getDataVerao() {
		return dataVerao;
	}
	public void setDataVerao(Date dataVerao) {
		this.dataVerao = dataVerao;
	}
	public Float getNotaFinalContinua() {
		return notaFinalContinua;
	}
	public void setNotaFinalContinua(Float notaFinalContinua) {
		this.notaFinalContinua = notaFinalContinua;
	}
	public Date getDataNotaFinalContinua() {
		return dataNotaFinalContinua;
	}
	public void setDataNotaFinalContinua(Date dataNotaFinalContinua) {
		this.dataNotaFinalContinua = dataNotaFinalContinua;
	}
	public Float getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(Float notaFinal) {
		this.notaFinal = notaFinal;
	}
	public Date getDataNotaFinal() {
		return dataNotaFinal;
	}
	public void setDataNotaFinal(Date dataNotaFinal) {
		this.dataNotaFinal = dataNotaFinal;
	}
	public boolean isValidada() {
		return validada;
	}
	public void setValidada(boolean validada) {
		this.validada = validada;
	}
	public TipoDisciplina getTipoDisciplina() {
		return tipoDisciplina;
	}
	public void setTipoDisciplina(TipoDisciplina tipoDisciplina) {
		this.tipoDisciplina = tipoDisciplina;
	}
	public List<Prova> getProvas() {
		return provas;
	}
	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}
	public boolean isSemFrequencia() {
		return semFrequencia;
	}
	public void setSemFrequencia(boolean semFrequencia) {
		this.semFrequencia = semFrequencia;
	}
	public Float getNotaExtraodinaria() {
		return notaExtraodinaria;
	}
	public void setNotaExtraodinaria(Float notaExtraodinaria) {
		this.notaExtraodinaria = notaExtraodinaria;
	}
	public Date getDataNotaExtraordinaria() {
		return dataNotaExtraordinaria;
	}
	public void setDataNotaExtraordinaria(Date dataNotaExtraordinaria) {
		this.dataNotaExtraordinaria = dataNotaExtraordinaria;
	}
	public String getUsuarioExameOral() {
		return usuarioExameOral;
	}
	public void setUsuarioExameOral(String usuarioExameOral) {
		this.usuarioExameOral = usuarioExameOral;
	}
}