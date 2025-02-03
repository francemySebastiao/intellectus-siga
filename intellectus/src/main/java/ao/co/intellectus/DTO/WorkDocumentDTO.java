package ao.co.intellectus.DTO;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"documentNumber", "documentStatus", "hash", "hashControl", "workDate", "workType", "sourceId", "systemEntryDate", "customerId", "line", "documentTotals"})
public class WorkDocumentDTO {

	private String documentNumber;
	private List<DocumentStatusDTO> documentStatus;
	private String hash;
    private String hashControl;
    private String workDate;
    private String workType;
    private String sourceId;
    private String systemEntryDate;
    private String customerId;
    private List<LineDTO> line;
    private List<DocumentTotalsDTO> documentTotals;
    
   
    
    @XmlElement(name = "DocumentNumber")
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
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
	
	@XmlElement(name = "WorkDate")
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	
	@XmlElement(name = "WorkType")
	public String getWorkType() {
		return workType;
	}
	
	public void setWorkType(String workType) {
		this.workType = workType;
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
	
	@XmlElement(name = "DocumentTotals")
	public List<DocumentTotalsDTO> getDocumentTotals() {
		return documentTotals;
	}
	public void setDocumentTotals(List<DocumentTotalsDTO> documentTotals) {
		this.documentTotals = documentTotals;
	}
	
	
}
