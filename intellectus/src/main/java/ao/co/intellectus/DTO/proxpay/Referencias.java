package ao.co.intellectus.DTO.proxpay;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Referencias {
	@Temporal(TemporalType.DATE)
	private Date updated_at;
	private String status;
	private String number;
	private String id;
	@Temporal(TemporalType.DATE)
	private Date expiry_date;
	private Integer entity_id;
	private Filds custom_fields;
	@Temporal(TemporalType.DATE)
	private Date created_at;
	private double amount;
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}
	public Integer getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(Integer entity_id) {
		this.entity_id = entity_id;
	}
	public Filds getCustom_fields() {
		return custom_fields;
	}
	public void setCustom_fields(Filds custom_fields) {
		this.custom_fields = custom_fields;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
