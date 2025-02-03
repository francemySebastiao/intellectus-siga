package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name="t_product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "product_code")
	private String productCode;
	@Column(name = "product_type")
	private String productType;
	@Column(name = "product_description")
	private String productDescription;
	@Column(name = "product_number_code")
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
