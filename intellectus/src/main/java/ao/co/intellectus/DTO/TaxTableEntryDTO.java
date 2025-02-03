package ao.co.intellectus.DTO;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"taxType", "taxCode", "description", "taxPercentage", "taxAmount"})
public class TaxTableEntryDTO {

	private String taxType;
	private String taxCode;
	private String description;
	private BigDecimal taxPercentage;
	private BigDecimal taxAmount;
	
	@XmlElement(name = "TaxType")
	public String getTaxType() {
		return taxType;
	}
	
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	
	@XmlElement(name = "TaxCode")
	public String getTaxCode() {
		return taxCode;
	}
	
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	
	@XmlElement(name = "Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
