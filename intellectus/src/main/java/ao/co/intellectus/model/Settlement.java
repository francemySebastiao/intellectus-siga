package ao.co.intellectus.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "t_payment")
public class Settlement {

	@Id
	private Integer id;
	private BigDecimal settlementAmount;

	@XmlElement(name = "SettlementAmount")
	public BigDecimal getSettlementAmount() {
		return settlementAmount;
	}

	public void setSettlementAmount(BigDecimal settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
	
	
}
