package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "t_work_document")
public class WorkDocument {

	@Id
	private Integer id;
	private String documentNumber;
	@ManyToOne
	@JoinColumn(name ="document_status")
	private DocumentStatus documentStatus;
	private String hash;
    private String hashControl;
    private String workDate;
    private String workType;
    private String sourceId;
    private String systemEntryDate;
    private String customerId;
    @ManyToOne
	@JoinColumn(name ="line")
    private Line line;
    @ManyToOne
	@JoinColumn(name ="document_totals")
    private DocumentTotals documentTotals;
    
    @XmlElement(name = "DocumentNumber")
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	
	@XmlElement(name = "DocumentStatus")
	public DocumentStatus getDocumentStatus() {
		return documentStatus;
	}
	public void setDocumentStatus(DocumentStatus documentStatus) {
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
	
	@XmlElement(name = "SpecialRegimes")
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	@XmlElement(name = "CustomerID")
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	@XmlElement(name = "Line")
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	
	@XmlElement(name = "DocumentTotals")
	public DocumentTotals getDocumentTotals() {
		return documentTotals;
	}
	public void setDocumentTotals(DocumentTotals documentTotals) {
		this.documentTotals = documentTotals;
	}
	public String getSystemEntryDate() {
		return systemEntryDate;
	}
	public void setSystemEntryDate(String systemEntryDate) {
		this.systemEntryDate = systemEntryDate;
	}
    
}
