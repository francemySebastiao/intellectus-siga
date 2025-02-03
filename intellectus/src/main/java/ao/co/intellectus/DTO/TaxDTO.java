package ao.co.intellectus.DTO;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"taxType", "taxCountryRegion", "taxCode", "taxPercentage", "taxAmount"})
public class TaxDTO {

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
