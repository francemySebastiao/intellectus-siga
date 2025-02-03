package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.TipoDisciplina;

public class AutorizacaoCliente {
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	@Temporal(TemporalType.DATE)
	private Date dataFim;
	@Enumerated(EnumType.STRING)
	private TipoDisciplina tipoDisciplina;
	private String tipoDisciplinaString;
	private Integer anoLectivo;
	private String anoLectivoDescricao;
	private String prova;
	private boolean emCurso;
	private String grau;
	
	public String getAnoLectivoDescricao() {
		return anoLectivoDescricao;
	}
	public void setAnoLectivoDescricao(String anoLectivoDescricao) {
		this.anoLectivoDescricao = anoLectivoDescricao;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public TipoDisciplina getTipoDisciplina() {
		return tipoDisciplina;
	}
	public void setTipoDisciplina(TipoDisciplina tipoDisciplina) {
		this.tipoDisciplina = tipoDisciplina;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public String getProva() {
		return prova;
	}
	public void setProva(String prova) {
		this.prova = prova;
	}
	public boolean isEmCurso() {
		return emCurso;
	}
	public void setEmCurso(boolean emCurso) {
		this.emCurso = emCurso;
	}
	public String getTipoDisciplinaString() {
		return tipoDisciplinaString;
	}
	public void setTipoDisciplinaString(String tipoDisciplinaString) {
		this.tipoDisciplinaString = tipoDisciplinaString;
	}
	public String getGrau() {
		return grau;
	}
	public void setGrau(String grau) {
		this.grau = grau;
	}
}
