package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "t_invoice")
public class Invoice {

	@Id
	private Integer id;
	private String invoiceNo;
	@ManyToOne
	@JoinColumn(name = "document_status")
    private DocumentStatus documentStatus;
    private String hash;
    private String hashControl;
    private String invoiceDate;
    private Integer period;
    private String invoiceType;
    @ManyToOne
	@JoinColumn(name = "special_regimes")
    private SpecialRegimes specialRegimes;
    private String sourceId;
    private String systemEntryDate;
    private String customerId;
    @ManyToOne
	@JoinColumn(name = "line")
    private Line line;
    @ManyToOne
	@JoinColumn(name = "document_total")
    private DocumentTotals documentTotals;
    private String dataEmissao;
    
    
    @XmlElement(name = "InvoiceNo")
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
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
	
	@XmlElement(name = "InvoiceDate")
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	@XmlElement(name = "Period")
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
	@XmlElement(name = "InvoiceType")
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	
	@XmlElement(name = "SpecialRegimes")
	public SpecialRegimes getSpecialRegimes() {
		return specialRegimes;
	}
	public void setSpecialRegimes(SpecialRegimes specialRegimes) {
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
    
    
    

    
    
}
