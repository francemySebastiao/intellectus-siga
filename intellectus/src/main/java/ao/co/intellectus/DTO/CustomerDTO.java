package ao.co.intellectus.DTO;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"customerID", "accountId", "customerTaxID", "companyName", "billingAddress", "selfBillingIndicator"})
public class CustomerDTO {

	private String customerID;
	private String AccountId;
    private String customerTaxID;
    private String companyName;
    private List<BillingAddressDTO> billingAddress;
    private Integer selfBillingIndicator;
    
 

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
	public List<BillingAddressDTO> getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(List<BillingAddressDTO> billingAddress) {
		this.billingAddress = billingAddress;
	}

	@XmlElement(name = "SelfBillingIndicator")
	public Integer getSelfBillingIndicator() {
		return selfBillingIndicator;
	}

	public void setSelfBillingIndicator(Integer selfBillingIndicator) {
		this.selfBillingIndicator = selfBillingIndicator;
	}
}
