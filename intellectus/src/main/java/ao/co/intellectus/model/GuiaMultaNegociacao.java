package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_guia_multa_negociacao")
public class GuiaMultaNegociacao {
	@Id
	@GeneratedValue
	private Integer id;
	private String numeroDeAluno;
	@Temporal(TemporalType.DATE)
	private Date dataInsercao;
	@Temporal(TemporalType.DATE)
	private Date dataEmissaoGuia;
	@ManyToOne
	@JoinColumn(name="codigo_guia")
	private Guia guia;
	@ManyToOne
	@JoinColumn(name="codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	@ManyToOne
	@JoinColumn(name="codigo_aluno")
	private Aluno aluno;
	@ManyToOne
	@JoinColumn(name="codigo_emolumento")
	private Emolumento emolumento;
	@ManyToOne
	@JoinColumn(name="codigo_guia_multa_valor")
	private GuiaMultaValor guiaMultaValor;
	@Temporal(TemporalType.DATE)
    private Date dataEmissaoPlano;
	private boolean liquidada;
	private boolean anulado;
	private boolean processadoPlano;
	@Temporal(TemporalType.DATE)
	private Date dataLiquidada;
	@Temporal(TemporalType.DATE)
	private Date dataAnulado;
	private double valor;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(String numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public Date getDataInsercao() {
		return dataInsercao;
	}
	public void setDataInsercao(Date dataInsercao) {
		this.dataInsercao = dataInsercao;
	}
	public Date getDataEmissaoGuia() {
		return dataEmissaoGuia;
	}
	public void setDataEmissaoGuia(Date dataEmissaoGuia) {
		this.dataEmissaoGuia = dataEmissaoGuia;
	}
	public Guia getGuia() {
		return guia;
	}
	public void setGuia(Guia guia) {
		this.guia = guia;
	}
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Emolumento getEmolumento() {
		return emolumento;
	}
	public void setEmolumento(Emolumento emolumento) {
		this.emolumento = emolumento;
	}
	public GuiaMultaValor getGuiaMultaValor() {
		return guiaMultaValor;
	}
	public void setGuiaMultaValor(GuiaMultaValor guiaMultaValor) {
		this.guiaMultaValor = guiaMultaValor;
	}
	public Date getDataEmissaoPlano() {
		return dataEmissaoPlano;
	}
	public void setDataEmissaoPlano(Date dataEmissaoPlano) {
		this.dataEmissaoPlano = dataEmissaoPlano;
	}
	public boolean isLiquidada() {
		return liquidada;
	}
	public void setLiquidada(boolean liquidada) {
		this.liquidada = liquidada;
	}
	public boolean isAnulado() {
		return anulado;
	}
	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}
	public boolean isProcessadoPlano() {
		return processadoPlano;
	}
	public void setProcessadoPlano(boolean processadoPlano) {
		this.processadoPlano = processadoPlano;
	}
	public Date getDataLiquidada() {
		return dataLiquidada;
	}
	public void setDataLiquidada(Date dataLiquidada) {
		this.dataLiquidada = dataLiquidada;
	}
	public Date getDataAnulado() {
		return dataAnulado;
	}
	public void setDataAnulado(Date dataAnulado) {
		this.dataAnulado = dataAnulado;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
