package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_emolumento")
public class Emolumento {	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer codigo;
	private String emolumento;
	private boolean propina;
	private boolean status; 
	private boolean paraContecioso; 
	private boolean desconta;
	@Column(nullable=true)
	private boolean podeMulta;
	
	@Column(name = "codigo_iva")
	private String codigoIva;
	
	@Column(name = "percentagem_iva")
	private Integer percentagemIva;
	
	@Column(name = "percentagem_desconto")
	private Integer percentagemDesconto;
	
	public Integer getId() {
		return id;
	} 
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getEmolumento() {
		return emolumento;
	}
	public void setEmolumento(String emolumento) {
		this.emolumento = emolumento;
	}
	public boolean isPropina() {
		return propina;
	}
	public void setPropina(boolean propina) {
		this.propina = propina;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isParaContecioso() {
		return paraContecioso;
	}
	public void setParaContecioso(boolean paraContecioso) {
		this.paraContecioso = paraContecioso;
	}
	public boolean isDesconta() {
		return desconta;
	}
	public void setDesconta(boolean desconta) {
		this.desconta = desconta;
	}
	public boolean isPodeMulta() {
		return podeMulta;
	}
	public void setPodeMulta(boolean podeMulta) {
		this.podeMulta = podeMulta;
	}
	public String getCodigoIva() {
		return codigoIva;
	}
	public void setCodigoIva(String codigoIva) {
		this.codigoIva = codigoIva;
	}
	public Integer getPercentagemIva() {
		return percentagemIva;
	}
	public void setPercentagemIva(Integer percentagemIva) {
		this.percentagemIva = percentagemIva;
	}
	public Integer getPercentagemDesconto() {
		return percentagemDesconto;
	}
	public void setPercentagemDesconto(Integer percentagemDesconto) {
		this.percentagemDesconto = percentagemDesconto;
	}
	public Emolumento() {
	}
	public Emolumento(Integer id) {
		this.id = id;
	}
	
	
	
	
}