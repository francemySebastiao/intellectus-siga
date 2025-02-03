package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Grau;

public class ProcessamentoGuiaCliente {
	private Integer anoLectivo;
	private Date dataVencimento;
	private String mes;
	//private Integer codigoEmolumento;
	@Enumerated(EnumType.STRING)
	private Grau grau;
	private Integer tipoInscricao;
	private String turno;
	private Integer emolumentoNormal;
	//private String descricaoNormal;
	private Integer emolumentoInscricaoDisciplina;
	private String semestre;
	
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	/*public Integer getCodigoEmolumento() {
		return codigoEmolumento;
	}
	public void setCodigoEmolumento(Integer codigoEmolumento) {
		this.codigoEmolumento = codigoEmolumento;
	}*/
	public Grau getGrau() {
		return grau;
	}
	public void setGrau(Grau grau) {
		this.grau = grau;
	}
	
	public Integer getTipoInscricao() {
		return tipoInscricao;
	}
	public void setTipoInscricao(Integer tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public Integer getEmolumentoNormal() {
		return emolumentoNormal;
	}
	public void setEmolumentoNormal(Integer emolumentoNormal) {
		this.emolumentoNormal = emolumentoNormal;
	}
	/*public String getDescricaoNormal() {
		return descricaoNormal;
	}
	public void setDescricaoNormal(String descricaoNormal) {
		this.descricaoNormal = descricaoNormal;
	}*/
	public Integer getEmolumentoInscricaoDisciplina() {
		return emolumentoInscricaoDisciplina;
	}
	public void setEmolumentoInscricaoDisciplina(Integer emolumentoInscricaoDisciplina) {
		this.emolumentoInscricaoDisciplina = emolumentoInscricaoDisciplina;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	
	
	
}