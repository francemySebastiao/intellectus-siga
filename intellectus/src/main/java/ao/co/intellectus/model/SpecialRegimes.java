package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;


@Entity
@Table(name = "t_special_regime")
public class SpecialRegimes {

	@Id
	private Integer id;
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
