package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_guia_candidatura_historico")
public class GuiaCandidaturaHistorico {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name="codigo_guia_candidatura")
	private GuiaCandidatura guia;
	@ManyToOne
	@JoinColumn(name="codigo_emolumento")
	private Emolumento emolumento;
	private double valor;
	private String obs;
	@ManyToOne
	@JoinColumn(name="codigo_aluno")
	private Candidato candidato;
	@ManyToOne
	@JoinColumn(name="codigo_anoLectivo")
	private AnoLectivo anoLectivo;
	private Integer numeroDeCandidato;
	
	private String quantidade;
	
	@Column(name = "codigo_iva")
	private String codigoIva;
	
	@Column(name = "percentagem_iva")
	private String percentagemIva;
	
	@Column(name = "valor_imposto")
	private double valorImposto;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public GuiaCandidatura getGuia() {
		return guia;
	}
	public void setGuia(GuiaCandidatura guia) {
		this.guia = guia;
	}
	public Emolumento getEmolumento() {
		return emolumento;
	}
	public void setEmolumento(Emolumento emolumento) {
		this.emolumento = emolumento;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public Candidato getCandidato() {
		return candidato;
	}
	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Integer getNumeroDeCandidato() {
		return numeroDeCandidato;
	}
	public void setNumeroDeCandidato(Integer numeroDeCandidato) {
		this.numeroDeCandidato = numeroDeCandidato;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getCodigoIva() {
		return codigoIva;
	}
	public void setCodigoIva(String codigoIva) {
		this.codigoIva = codigoIva;
	}
	public String getPercentagemIva() {
		return percentagemIva;
	}
	public void setPercentagemIva(String percentagemIva) {
		this.percentagemIva = percentagemIva;
	}
	public double getValorImposto() {
		return valorImposto;
	}
	public void setValorImposto(double valorImposto) {
		this.valorImposto = valorImposto;
	}	
	
	
}
