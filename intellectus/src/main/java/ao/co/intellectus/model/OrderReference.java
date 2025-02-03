package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "t_order_reference")
public class OrderReference {

	@Id
	private Integer id;
	@Column(name = "originating_on")
	private String originatingON;
	private String orderDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@XmlElement(name = "OriginatingON")
	public String getOriginatingON() {
		return originatingON;
	}
	
	public void setOriginatingON(String originatingON) {
		this.originatingON = originatingON;
	}
	@XmlElement(name = "OrderDate")
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
	
}
