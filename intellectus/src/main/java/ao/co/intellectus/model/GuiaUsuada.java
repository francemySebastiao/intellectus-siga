package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_guia_usada")
public class GuiaUsuada {

	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name="codigo_guia_pagamento")
	private Guia guia;
	@ManyToOne
	@JoinColumn(name="codigo_disciplina")
	private Disciplina disciplina;	
	private float ValorGuia;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Guia getGuia() {
		return guia;
	}
	public void setGuia(Guia guia) {
		this.guia = guia;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public float getValorGuia() {
		return ValorGuia;
	}
	public void setValorGuia(float valorGuia) {
		ValorGuia = valorGuia;
	}
}
