package ao.co.intellectus.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SourceDocuments")
public class SourceDocuments {

	private SalesInvoices salesInvoice;
	private WorkingDocuments workDocuments;
	private Payments payments;

	@XmlElement(name = "SalesInvoices")
	public SalesInvoices getSalesInvoice() {
		return salesInvoice;
	}

	public void setSalesInvoice(SalesInvoices salesInvoice) {
		this.salesInvoice = salesInvoice;
	}

	@XmlElement(name = "WorkDocuments")
	public WorkingDocuments getWorkDocuments() {
		return workDocuments;
	}

	public void setWorkDocuments(WorkingDocuments workDocuments) {
		this.workDocuments = workDocuments;
	}

	@XmlElement(name = "Payments")
	public Payments getPayments() {
		return payments;
	}

	public void setPayments(Payments payments) {
		this.payments = payments;
	}
	
	
	
	
}
