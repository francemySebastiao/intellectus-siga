package ao.co.intellectus.DTO;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"numberOfEntries", "totalDebit", "totalCredit", "payment"})
public class PaymentsDTO {

	private Integer numberOfEntries;
	private Double totalDebit;
	private Double totalCredit;
	private List<PaymentDTO> payment;
	
	 @XmlElement(name = "NumberOfEntries")
	public Integer getNumberOfEntries() {
		return numberOfEntries;
	}
	public void setNumberOfEntries(Integer numberOfEntries) {
		this.numberOfEntries = numberOfEntries;
	}
	
	 @XmlElement(name = "TotalDebit")
	public Double getTotalDebit() {
		return totalDebit;
	}
	public void setTotalDebit(Double totalDebit) {
		this.totalDebit = totalDebit;
	}
	
	 @XmlElement(name = "TotalCredit")
	public Double getTotalCredit() {
		return totalCredit;
	}
	public void setTotalCredit(Double totalCredit) {
		this.totalCredit = totalCredit;
	}
	
	 @XmlElement(name = "Payment")
	public List<PaymentDTO> getPayment() {
		return payment;
	}
	public void setPayment(List<PaymentDTO> payment) {
		this.payment = payment;
	}
}
