package ao.co.intellectus.DTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "AuditFile", namespace = "urn:OECD:StandardAuditFile-Tax:AO_1.01_01")
@XmlType(propOrder = {"header", "masterFiles", "sourceDocuments"})
public class AuditFileDTO {

	private HeaderDTO header;
	private MasterFileDTO masterFiles;
	private SourceDocumentsDTO sourceDocuments;
	
	@XmlElement(name = "Header")
	public HeaderDTO getHeader() {
		return header;
	}

	public void setHeader(HeaderDTO header) {
		this.header = header;
	}

	@XmlElement(name = "MasterFiles")
	public MasterFileDTO getMasterFiles() {
		return masterFiles;
	}

	public void setMasterFiles(MasterFileDTO masterFiles) {
		this.masterFiles = masterFiles;
	}

	@XmlElement(name = "SourceDocuments")
	public SourceDocumentsDTO getSourceDocuments() {
		return sourceDocuments;
	}

	public void setSourceDocuments(SourceDocumentsDTO sourceDocuments) {
		this.sourceDocuments = sourceDocuments;
	}
	
	
}
