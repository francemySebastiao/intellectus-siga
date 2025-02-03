package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "t_document_total")
public class DocumentTotals {

	@Id
	private Integer id;
	private Double taxPayable;
    private Double netTotal;
    private Double grossTotal;
	/*@ManyToOne
	@JoinColumn(name = "payment")
    private Payment payment;*/

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

   /* @XmlElement(name = "Payment")
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}*/
}
