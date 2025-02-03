package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_GUIAS_LIQUIDADAS")
public class GuiasLiquidadas {

	@Id
	private String numeroGuia;
	private int anoLectivo;
	private String curso;
	private String nome;
	@Temporal(TemporalType.DATE)
	private Date dataLiquidacao;
	private String banco;
	//private String emolumento;
	private float valor;
	//private int codigoEmolumento;
	@Column(name="NUMERO_BORDEREUX")
	private String numeroBordereux;
	@Temporal(TemporalType.DATE)
	private Date dataDeposito;
	

	public String getNumeroGuia() {
		return numeroGuia;
	}

	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}

	public int getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(int anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataLiquidacao() {
		return dataLiquidacao;
	}

	public void setDataLiquidacao(Date dataLiquidacao) {
		this.dataLiquidacao = dataLiquidacao;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getNumeroBordereux() {
		return numeroBordereux;
	}

	public void setNumeroBordereux(String numeroBordereux) {
		this.numeroBordereux = numeroBordereux;
	}

	public Date getDataDeposito() {
		return dataDeposito;
	}

	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}

}
