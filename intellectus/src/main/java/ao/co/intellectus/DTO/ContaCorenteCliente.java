package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ContaCorenteCliente {
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date dataMovimento;
	private Integer numeroAluno;
	private Integer codigoAluno;
	private String nome;
	private Double valor;
	private Double valorAnterior;
	private Integer anoLectivo;
	private Double valorDivida;
	private Double valorMulta;
	private Double totalGeralDivida;
	private String instituicao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
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
	public Integer getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
	}
	public Integer getCodigoAluno() {
		return codigoAluno;
	}
	public void setCodigoAluno(Integer codigoAluno) {
		this.codigoAluno = codigoAluno;
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
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
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
	public String getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
}
