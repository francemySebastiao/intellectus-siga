package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "t_work_documents")
public class WorkingDocuments {

	@Id
	private Integer id;
	private Integer numberOfEntries;
	private Double totalDebit;
	private Double totalCredit;
	@ManyToOne
	@JoinColumn(name ="work_document")
	private WorkDocument workDocument;

	@XmlElement(name = "NumberOfEntries")
	public Integer getNumberOfEntries() {
		return numberOfEntries;
	}

	public void setNumberOfEntries(Integer numberOfEntries) {
		this.numberOfEntries = numberOfEntries;
	}

	@XmlElement(name = "TotalDebit")
	public Double getTotalDebit() {
		return totalDebit;
	}

	public void setTotalDebit(Double totalDebit) {
		this.totalDebit = totalDebit;
	}

	@XmlElement(name = "TotalCredit")
	public Double getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(Double totalCredit) {
		this.totalCredit = totalCredit;
	}

	@XmlElement(name = "WorkDocument")
	public WorkDocument getWorkDocument() {
		return workDocument;
	}

	public void setWorkDocument(WorkDocument workDocument) {
		this.workDocument = workDocument;
	}

		
	
	
	
	
}
