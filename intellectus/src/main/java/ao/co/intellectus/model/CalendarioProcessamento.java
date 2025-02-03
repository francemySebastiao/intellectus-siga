package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_calendario_processamento")
public class CalendarioProcessamento {
	@Id
	@GeneratedValue
	private Integer id;	
	@Temporal(TemporalType.DATE)
	private Date dataProcessamento; 
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	private String mes;
	private Integer totalGuias;
	private float totalValor;
	private float totalPago;
	private float totalAberto;
	private boolean processado;
    @JoinColumn(name="processada_referencias")
	private boolean processadoReferencias;
	@ManyToOne
	@JoinColumn(name="codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	@Column(nullable=true)
	private boolean mesAProcessar;
	@ManyToOne
	@JoinColumn(name="codigo_em_ap")
	private Emolumento emAp;
	@ManyToOne
	@JoinColumn(name="codigo_em_pd")
	private Emolumento emolumentoPd;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataProcessamento() {
		return dataProcessamento;
	}
	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public Integer getTotalGuias() {
		return totalGuias;
	}
	public void setTotalGuias(Integer totalGuias) {
		this.totalGuias = totalGuias;
	}
	public float getTotalValor() {
		return totalValor;
	}
	public void setTotalValor(float totalValor) {
		this.totalValor = totalValor;
	}
	public float getTotalPago() {
		return totalPago;
	}
	public void setTotalPago(float totalPago) {
		this.totalPago = totalPago;
	}
	public float getTotalAberto() {
		return totalAberto;
	}
	public void setTotalAberto(float totalAberto) {
		this.totalAberto = totalAberto;
	}
	public boolean isProcessado() {
		return processado;
	}
	public void setProcessado(boolean processado) {
		this.processado = processado;
	}
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Emolumento getEmAp() {
		return emAp;
	}
	public void setEmAp(Emolumento emAp) {
		this.emAp = emAp;
	}
	public Emolumento getEmolumentoPd() {
		return emolumentoPd;
	}
	public void setEmolumentoPd(Emolumento emolumentoPd) {
		this.emolumentoPd = emolumentoPd;
	}
	public boolean isMesAProcessar() {
		return mesAProcessar;
	}
	public void setMesAProcessar(boolean mesAProcessar) {
		this.mesAProcessar = mesAProcessar;
	}
	public boolean isProcessadoReferencias() {
		return processadoReferencias;
	}
	public void setProcessadoReferencias(boolean processadoReferencias) {
		this.processadoReferencias = processadoReferencias;
	}
	
	
}
