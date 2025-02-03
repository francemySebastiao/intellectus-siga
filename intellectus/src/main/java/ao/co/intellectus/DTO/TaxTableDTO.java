package ao.co.intellectus.DTO;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class TaxTableDTO {

	private List<TaxTableEntryDTO> taxTableEntry;

	@XmlElement(name = "TaxTableEntry")
	public List<TaxTableEntryDTO> getTaxTableEntry() {
		return taxTableEntry;
	}

	public void setTaxTableEntry(List<TaxTableEntryDTO> taxTableEntry) {
		this.taxTableEntry = taxTableEntry;
	}
}
