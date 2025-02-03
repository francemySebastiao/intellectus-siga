package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_credito_de_aluno")
public class CreaditoDeAluno {
    @Id
    @GeneratedValue
	private Integer id;
    private double valor;
    @Temporal(TemporalType.DATE)
    private Date dataRegistro;
    @Temporal(TemporalType.DATE)
    private Date dataDeposito;
    @ManyToOne
    @JoinColumn(name="codigo_aluno")
    private Aluno aluno;
    private Integer numeroDeAluno;
    @ManyToOne
    @JoinColumn(name="codigo_empresa")
    private EmpresaConvenio empresa;
    @ManyToOne
    @JoinColumn(name="codigo_ano_lectivo")
    private AnoLectivo anoLectivo;
    @Column(length=25)
    private String bordero;
    @ManyToOne
    @JoinColumn(name="codigo_banco")
    private Banco banco;
    @ManyToOne
    @JoinColumn(name="codigo_moeda")    
    private Moeda moeda;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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
	public String getBordero() {
		return bordero;
	}
	public void setBordero(String bordero) {
		this.bordero = bordero;
	}
	public Date getDataDeposito() {
		return dataDeposito;
	}
	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}
	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public Banco getBanco() {
		return banco;
	}
	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	public Moeda getMoeda() {
		return moeda;
	}
	public void setMoeda(Moeda moeda) {
		this.moeda = moeda;
	}	
}