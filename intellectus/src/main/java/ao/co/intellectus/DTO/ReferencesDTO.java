package ao.co.intellectus.DTO;

import javax.xml.bind.annotation.XmlElement;

public class ReferencesDTO {

	private String reference;

	@XmlElement(name = "Reference")
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
}
