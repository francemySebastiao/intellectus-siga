package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "t_sale_invoice")
public class SalesInvoices {

	@Id
	private Integer id;
	private Integer numberOfEntries;
	private Double totalDebit;
	private Double totalCredit;
	@ManyToOne
	@JoinColumn(name ="invoice")
	private Invoice invoice;
	
	private Boolean anulado;
	
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
	
	@XmlElement(name = "Invoice")
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getAnulado() {
		return anulado;
	}
	public void setAnulado(Boolean anulado) {
		this.anulado = anulado;
	}
	
	
}
