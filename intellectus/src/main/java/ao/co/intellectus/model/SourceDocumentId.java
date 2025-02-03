package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "t_source_document_id")
public class SourceDocumentId {

	@Id
	private Integer id;
	private String originatingOn;
	private String invoiceDate;

	@XmlElement(name = "OriginatingON")
	public String getOriginatingOn() {
		return originatingOn;
	}

	public void setOriginatingOn(String originatingOn) {
		this.originatingOn = originatingOn;
	}

	@XmlElement(name = "InvoiceDate")
	public String getInvoceDate() {
		return invoiceDate;
	}

	public void setInvoceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

}
