package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_payment_document_total")
public class PaymentDocumentTotals {

	@Id
	private Integer id;
	private Double taxPayable;
    private Double netTotal;
    private Double grossTotal;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getTaxPayable() {
		return taxPayable;
	}
	public void setTaxPayable(Double taxPayable) {
		this.taxPayable = taxPayable;
	}
	public Double getNetTotal() {
		return netTotal;
	}
	public void setNetTotal(Double netTotal) {
		this.netTotal = netTotal;
	}
	public Double getGrossTotal() {
		return grossTotal;
	}
	public void setGrossTotal(Double grossTotal) {
		this.grossTotal = grossTotal;
	}
    
    
}
