package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_payment_document_status")
public class PaymentDocumentStatus {

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
	public String getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	public String getInvoiceStatusDate() {
		return invoiceStatusDate;
	}
	public void setInvoiceStatusDate(String invoiceStatusDate) {
		this.invoiceStatusDate = invoiceStatusDate;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getSourceBilling() {
		return SourceBilling;
	}
	public void setSourceBilling(String sourceBilling) {
		SourceBilling = sourceBilling;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	public String getWorkStatusDate() {
		return workStatusDate;
	}
	public void setWorkStatusDate(String workStatusDate) {
		this.workStatusDate = workStatusDate;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentStatusDate() {
		return paymentStatusDate;
	}
	public void setPaymentStatusDate(String paymentStatusDate) {
		this.paymentStatusDate = paymentStatusDate;
	}
	public String getSourcePayment() {
		return sourcePayment;
	}
	public void setSourcePayment(String sourcePayment) {
		this.sourcePayment = sourcePayment;
	}
	
	
	
}
