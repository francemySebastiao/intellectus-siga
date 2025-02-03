package ao.co.intellectus.DTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"originatingON", "orderDate"})
public class OrderReferenceDTO {

	private String originatingON;
	private String orderDate;
	
	
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
