package ao.co.intellectus.DTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"selfBillingIndicator", "cashVatSchemeIndicator", "thirdPartiesBillingIndicator"})
public class SpecialRegimesDTO {

	private Integer selfBillingIndicator;
	private Integer cashVatSchemeIndicator;
	private Integer thirdPartiesBillingIndicator;
	
	@XmlElement(name = "SelfBillingIndicator")
	public Integer getSelfBillingIndicator() {
		return selfBillingIndicator;
	}
	public void setSelfBillingIndicator(Integer selfBillingIndicator) {
		this.selfBillingIndicator = selfBillingIndicator;
	}
	
	@XmlElement(name = "CashVATSchemeIndicator")
	public Integer getCashVatSchemeIndicator() {
		return cashVatSchemeIndicator;
	}
	public void setCashVatSchemeIndicator(Integer cashVatSchemeIndicator) {
		this.cashVatSchemeIndicator = cashVatSchemeIndicator;
	}
	
	@XmlElement(name = "ThirdPartiesBillingIndicator")
	public Integer getThirdPartiesBillingIndicator() {
		return thirdPartiesBillingIndicator;
	}
	public void setThirdPartiesBillingIndicator(Integer thirdPartiesBillingIndicator) {
		this.thirdPartiesBillingIndicator = thirdPartiesBillingIndicator;
	}
}
