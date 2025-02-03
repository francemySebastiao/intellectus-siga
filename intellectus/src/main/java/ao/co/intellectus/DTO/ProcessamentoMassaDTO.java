package ao.co.intellectus.DTO;

import java.util.Date;
import java.util.List;

import ao.co.intellectus.model.Grau;

public class ProcessamentoMassaDTO {

	private Integer anoLectivo;
	private Grau grau;
	private String mes;
	private Date dataVencimento;
	private String tipoInscricao;
	//private Integer propinaNormal;
	//private String nomeEmolumento;
	//private Integer propinaDisc;
	//private String nomeEmolumentoDisc;
	private List<ProcessamentoMassaEmolDTO> processado;
	
	
	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Grau getGrau() {
		return grau;
	}

	public void setGrau(Grau grau) {
		this.grau = grau;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getTipoInscricao() {
		return tipoInscricao;
	}

	public void setTipoInscricao(String tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}

	/*public Integer getPropinaNormal() {
		return propinaNormal;
	}

	public void setPropinaNormal(Integer propinaNormal) {
		this.propinaNormal = propinaNormal;
	}

	public String getNomeEmolumento() {
		return nomeEmolumento;
	}

	public void setNomeEmolumento(String nomeEmolumento) {
		this.nomeEmolumento = nomeEmolumento;
	}

	public Integer getPropinaDisc() {
		return propinaDisc;
	}

	public void setPropinaDisc(Integer propinaDisc) {
		this.propinaDisc = propinaDisc;
	}

	public String getNomeEmolumentoDisc() {
		return nomeEmolumentoDisc;
	}

	public void setNomeEmolumentoDisc(String nomeEmolumentoDisc) {
		this.nomeEmolumentoDisc = nomeEmolumentoDisc;
	}*/

	public List<ProcessamentoMassaEmolDTO> getProcessado() {
		return processado;
	}

	public void setProcessado(List<ProcessamentoMassaEmolDTO> processado) {
		this.processado = processado;
	}
	
	

}
