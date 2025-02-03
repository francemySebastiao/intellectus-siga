package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_horario_temporario")
public class HorarioTemporario {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(length=20)
	private String segunda;
	@Column(length=20)
	private String terca;
	@Column(length=20)
	private String quarta;
	@Column(length=20)
	private String quinta;
	@Column(length=20)
	private String sexta;
	@Column(length=20)
	private String sabado;
	@Column(length=20)
	private String intervalo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSegunda() {
		return segunda;
	}
	public void setSegunda(String segunda) {
		this.segunda = segunda;
	}
	public String getTerca() {
		return terca;
	}
	public void setTerca(String terca) {
		this.terca = terca;
	}
	public String getQuarta() {
		return quarta;
	}
	public void setQuarta(String quarta) {
		this.quarta = quarta;
	}
	public String getQuinta() {
		return quinta;
	}
	public void setQuinta(String quinta) {
		this.quinta = quinta;
	}
	public String getSexta() {
		return sexta;
	}
	public void setSexta(String sexta) {
		this.sexta = sexta;
	}
	public String getSabado() {
		return sabado;
	}
	public void setSabado(String sabado) {
		this.sabado = sabado;
	}
	public String getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(String intervalo) {
		this.intervalo = intervalo;
	}	
	
}
