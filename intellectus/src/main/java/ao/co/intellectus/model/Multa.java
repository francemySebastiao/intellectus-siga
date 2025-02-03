package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_multas_guia")
public class Multa {
	@Id
	@GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name="codigo_guia",unique=true)
    private Guia guia;
    private String numeroGuia;
    @OneToOne
    @JoinColumn(name="codigo_ano_lectivo")
    private AnoLectivo anoLectivo;
    @Temporal(TemporalType.DATE)
    private Date dataEmissaoGuia;
    @Temporal(TemporalType.DATE)
    private Date dataVencimentoGuia;
    private Double valorGuia;
    private Double valorMulta;
    private Double valorTotal;
    @Temporal(TemporalType.DATE)
    private Date dataCalculoMulta;
    private boolean fimGeraMulta;
    private Integer quantidadeMultas;
    private Integer aluno;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Guia getGuia() {
		return guia;
	}
	public void setGuia(Guia guia) {
		this.guia = guia;
	}
	public Date getDataEmissaoGuia() {
		return dataEmissaoGuia;
	}
	public void setDataEmissaoGuia(Date dataEmissaoGuia) {
		this.dataEmissaoGuia = dataEmissaoGuia;
	}
	public Date getDataVencimentoGuia() {
		return dataVencimentoGuia;
	}
	public void setDataVencimentoGuia(Date dataVencimentoGuia) {
		this.dataVencimentoGuia = dataVencimentoGuia;
	}
	public Double getValorGuia() {
		return valorGuia;
	}
	public void setValorGuia(Double valorGuia) {
		this.valorGuia = valorGuia;
	}
	public Double getValorMulta() {
		return valorMulta;
	}
	public void setValorMulta(Double valorMulta) {
		this.valorMulta = valorMulta;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Date getDataCalculoMulta() {
		return dataCalculoMulta;
	}
	public void setDataCalculoMulta(Date dataCalculoMulta) {
		this.dataCalculoMulta = dataCalculoMulta;
	}
	public boolean isFimGeraMulta() {
		return fimGeraMulta;
	}
	public void setFimGeraMulta(boolean fimGeraMulta) {
		this.fimGeraMulta = fimGeraMulta;
	}
	public String getNumeroGuia() {
		return numeroGuia;
	}
	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}
	public Integer getQuantidadeMultas() {
		return quantidadeMultas;
	}
	public void setQuantidadeMultas(Integer quantidadeMultas) {
		this.quantidadeMultas = quantidadeMultas;
	}
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Integer getAluno() {
		return aluno;
	}
	public void setAluno(Integer aluno) {
		this.aluno = aluno;
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
		Multa other = (Multa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}