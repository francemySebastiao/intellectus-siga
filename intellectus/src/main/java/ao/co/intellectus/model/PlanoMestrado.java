package ao.co.intellectus.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_plano_mestrado")
public class PlanoMestrado {
	@Id
	@GeneratedValue
	private Integer id;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private boolean encerrado;
	@Enumerated(EnumType.STRING)
	private Grau grau;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	public boolean isEncerrado() {
		return encerrado;
	}
	public void setEncerrado(boolean encerrado) {
		this.encerrado = encerrado;
	}
	public Grau getGrau() {
		return grau;
	}
	public void setGrau(Grau grau) {
		this.grau = grau;
	}
}
