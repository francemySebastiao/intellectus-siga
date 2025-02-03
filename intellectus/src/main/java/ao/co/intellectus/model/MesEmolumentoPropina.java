package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_mes_emolumento_propina")
public class MesEmolumentoPropina {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(length=16)
	private String mes;
	@ManyToOne
	@JoinColumn(name="codigo_emolumento_normal")
	private Emolumento emolumentoNormal;
	@Column(length=50)
	private String descricaoNormal;
	@ManyToOne
	@JoinColumn(name="codigo_inscricao_disciplina")
	private Emolumento emolumentoInscricaoDisciplina;
	@Column(length=50)
	private String descricaoInscricaoDisciplina;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Emolumento getEmolumentoNormal() {
		return emolumentoNormal;
	}
	public void setEmolumentoNormal(Emolumento emolumentoNormal) {
		this.emolumentoNormal = emolumentoNormal;
	}
	public Emolumento getEmolumentoInscricaoDisciplina() {
		return emolumentoInscricaoDisciplina;
	}
	public void setEmolumentoInscricaoDisciplina(Emolumento emolumentoInscricaoDisciplina) {
		this.emolumentoInscricaoDisciplina = emolumentoInscricaoDisciplina;
	}
	public String getDescricaoNormal() {
		return descricaoNormal;
	}
	public void setDescricaoNormal(String descricaoNormal) {
		this.descricaoNormal = descricaoNormal;
	}
	public String getDescricaoInscricaoDisciplina() {
		return descricaoInscricaoDisciplina;
	}
	public void setDescricaoInscricaoDisciplina(String descricaoInscricaoDisciplina) {
		this.descricaoInscricaoDisciplina = descricaoInscricaoDisciplina;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
}
