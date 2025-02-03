package ao.co.intellectus.DTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"productType", "productCode", "productGroup", "productDescription", "productNumberCode"})
public class ProductDTO {

	private String productType;
	private String productCode;
	private String productGroup;
	private String productDescription;
	private String productNumberCode;
	
	@XmlElement(name = "ProductType")
	public String getProductType() {
		return productType;
	}
	
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	@XmlElement(name = "ProductCode")
	public String getProductCode() {
		return productCode;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	@XmlElement(name = "ProductGroup")
	public String getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}
	
	
	@XmlElement(name = "ProductDescription")
	public String getProductDescription() {
		return productDescription;
	}
	
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	@XmlElement(name = "ProductNumberCode")
	public String getProductNumberCode() {
		return productNumberCode;
	}
	
	public void setProductNumberCode(String productNumberCode) {
		this.productNumberCode = productNumberCode;
	}
	
}
