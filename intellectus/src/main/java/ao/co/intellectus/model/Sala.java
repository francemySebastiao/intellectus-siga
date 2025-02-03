package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_sala")
public class Sala {
	@Id
	@GeneratedValue
	private Integer id;
	private String designacao;
	private Integer tipoSala;
	private Integer capacidade;
	private boolean projector;
	private boolean quadroMultimedia;
	private boolean som;
	private boolean estado;
	private Integer sector;
	private boolean Mesa;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDesignacao() {
		return designacao;
	}
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}
	public Integer getTipoSala() {
		return tipoSala;
	}
	public void setTipoSala(Integer tipoSala) {
		this.tipoSala = tipoSala;
	}
	public Integer getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}
	public boolean isProjector() {
		return projector;
	}
	public void setProjector(boolean projector) {
		this.projector = projector;
	}
	public boolean isQuadroMultimedia() {
		return quadroMultimedia;
	}
	public void setQuadroMultimedia(boolean quadroMultimedia) {
		this.quadroMultimedia = quadroMultimedia;
	}
	public boolean isSom() {
		return som;
	}
	public void setSom(boolean som) {
		this.som = som;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Integer getSector() {
		return sector;
	}
	public void setSector(Integer sector) {
		this.sector = sector;
	}
	public boolean isMesa() {
		return Mesa;
	}
	public void setMesa(boolean mesa) {
		Mesa = mesa;
	}
}
