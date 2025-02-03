package ao.co.intellectus.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "t_payment_method")
public class PaymentMethod {

	@Id
	private Integer id;
	private String paymentMechanism;
	private BigDecimal paymentAmount;
	private String paymentDate;
	
	
	@XmlElement(name = "PaymentMechanism")
	public String getPaymentMechanism() {
		return paymentMechanism;
	}
	public void setPaymentMechanism(String paymentMechanism) {
		this.paymentMechanism = paymentMechanism;
	}
	
	@XmlElement(name = "PaymentAmount")
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
	@XmlElement(name = "PaymentDate")
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
	
}
