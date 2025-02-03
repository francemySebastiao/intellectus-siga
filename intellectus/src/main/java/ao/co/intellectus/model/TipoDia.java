package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_tipo_dia")
public class TipoDia {
	@Id
	@GeneratedValue
	private Integer id;
	private String tipoDia;
	private String codigoDia;
	private String corDia;
	private boolean lectivo;
	private boolean exames;
	private boolean ferias;
	private boolean pagaFerias;
	private boolean contaHoras;
	private boolean descontaHoras;
	private boolean contaCompensacao;
	private boolean descontaAtrasos;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipoDia() {
		return tipoDia;
	}
	public void setTipoDia(String tipoDia) {
		this.tipoDia = tipoDia;
	}
	public String getCodigoDia() {
		return codigoDia;
	}
	public void setCodigoDia(String codigoDia) {
		this.codigoDia = codigoDia;
	}
	public String getCorDia() {
		return corDia;
	}
	public void setCorDia(String corDia) {
		this.corDia = corDia;
	}
	public boolean isLectivo() {
		return lectivo;
	}
	public void setLectivo(boolean lectivo) {
		this.lectivo = lectivo;
	}
	public boolean isExames() {
		return exames;
	}
	public void setExames(boolean exames) {
		this.exames = exames;
	}
	public boolean isFerias() {
		return ferias;
	}
	public void setFerias(boolean ferias) {
		this.ferias = ferias;
	}
	public boolean isPagaFerias() {
		return pagaFerias;
	}
	public void setPagaFerias(boolean pagaFerias) {
		this.pagaFerias = pagaFerias;
	}
	public boolean isContaHoras() {
		return contaHoras;
	}
	public void setContaHoras(boolean contaHoras) {
		this.contaHoras = contaHoras;
	}
	public boolean isDescontaHoras() {
		return descontaHoras;
	}
	public void setDescontaHoras(boolean descontaHoras) {
		this.descontaHoras = descontaHoras;
	}
	public boolean isContaCompensacao() {
		return contaCompensacao;
	}
	public void setContaCompensacao(boolean contaCompensacao) {
		this.contaCompensacao = contaCompensacao;
	}
	public boolean isDescontaAtrasos() {
		return descontaAtrasos;
	}
	public void setDescontaAtrasos(boolean descontaAtrasos) {
		this.descontaAtrasos = descontaAtrasos;
	}
}
