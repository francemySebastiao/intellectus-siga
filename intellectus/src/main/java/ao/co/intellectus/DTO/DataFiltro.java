package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class DataFiltro {

	@Temporal(TemporalType.DATE)
	private Date de;
	@Temporal(TemporalType.DATE)
	private Date para;

	public Date getDe() {
		return de;
	}

	public void setDe(Date de) {
		this.de = de;
	}

	public Date getPara() {
		return para;
	}

	public void setPara(Date para) {
		this.para = para;
	}

}
