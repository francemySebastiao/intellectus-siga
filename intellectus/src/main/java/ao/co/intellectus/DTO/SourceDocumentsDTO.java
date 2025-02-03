package ao.co.intellectus.DTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "SourceDocuments")
@XmlType(propOrder = {"salesInvoices", "workingDocuments", "payments"})
public class SourceDocumentsDTO {

	private SalesInvoiceDTO salesInvoices;
	private WorkDocumentsDTO workingDocuments;
	private PaymentsDTO payments;
	
	
	@XmlElement(name = "SalesInvoices")
	public SalesInvoiceDTO getSalesInvoices() {
		return salesInvoices;
	}

	public void setSalesInvoices(SalesInvoiceDTO salesInvoices) {
		this.salesInvoices = salesInvoices;
	}

	@XmlElement(name = "WorkingDocuments")
	public WorkDocumentsDTO getWorkingDocuments() {
		return workingDocuments;
	}

	public void setWorkingDocuments(WorkDocumentsDTO workingDocuments) {
		this.workingDocuments = workingDocuments;
	}

	@XmlElement(name = "Payments")
	public PaymentsDTO getPayments() {
		return payments;
	}

	public void setPayments(PaymentsDTO payments) {
		this.payments = payments;
	}
	
	
	
}
