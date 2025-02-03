package ao.co.intellectus.DTO;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "MasterFiles")
@XmlType(propOrder = {"customer", "product", "taxTable"})
public class MasterFileDTO {

	private List<CustomerDTO> customer;
	private List<ProductDTO> product;
	private TaxTableDTO taxTable;
	
	

	@XmlElement(name = "Product")
	public List<ProductDTO> getProduct() {
		return product;
	}

	public void setProduct(List<ProductDTO> product) {
		this.product = product;
	}

	@XmlElement(name = "Customer")
	public List<CustomerDTO> getCustomer() {
		return customer;
	}

	public void setCustomer(List<CustomerDTO> customer) {
		this.customer = customer;
	}

	@XmlElement(name = "TaxTable")
	public TaxTableDTO getTaxTable() {
		return taxTable;
	}

	public void setTaxTable(TaxTableDTO taxTable) {
		this.taxTable = taxTable;
	}
        
}
