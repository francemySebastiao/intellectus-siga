package ao.co.intellectus.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_working_line")
public class WorkingLine {

	@Id
	private Integer id;
	private Integer lineNumber;
	@ManyToOne
	@JoinColumn(name ="order_references")
	private OrderReference orderReference;
	private String productCode;
	private String productDescription;
	private BigDecimal quantity;
	private String unitOfMeasure;
	private BigDecimal unitPrice;
	private String taxPointDate;
	private String reference;
	private BigDecimal debitAmount;
	private BigDecimal creditAmount;
	private String description;
	@ManyToOne
	@JoinColumn(name ="tax")
	private Tax tax;
	private String taxExemptionReason;
	private String taxExemptionCode;
	@ManyToOne
	@JoinColumn(name ="source_document_id")
	private SourceDocumentId sourceDocumentId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
	public OrderReference getOrderReference() {
		return orderReference;
	}
	public void setOrderReference(OrderReference orderReference) {
		this.orderReference = orderReference;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getTaxPointDate() {
		return taxPointDate;
	}
	public void setTaxPointDate(String taxPointDate) {
		this.taxPointDate = taxPointDate;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public BigDecimal getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Tax getTax() {
		return tax;
	}
	public void setTax(Tax tax) {
		this.tax = tax;
	}
	public String getTaxExemptionReason() {
		return taxExemptionReason;
	}
	public void setTaxExemptionReason(String taxExemptionReason) {
		this.taxExemptionReason = taxExemptionReason;
	}
	public String getTaxExemptionCode() {
		return taxExemptionCode;
	}
	public void setTaxExemptionCode(String taxExemptionCode) {
		this.taxExemptionCode = taxExemptionCode;
	}
	public SourceDocumentId getSourceDocumentId() {
		return sourceDocumentId;
	}
	public void setSourceDocumentId(SourceDocumentId sourceDocumentId) {
		this.sourceDocumentId = sourceDocumentId;
	}
	
	
	
}
