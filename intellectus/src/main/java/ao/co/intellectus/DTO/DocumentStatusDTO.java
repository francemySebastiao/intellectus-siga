package ao.co.intellectus.DTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"workStatus", "workStatusDate", "invoiceStatus", "invoiceStatusDate", "paymentStatus", "paymentStatusDate", "sourceId", "sourceBilling", "sourcePayment"})
public class DocumentStatusDTO {

	private String workStatus;
	private String workStatusDate;
	
	private String invoiceStatus;
	private String invoiceStatusDate;
	
	private String sourceId;
	private String sourceBilling;
	
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
		return sourceBilling;
	}

	public void setSourceBilling(String sourceBilling) {
		this.sourceBilling = sourceBilling;
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

	@XmlElement(name = "PaymentStatus")
	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@XmlElement(name = "PaymentStatusDate")
	public String getPaymentStatusDate() {
		return paymentStatusDate;
	}

	public void setPaymentStatusDate(String paymentStatusDate) {
		this.paymentStatusDate = paymentStatusDate;
	}

	@XmlElement(name = "SourcePayment")
	public String getSourcePayment() {
		return sourcePayment;
	}

	public void setSourcePayment(String sourcePayment) {
		this.sourcePayment = sourcePayment;
	}

}
