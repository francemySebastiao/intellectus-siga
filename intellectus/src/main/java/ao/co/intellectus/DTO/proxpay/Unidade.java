package ao.co.intellectus.DTO.proxpay;

public class Unidade {
	private double amount;
	private String expiry_date;
	private Filds custom_fields;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public Filds getCustom_fields() {
		return custom_fields;
	}
	public void setCustom_fields(Filds custom_fields) {
		this.custom_fields = custom_fields;
	}
}
