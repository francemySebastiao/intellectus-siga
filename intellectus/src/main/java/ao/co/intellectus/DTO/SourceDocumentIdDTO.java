package ao.co.intellectus.DTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"originatingOn", "invoceDate"})
public class SourceDocumentIdDTO {

	private String originatingOn;
	private String invoceDate;

	@XmlElement(name = "OriginatingON")
	public String getOriginatingOn() {
		return originatingOn;
	}

	public void setOriginatingOn(String originatingOn) {
		this.originatingOn = originatingOn;
	}

	@XmlElement(name = "InvoiceDate")
	public String getInvoceDate() {
		return invoceDate;
	}

	public void setInvoceDate(String invoceDate) {
		this.invoceDate = invoceDate;
	}
}
