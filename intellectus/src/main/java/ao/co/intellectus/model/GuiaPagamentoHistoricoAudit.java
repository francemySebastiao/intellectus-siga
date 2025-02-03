package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_guia_pagamento_historico_audit")
public class GuiaPagamentoHistoricoAudit {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "codigo_guia_pagamento")
	private GuiaAudit guiaAudit;
	@ManyToOne
	@JoinColumn(name = "codigo_emolumento")
	private Emolumento emolumento;
	private double valor;
	private String obs;
	@ManyToOne
	@JoinColumn(name = "codigo_aluno")
	private Aluno aluno;
	@ManyToOne
	@JoinColumn(name = "codigo_anoLectivo")
	private AnoLectivo anoLectivo;
	private String numeroDeAluno;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GuiaAudit getGuiaAudit() {
		return guiaAudit;
	}

	public void setGuiaAudit(GuiaAudit guiaAudit) {
		this.guiaAudit = guiaAudit;
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

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public String getNumeroDeAluno() {
		return numeroDeAluno;
	}

	public void setNumeroDeAluno(String numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
}