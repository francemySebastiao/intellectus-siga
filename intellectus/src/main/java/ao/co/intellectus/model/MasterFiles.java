package ao.co.intellectus.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MasterFiles")
public class MasterFiles {

	private Customer customer;
	private List<Product> product;
	private TaxTable taxTable;
	
	
	@XmlElement(name = "Customer")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @XmlElement(name = "Product")
	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	@XmlElement(name = "TaxTable")
	public TaxTable getTaxTable() {
		return taxTable;
	}

	public void setTaxTable(TaxTable taxTable) {
		this.taxTable = taxTable;
	}
}
