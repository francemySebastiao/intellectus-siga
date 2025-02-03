package ao.co.intellectus.DTO;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"lineNumber", "sourceDocumentId", "originationOn", "productCode", "productDescription", "quantity", "unitOfMeasure", "unitPrice", "taxPointDate", "reference", "description", "debitAmount", 
		"creditAmount", "tax", "taxExemptionReason", "taxExemptionCode"})
public class LineDTO {

	private Integer lineNumber;
	private List<OrderReferenceDTO> originationOn;
	private String productCode;
	private String productDescription;
	private BigDecimal quantity;
	private String unitOfMeasure;
	private BigDecimal unitPrice;
	private String taxPointDate;
	private List<ReferencesDTO> reference;
	private String description;
	private BigDecimal debitAmount;
	private BigDecimal creditAmount;
	private List<TaxDTO> tax;
	private String taxExemptionReason;
	private String taxExemptionCode;
	private SourceDocumentIdDTO sourceDocumentId;
	
	@XmlElement(name = "LineNumber")
	public Integer getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	@XmlElement(name = "OrderReferences")
	public List<OrderReferenceDTO> getOriginationOn() {
		return originationOn;
	}
	public void setOriginationOn(List<OrderReferenceDTO> originationOn) {
		this.originationOn = originationOn;
	}
	@XmlElement(name = "ProductCode")
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	@XmlElement(name = "ProductDescription")
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	@XmlElement(name = "Quantity")
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	@XmlElement(name = "UnitOfMeasure")
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	
	@XmlElement(name = "UnitPrice")
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@XmlElement(name = "TaxPointDate")
	public String getTaxPointDate() {
		return taxPointDate;
	}
	public void setTaxPointDate(String taxPointDate) {
		this.taxPointDate = taxPointDate;
	}
	
	@XmlElement(name = "References")
	public List<ReferencesDTO> getReference() {
		return reference;
	}
	public void setReference(List<ReferencesDTO> reference) {
		this.reference = reference;
	}
	
	@XmlElement(name = "Description")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement(name = "Tax")
	public List<TaxDTO> getTax() {
		return tax;
	}
	public void setTax(List<TaxDTO> tax) {
		this.tax = tax;
	}
	
	@XmlElement(name = "TaxExemptionReason")
	public String getTaxExemptionReason() {
		return taxExemptionReason;
	}
	
	public void setTaxExemptionReason(String taxExemptionReason) {
		this.taxExemptionReason = taxExemptionReason;
	}
	
	@XmlElement(name = "TaxExemptionCode")
	public String getTaxExemptionCode() {
		return taxExemptionCode;
	}
	public void setTaxExemptionCode(String taxExemptionCode) {
		this.taxExemptionCode = taxExemptionCode;
	}
	
	@XmlElement(name = "SourceDocumentID")
	public SourceDocumentIdDTO getSourceDocumentId() {
		return sourceDocumentId;
	}
	public void setSourceDocumentId(SourceDocumentIdDTO sourceDocumentId) {
		this.sourceDocumentId = sourceDocumentId;
	}
	@XmlElement(name = "DebitAmount")
	public BigDecimal getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}
	@XmlElement(name = "CreditAmount")
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}
}
