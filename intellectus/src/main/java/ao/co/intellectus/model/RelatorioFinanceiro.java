package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.enumeracao.TipoRelatorio;

@Entity
@Table(name = "t_relario_financeiro")
public class RelatorioFinanceiro {
	@Id
	@GeneratedValue
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date dataEnviar;
	@Temporal(TemporalType.DATE)
	private Date dataEnviado;
	private Integer mesInteiro;
	private boolean enviado;
	private boolean proximo;
	private String primeiroResponsavel;
	private String segundoResponsavel;
	private Integer anoLectivo;
	@Enumerated(EnumType.STRING)
	private TipoRelatorio tipoRelatorio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataEnviar() {
		return dataEnviar;
	}

	public void setDataEnviar(Date dataEnviar) {
		this.dataEnviar = dataEnviar;
	}

	public Date getDataEnviado() {
		return dataEnviado;
	}

	public void setDataEnviado(Date dataEnviado) {
		this.dataEnviado = dataEnviado;
	}

	public Integer getMesInteiro() {
		return mesInteiro;
	}

	public void setMesInteiro(Integer mesInteiro) {
		this.mesInteiro = mesInteiro;
	}

	public boolean isEnviado() {
		return enviado;
	}

	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
	}

	public boolean isProximo() {
		return proximo;
	}

	public void setProximo(boolean proximo) {
		this.proximo = proximo;
	}

	public String getPrimeiroResponsavel() {
		return primeiroResponsavel;
	}

	public void setPrimeiroResponsavel(String primeiroResponsavel) {
		this.primeiroResponsavel = primeiroResponsavel;
	}

	public String getSegundoResponsavel() {
		return segundoResponsavel;
	}

	public void setSegundoResponsavel(String segundoResponsavel) {
		this.segundoResponsavel = segundoResponsavel;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public TipoRelatorio getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(TipoRelatorio tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

}
