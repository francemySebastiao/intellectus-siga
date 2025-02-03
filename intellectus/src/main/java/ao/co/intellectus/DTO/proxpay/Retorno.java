package ao.co.intellectus.DTO.proxpay;

import java.util.List;

public class Retorno {
	private List<Referencias> references;
	private Meta meta;

	public List<Referencias> getReferences() {
		return references;
	}

	public void setReferences(List<Referencias> references) {
		this.references = references;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}
}
