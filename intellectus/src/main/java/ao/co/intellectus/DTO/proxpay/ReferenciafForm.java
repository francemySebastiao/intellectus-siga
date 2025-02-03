package ao.co.intellectus.DTO.proxpay;

import java.math.BigDecimal;

public class ReferenciafForm {
	private String expiry_date;
	private BigDecimal amount;
	private CustomFields custom_fields;
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public CustomFields getCustom_fields() {
		return custom_fields;
	}
	public void setCustom_fields(CustomFields custom_fields) {
		this.custom_fields = custom_fields;
	}
}
