package ao.co.intellectus.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AuditFileDTO", namespace = "urn:OECD:StandardAuditFile-Tax:AO_1.01_01")
public class AuditFile {

	private Header header;
	private MasterFiles masterFiles;
	private SourceDocuments sourceDocuments;

	@XmlElement(name = "Header")
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	@XmlElement(name = "MasterFiles")
	public MasterFiles getMasterFiles() {
		return masterFiles;
	}

	public void setMasterFiles(MasterFiles masterFiles) {
		this.masterFiles = masterFiles;
	}

	@XmlElement(name = "SourceDocuments")
	public SourceDocuments getSourceDocuments() {
		return sourceDocuments;
	}

	public void setSourceDocuments(SourceDocuments sourceDocuments) {
		this.sourceDocuments = sourceDocuments;
	}

	
}
