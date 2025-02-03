package ao.co.intellectus.DTO;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"paymentRefNo", "transactionDate", "paymentType", "documentStatus", "sourceId", "systemEntryDate", "customerId", "line", "documentTotals"})
public class PaymentDTO {


	private String paymentRefNo;
	private String transactionDate;
	private String paymentType;
	private List<DocumentStatusDTO> documentStatus;
	private String sourceId;
	private String systemEntryDate;
	private String customerId;
	private List<LineDTO> line;
	private List<DocumentTotalsDTO> documentTotals;
	

	@XmlElement(name = "PaymentRefNo")
	public String getPaymentRefNo() {
		return paymentRefNo;
	}

	public void setPaymentRefNo(String paymentRefNo) {
		this.paymentRefNo = paymentRefNo;
	}

	@XmlElement(name = "TransactionDate")
	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	@XmlElement(name = "PaymentType")
	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@XmlElement(name = "SourceID")
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@XmlElement(name = "SystemEntryDate")
	public String getSystemEntryDate() {
		return systemEntryDate;
	}

	public void setSystemEntryDate(String systemEntryDate) {
		this.systemEntryDate = systemEntryDate;
	}

	@XmlElement(name = "DocumentStatus")
	public List<DocumentStatusDTO> getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(List<DocumentStatusDTO> documentStatus) {
		this.documentStatus = documentStatus;
	}
	
	@XmlElement(name = "Line")
	public List<LineDTO> getLine() {
		return line;
	}

	public void setLine(List<LineDTO> line) {
		this.line = line;
	}

	@XmlElement(name = "DocumentTotals")
	public List<DocumentTotalsDTO> getDocumentTotals() {
		return documentTotals;
	}

	public void setDocumentTotals(List<DocumentTotalsDTO> documentTotals) {
		this.documentTotals = documentTotals;
	}

	@XmlElement(name = "CustomerID")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
