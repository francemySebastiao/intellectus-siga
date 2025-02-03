package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_configuracao")
public class Configuracao {
	@Id
	@GeneratedValue 
	private Integer id;
	private Integer anoLectivoAtual;
	private Integer percentagemMulta;
	private Integer diasVencimentoGuia;
	private Integer diasGeracaoMulta;
	private String proximaGuia; 
	private Integer proximoDiploma;
	private String proximoAluno;
	private Integer proximoCandidato;
	private Integer proximaDeclaracao;
	private Integer limiteDiasAnulamentoGuia;
	private Integer limiteDeAlunosPorTurma;
	private Integer limiteValorParaContencioso;
	private Integer limiteParaContenciosoPorGuia;
	private Integer limiteAprovacaoFrequencia;
	private Integer cambio;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAnoLectivoAtual() {
		return anoLectivoAtual;
	}
	public void setAnoLectivoAtual(Integer anoLectivoAtual) {
		this.anoLectivoAtual = anoLectivoAtual;
	}
	public Integer getPercentagemMulta() {
		return percentagemMulta;
	}
	public void setPercentagemMulta(Integer percentagemMulta) {
		this.percentagemMulta = percentagemMulta;
	}
	public Integer getDiasVencimentoGuia() {
		return diasVencimentoGuia;
	}
	public void setDiasVencimentoGuia(Integer diasVencimentoGuia) {
		this.diasVencimentoGuia = diasVencimentoGuia;
	}
	public Integer getDiasGeracaoMulta() {
		return diasGeracaoMulta;
	}
	public void setDiasGeracaoMulta(Integer diasGeracaoMulta) {
		this.diasGeracaoMulta = diasGeracaoMulta;
	}
	public String getProximaGuia() {
		return proximaGuia;
	}
	public void setProximaGuia(String proximaGuia) {
		this.proximaGuia = proximaGuia;
	}
	public Integer getProximoDiploma() {
		return proximoDiploma;
	}
	public void setProximoDiploma(Integer proximoDiploma) {
		this.proximoDiploma = proximoDiploma;
	}
	public String getProximoAluno() {
		return proximoAluno;
	}
	public void setProximoAluno(String proximoAluno) {
		this.proximoAluno = proximoAluno;
	}
	public Integer getProximoCandidato() {
		return proximoCandidato;
	}
	public void setProximoCandidato(Integer proximoCandidato) {
		this.proximoCandidato = proximoCandidato;
	}
	public Integer getProximaDeclaracao() {
		return proximaDeclaracao;
	}
	public void setProximaDeclaracao(Integer proximaDeclaracao) {
		this.proximaDeclaracao = proximaDeclaracao;
	}
	public Integer getLimiteDiasAnulamentoGuia() {
		return limiteDiasAnulamentoGuia;
	}
	public void setLimiteDiasAnulamentoGuia(Integer limiteDiasAnulamentoGuia) {
		this.limiteDiasAnulamentoGuia = limiteDiasAnulamentoGuia;
	}
	public Integer getLimiteDeAlunosPorTurma() {
		return limiteDeAlunosPorTurma;
	}
	public void setLimiteDeAlunosPorTurma(Integer limiteDeAlunosPorTurma) {
		this.limiteDeAlunosPorTurma = limiteDeAlunosPorTurma;
	}
	public Integer getLimiteValorParaContencioso() {
		return limiteValorParaContencioso;
	}
	public void setLimiteValorParaContencioso(Integer limiteValorParaContencioso) {
		this.limiteValorParaContencioso = limiteValorParaContencioso;
	}
	public Integer getLimiteParaContenciosoPorGuia() {
		return limiteParaContenciosoPorGuia;
	}
	public void setLimiteParaContenciosoPorGuia(Integer limiteParaContenciosoPorGuia) {
		this.limiteParaContenciosoPorGuia = limiteParaContenciosoPorGuia;
	}
	public Integer getLimiteAprovacaoFrequencia() {
		return limiteAprovacaoFrequencia;
	}
	public void setLimiteAprovacaoFrequencia(Integer limiteAprovacaoFrequencia) {
		this.limiteAprovacaoFrequencia = limiteAprovacaoFrequencia;
	}
	public Integer getCambio() {
		return cambio;
	}
	public void setCambio(Integer cambio) {
		this.cambio = cambio;
	}
}