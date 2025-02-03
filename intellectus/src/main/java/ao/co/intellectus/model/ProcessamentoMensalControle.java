package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="processamento_mensal_controle")
public class ProcessamentoMensalControle {
	@Id
	@GeneratedValue
	private Integer id;
	private boolean procAnoCompleto;
	private String anoCompleto;
	private boolean procAnoCompletoSemF;
	private String anoCompletoSemF;
	private boolean procAnoCompletoComF;
	private String anoCompletoComF;
	private boolean procPorDisciplina;
	private String porDisciplina;
	private Integer totalProcessado;
	private Integer totalComDesconto;
	private Integer anoLectivo;
	private String mes;
	private Integer totalAnoCompleto;
	private Integer totalAnoCompletoSemFrq;
	private Integer totalAnoCompletoComFreq;
	private Integer totalPorDisciplina;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isProcAnoCompleto() {
		return procAnoCompleto;
	}
	public void setProcAnoCompleto(boolean procAnoCompleto) {
		this.procAnoCompleto = procAnoCompleto;
	}
	public String getAnoCompleto() {
		return anoCompleto;
	}
	public void setAnoCompleto(String anoCompleto) {
		this.anoCompleto = anoCompleto;
	}
	public boolean isProcAnoCompletoSemF() {
		return procAnoCompletoSemF;
	}
	public void setProcAnoCompletoSemF(boolean procAnoCompletoSemF) {
		this.procAnoCompletoSemF = procAnoCompletoSemF;
	}
	public String getAnoCompletoSemF() {
		return anoCompletoSemF;
	}
	public void setAnoCompletoSemF(String anoCompletoSemF) {
		this.anoCompletoSemF = anoCompletoSemF;
	}
	public boolean isProcAnoCompletoComF() {
		return procAnoCompletoComF;
	}
	public void setProcAnoCompletoComF(boolean procAnoCompletoComF) {
		this.procAnoCompletoComF = procAnoCompletoComF;
	}
	public String getAnoCompletoComF() {
		return anoCompletoComF;
	}
	public void setAnoCompletoComF(String anoCompletoComF) {
		this.anoCompletoComF = anoCompletoComF;
	}
	public boolean isProcPorDisciplina() {
		return procPorDisciplina;
	}
	public void setProcPorDisciplina(boolean procPorDisciplina) {
		this.procPorDisciplina = procPorDisciplina;
	}
	public String getPorDisciplina() {
		return porDisciplina;
	}
	public void setPorDisciplina(String porDisciplina) {
		this.porDisciplina = porDisciplina;
	}
	public Integer getTotalProcessado() {
		return totalProcessado;
	}
	public void setTotalProcessado(Integer totalProcessado) {
		this.totalProcessado = totalProcessado;
	}
	public Integer getTotalComDesconto() {
		return totalComDesconto;
	}
	public void setTotalComDesconto(Integer totalComDesconto) {
		this.totalComDesconto = totalComDesconto;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public Integer getTotalAnoCompleto() {
		return totalAnoCompleto;
	}
	public void setTotalAnoCompleto(Integer totalAnoCompleto) {
		this.totalAnoCompleto = totalAnoCompleto;
	}
	public Integer getTotalAnoCompletoSemFrq() {
		return totalAnoCompletoSemFrq;
	}
	public void setTotalAnoCompletoSemFrq(Integer totalAnoCompletoSemFrq) {
		this.totalAnoCompletoSemFrq = totalAnoCompletoSemFrq;
	}
	public Integer getTotalAnoCompletoComFreq() {
		return totalAnoCompletoComFreq;
	}
	public void setTotalAnoCompletoComFreq(Integer totalAnoCompletoComFreq) {
		this.totalAnoCompletoComFreq = totalAnoCompletoComFreq;
	}
	public Integer getTotalPorDisciplina() {
		return totalPorDisciplina;
	}
	public void setTotalPorDisciplina(Integer totalPorDisciplina) {
		this.totalPorDisciplina = totalPorDisciplina;
	}
}
