package ao.co.intellectus.DTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"buildingNumber", "streetName", "addressDetail", "city", "postalCode", "country"})
public class BillingAddressDTO {

	
	private String buildingNumber;
	private String streetName;
	private String addressDetail;
	private String city;
	private String postalCode;
	//private String province;
	private String country;
	

	
	@XmlElement(name = "BuildingNumber")
	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	@XmlElement(name = "StreetName")
	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@XmlElement(name = "AddressDetail")
	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	@XmlElement(name = "City")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	

	@XmlElement(name = "PostalCode")
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/*@XmlElement(name = "Province")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}*/

	@XmlElement(name = "Country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
