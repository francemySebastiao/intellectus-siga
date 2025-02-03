package ao.co.intellectus.DTO;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

public class PaymentMethodDTO {

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
