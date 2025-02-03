package ao.co.intellectus.DTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Header")
@XmlType(propOrder = {"auditFileVersion", "companyID", "taxRegistrationNumber", "taxAccountingBasis", "companyName", "companyAddress", "fiscalYear", "startDate", "endDate",
		"currencyCode", "dateCreated", "taxEntity", "productCompanyTaxId", "softwareValidationNumber", "productId", "productVersion", "telephone", "fax", "email", "webSite"})

public class HeaderDTO {

	
	private String auditFileVersion;
    private String companyID;
    private String TaxRegistrationNumber;
    private String taxAccountingBasis;
    private String companyName;
    private CompanyAddressDTO companyAddress;
    private String fiscalYear;
    private String startDate;
    private String endDate;
    private String currencyCode;
    private String dateCreated;
    private String taxEntity;
    private String productCompanyTaxId;
    private String softwareValidationNumber;
    private String productId;
    private String productVersion;
    private String telephone;
    private String fax;
    private String email;
    private String webSite;
    
	
    @XmlElement(name = "AuditFileVersion")
    public String getAuditFileVersion() {
		return auditFileVersion;
	}
	public void setAuditFileVersion(String auditFileVersion) {
		this.auditFileVersion = auditFileVersion;
	}
	
	@XmlElement(name = "CompanyID")
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	
	@XmlElement(name = "TaxRegistrationNumber")
	public String getTaxRegistrationNumber() {
		return TaxRegistrationNumber;
	}
	public void setTaxRegistrationNumber(String taxRegistrationNumber) {
		TaxRegistrationNumber = taxRegistrationNumber;
	}
	
	@XmlElement(name = "TaxAccountingBasis")
	public String getTaxAccountingBasis() {
		return taxAccountingBasis;
	}
	public void setTaxAccountingBasis(String taxAccountingBasis) {
		this.taxAccountingBasis = taxAccountingBasis;
	}
	
	@XmlElement(name = "CompanyName")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@XmlElement(name = "CompanyAddress")
	public CompanyAddressDTO getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(CompanyAddressDTO companyAddress) {
		this.companyAddress = companyAddress;
	}
	
	@XmlElement(name = "FiscalYear")
	public String getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	
	@XmlElement(name = "StartDate")
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	@XmlElement(name = "EndDate")
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	@XmlElement(name = "CurrencyCode")
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	@XmlElement(name = "DateCreated")
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@XmlElement(name = "TaxEntity")
	public String getTaxEntity() {
		return taxEntity;
	}
	public void setTaxEntity(String taxEntity) {
		this.taxEntity = taxEntity;
	}
	
	@XmlElement(name = "ProductCompanyTaxID")
	public String getProductCompanyTaxId() {
		return productCompanyTaxId;
	}
	public void setProductCompanyTaxId(String productCompanyTaxId) {
		this.productCompanyTaxId = productCompanyTaxId;
	}
	
	@XmlElement(name = "SoftwareValidationNumber")
	public String getSoftwareValidationNumber() {
		return softwareValidationNumber;
	}
	public void setSoftwareValidationNumber(String softwareValidationNumber) {
		this.softwareValidationNumber = softwareValidationNumber;
	}
	
	@XmlElement(name = "ProductID")
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@XmlElement(name = "ProductVersion")
	public String getProductVersion() {
		return productVersion;
	}
	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	
	
}
