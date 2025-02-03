package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_conta_corrente_empresa")
public class ContaCorrenteEmpresa {

	@Id
    @GeneratedValue
	private Integer id;
	
	@OneToOne
    @JoinColumn(name="codigo_empresa")
    private EmpresaConvenio empresa;
	
	@ManyToOne
    @JoinColumn(name="codigo_ano_lectivo")
    private AnoLectivo anoLectivo;
    
	private Double valor;
    
    private Double valorAnterior;
    
    @Temporal(TemporalType.DATE)
    private Date dataMovimento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EmpresaConvenio getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaConvenio empresa) {
		this.empresa = empresa;
	}

	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(Double valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public Date getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}
	
}
