package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
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
@Table(name="t_conta_corrente")
public class ContaCorrenteAluno {
    @Id
    @GeneratedValue
	private Integer id;
    @Temporal(TemporalType.DATE)
    private Date dataMovimento;
    @OneToOne
    @JoinColumn(name="codigo_aluno")
    private Aluno aluno;
    //@Column(unique=true)
    private String numeroDeAluno;
    @ManyToOne
    @JoinColumn(name="codigo_ano_lectivo")
    private AnoLectivo anoLectivo;
    private Double valor;
    private Double valorAnterior;
    @ManyToOne
    @JoinColumn(name="codigo_instituicao")
    private Instituicao instituicao;
    private Double valorDivida;
    private Double valorMulta;
    private Double totalGeralDivida;
    
    @OneToOne
    @JoinColumn(name="codigo_empresa")
    private EmpresaConvenio empresa;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataMovimento() {
		return dataMovimento;
	}
	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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
	
	public String getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(String numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public Instituicao getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	public Double getValorDivida() {
		return valorDivida;
	}
	public void setValorDivida(Double valorDivida) {
		this.valorDivida = valorDivida;
	}
	
	public Double getValorMulta() {
		return valorMulta;
	}
	public void setValorMulta(Double valorMulta) {
		this.valorMulta = valorMulta;
	}
	public Double getTotalGeralDivida() {
		return totalGeralDivida;
	}
	public void setTotalGeralDivida(Double totalGeralDivida) {
		this.totalGeralDivida = totalGeralDivida;
	}
	
	public EmpresaConvenio getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaConvenio empresa) {
		this.empresa = empresa;
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
		ContaCorrenteAluno other = (ContaCorrenteAluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
