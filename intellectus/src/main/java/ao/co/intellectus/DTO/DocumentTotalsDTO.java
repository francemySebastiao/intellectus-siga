package ao.co.intellectus.DTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"taxPayable", "netTotal", "grossTotal"})
public class DocumentTotalsDTO {

	private Double taxPayable;
    private Double netTotal;
    private Double grossTotal;
    //private PaymentDTO payment;
    
    @XmlElement(name = "TaxPayable")
    public Double getTaxPayable() {
        return taxPayable;
    }

    public void setTaxPayable(Double taxPayable) {
        this.taxPayable = taxPayable;
    }

    @XmlElement(name = "NetTotal")
    public Double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(Double netTotal) {
        this.netTotal = netTotal;
    }

    @XmlElement(name = "GrossTotal")
    public Double getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(Double grossTotal) {
        this.grossTotal = grossTotal;
    }

    /*@XmlElement(name = "Payment")
	public PaymentDTO getPayment() {
		return payment;
	}

	public void setPayment(PaymentDTO payment) {
		this.payment = payment;
	}*/
    
}
