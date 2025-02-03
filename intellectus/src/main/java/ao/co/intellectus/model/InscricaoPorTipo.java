package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_PROG_INSCRICAO_POR_TIPO")
public class InscricaoPorTipo {
	@Id
	private Integer id;
	private String tipoInscricao;
	private Integer ano;
	private Integer totalInscrito;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipoInscricao() {
		return tipoInscricao;
	}
	public void setTipoInscricao(String tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Integer getTotalInscrito() {
		return totalInscrito;
	}
	public void setTotalInscrito(Integer totalInscrito) {
		this.totalInscrito = totalInscrito;
	}
}
