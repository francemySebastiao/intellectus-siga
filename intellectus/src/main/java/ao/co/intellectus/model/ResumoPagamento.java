package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_RESUMO_PAGAMENTO")
public class ResumoPagamento {
	@Id
	private Integer id;
	private Integer numeroDeAluno;
	private String descricaoPagamento;
	private Integer codigoAnoLectivo;
	private float valor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}

	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}

	public String getDescricaoPagamento() {
		return descricaoPagamento;
	}

	public void setDescricaoPagamento(String descricaoPagamento) {
		this.descricaoPagamento = descricaoPagamento;
	}

	public Integer getCodigoAnoLectivo() {
		return codigoAnoLectivo;
	}

	public void setCodigoAnoLectivo(Integer codigoAnoLectivo) {
		this.codigoAnoLectivo = codigoAnoLectivo;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
}
