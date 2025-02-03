package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_payment")
public class Payment {

	@Id
	private Integer id;
	private String paymentRefNo;
	private String transactionDate;
	private String paymentType;
	@ManyToOne
	@JoinColumn(name = "document_status")
	private DocumentStatus documentStatus;
	private String sourceId;
	private String systemEntryDate;
	private String customerId;
	@ManyToOne
	@JoinColumn(name = "line")
	private Line line;
	@ManyToOne
	@JoinColumn(name = "document_total")
	private DocumentTotals documentTotals;
	
	
	
	public String getPaymentRefNo() {
		return paymentRefNo;
	}
	public void setPaymentRefNo(String paymentRefNo) {
		this.paymentRefNo = paymentRefNo;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public DocumentStatus getDocumentStatus() {
		return documentStatus;
	}
	public void setDocumentStatus(DocumentStatus documentStatus) {
		this.documentStatus = documentStatus;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getSystemEntryDate() {
		return systemEntryDate;
	}
	public void setSystemEntryDate(String systemEntryDate) {
		this.systemEntryDate = systemEntryDate;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public DocumentTotals getDocumentTotals() {
		return documentTotals;
	}
	public void setDocumentTotals(DocumentTotals documentTotals) {
		this.documentTotals = documentTotals;
	}
	

}
