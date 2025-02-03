package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_intervalo")
public class Intervalo {
	@Id
	@GeneratedValue
	private Integer id;
	private String diaSemana;
	private String descricao;
	private String inicio;
	private String fim;
	private Integer ordemDia;
	private Integer ordem1;
	private Integer ordem2;
	@Enumerated(EnumType.STRING)
	private Turno turno;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getFim() {
		return fim;
	}
	public void setFim(String fim) {
		this.fim = fim;
	}
	
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public Integer getOrdemDia() {
		return ordemDia;
	}
	public void setOrdemDia(Integer ordemDia) {
		this.ordemDia = ordemDia;
	}
	public Integer getOrdem1() {
		return ordem1;
	}
	public void setOrdem1(Integer ordem1) {
		this.ordem1 = ordem1;
	}
	public Integer getOrdem2() {
		return ordem2;
	}
	public void setOrdem2(Integer ordem2) {
		this.ordem2 = ordem2;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intervalo other = (Intervalo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}