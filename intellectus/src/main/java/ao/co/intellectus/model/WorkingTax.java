package ao.co.intellectus.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_working_tax")
public class WorkingTax {

	@Id
	private Integer id;
	private String taxType;
	private String taxCountryRegion;
	private String taxCode;
	private BigDecimal taxPercentage;
	private BigDecimal taxAmount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	public String getTaxCountryRegion() {
		return taxCountryRegion;
	}
	public void setTaxCountryRegion(String taxCountryRegion) {
		this.taxCountryRegion = taxCountryRegion;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public BigDecimal getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(BigDecimal taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	
}
