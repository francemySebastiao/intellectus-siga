package ao.co.intellectus.DTO;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

public class SettlementDTO {

	private BigDecimal settlementAmount;

	@XmlElement(name = "SettlementAmount")
	public BigDecimal getSettlementAmount() {
		return settlementAmount;
	}

	public void setSettlementAmount(BigDecimal settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
}
