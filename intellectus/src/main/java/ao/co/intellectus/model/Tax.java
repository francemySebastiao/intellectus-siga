package ao.co.intellectus.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "t_tax")
public class Tax {

	@Id
	private Integer id;
	private String taxType;
	private String taxCountryRegion;
	private String taxCode;
	private BigDecimal taxPercentage;
	private BigDecimal taxAmount;
	
	
	@XmlElement(name = "TaxType")
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	
	@XmlElement(name = "TaxCountryRegion")
	public String getTaxCountryRegion() {
		return taxCountryRegion;
	}
	public void setTaxCountryRegion(String taxCountryRegion) {
		this.taxCountryRegion = taxCountryRegion;
	}
	
	@XmlElement(name = "TaxCode")
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	
	@XmlElement(name = "TaxPercentage")
	public BigDecimal getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(BigDecimal taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	
	@XmlElement(name = "TaxAmount")
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	
	
}
