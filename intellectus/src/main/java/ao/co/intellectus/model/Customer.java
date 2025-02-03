package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "t_customer")
public class Customer {

	@Id
	@Column(name = "customer_id")
	private String customerID;
	@Column(name = "account_id")
	private String AccountId;
	@Column(name = "customer_tax_id")
    private String customerTaxID;
	@Column(name = "company_name")
    private String companyName;
    @ManyToOne
	@JoinColumn(name = "billing_address")
    private BillingAddress billingAddress;
    @Column(name = "self_billing_inficator")
    private Integer selfBillingInficator;
    
 

	@XmlElement(name = "CustomerID")
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @XmlElement(name = "AccountID")
    public String getAccountId() {
		return AccountId;
	}

	public void setAccountId(String accountId) {
		AccountId = accountId;
	}

	@XmlElement(name = "CustomerTaxID")
    public String getCustomerTaxID() {
        return customerTaxID;
    }

    public void setCustomerTaxID(String customerTaxID) {
        this.customerTaxID = customerTaxID;
    }

    @XmlElement(name = "CompanyName")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    @XmlElement(name = "BillingAddress")
	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	@XmlElement(name = "SelfBillingIndicator")
	public Integer getSelfBillingInficator() {
		return selfBillingInficator;
	}

	public void setSelfBillingInficator(Integer selfBillingInficator) {
		this.selfBillingInficator = selfBillingInficator;
	}
	
	

    
}
