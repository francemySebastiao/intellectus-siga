package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "t_tax_table")
public class TaxTable {

	@Id
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "tax_table_entry")
	private TaxTableEntry taxTableEntry;

	@XmlElement(name = "TaxTableEntry")
	public TaxTableEntry getTaxTableEntry() {
		return taxTableEntry;
	}

	public void setTaxTableEntry(TaxTableEntry taxTableEntry) {
		this.taxTableEntry = taxTableEntry;
	}
	

		
	
}
