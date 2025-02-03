package ao.co.intellectus.DTO;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"invoiceNo", "documentStatus", "hash", "hashControl", "period", "invoiceDate", "invoiceType", "specialRegimes", "sourceId", "systemEntryDate", "customerId", "line", "documentTotals"})
public class InvoiceDTO {

	private String invoiceNo;
    private List<DocumentStatusDTO> documentStatus;
    private String hash;
    private String hashControl;
    private Integer period;
    private String invoiceDate;
    private String invoiceType;
    private List<SpecialRegimesDTO> specialRegimes;
    private String sourceId;
    private String systemEntryDate;
    private String customerId;
    private List<LineDTO> line;
    private List<DocumentTotalsDTO> documentTotals; //Falta
    
    
    @XmlElement(name = "InvoiceNo")
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	@XmlElement(name = "DocumentStatus")
	public List<DocumentStatusDTO> getDocumentStatus() {
		return documentStatus;
	}
	public void setDocumentStatus(List<DocumentStatusDTO> documentStatus) {
		this.documentStatus = documentStatus;
	}
	@XmlElement(name = "Hash")
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	@XmlElement(name = "HashControl")
	public String getHashControl() {
		return hashControl;
	}
	public void setHashControl(String hashControl) {
		this.hashControl = hashControl;
	}
	
	@XmlElement(name = "Period")
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
	@XmlElement(name = "InvoiceDate")
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	
	
	@XmlElement(name = "InvoiceType")
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	
	@XmlElement(name = "SpecialRegimes")
	public List<SpecialRegimesDTO> getSpecialRegimes() {
		return specialRegimes;
	}
	public void setSpecialRegimes(List<SpecialRegimesDTO> specialRegimes) {
		this.specialRegimes = specialRegimes;
	}
	@XmlElement(name = "SourceID")
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	@XmlElement(name = "SystemEntryDate")
	public String getSystemEntryDate() {
		return systemEntryDate;
	}
	public void setSystemEntryDate(String systemEntryDate) {
		this.systemEntryDate = systemEntryDate;
	}
	
	@XmlElement(name = "CustomerID")
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	@XmlElement(name = "Line")
	public List<LineDTO> getLine() {
		return line;
	}
	public void setLine(List<LineDTO> line) {
		this.line = line;
	}
	
	@XmlElement(name = "DocumentTotals")
	public List<DocumentTotalsDTO> getDocumentTotals() {
		return documentTotals;
	}
	public void setDocumentTotals(List<DocumentTotalsDTO> documentTotals) {
		this.documentTotals = documentTotals;
	}
}
