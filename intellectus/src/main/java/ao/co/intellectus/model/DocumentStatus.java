package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "t_document_status")
public class DocumentStatus {

	@Id
	private Integer id;
	private String invoiceStatus;
	private String invoiceStatusDate;
	private String sourceId;
	private String SourceBilling;
	private String workStatus;
	private String workStatusDate;
	private String paymentStatus;
	private String paymentStatusDate;
	private String sourcePayment;
	
	
	
	@XmlElement(name = "InvoiceStatus")
	public String getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	
	@XmlElement(name = "InvoiceStatusDate")
	public String getInvoiceStatusDate() {
		return invoiceStatusDate;
	}
	public void setInvoiceStatusDate(String invoiceStatusDate) {
		this.invoiceStatusDate = invoiceStatusDate;
	}
	
	@XmlElement(name = "SourceID")
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	@XmlElement(name = "SourceBilling")
	public String getSourceBilling() {
		return SourceBilling;
	}
	public void setSourceBilling(String sourceBilling) {
		SourceBilling = sourceBilling;
	}
	
	@XmlElement(name = "WorkStatus")
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	
	@XmlElement(name = "WorkStatusDate")
	public String getWorkStatusDate() {
		return workStatusDate;
	}
	public void setWorkStatusDate(String workStatusDate) {
		this.workStatusDate = workStatusDate;
	}
	
	@XmlElement(name = "WorkStatusDate")
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	@XmlElement(name = "WorkStatusDate")
	public String getPaymentStatusDate() {
		return paymentStatusDate;
	}
	public void setPaymentStatusDate(String paymentStatusDate) {
		this.paymentStatusDate = paymentStatusDate;
	}
	
	@XmlElement(name = "WorkStatusDate")
	public String getSourcePayment() {
		return sourcePayment;
	}
	public void setSourcePayment(String sourcePayment) {
		this.sourcePayment = sourcePayment;
	}
	
	public DocumentStatus() {

	}
	
}
