package ao.co.intellectus.DTO.proxpay;

import java.math.BigDecimal;
import java.util.Date;

public class PagamentosInterno {
	private Integer peridoContabilistico;
	private Date periodoContabilisticoInicio;
	private Date periodoContabilisticoFim;
	private BigDecimal valor;
	private CustomFields customFields;
	private Date dataPagamento;
	private String entidadeId;
	private String id;
	private String produtoId;
	private String referenciaId;
	private Integer referencia;
	private String terminalId;
	private String terminalLocalizacao;
	private String terminalTransacaoId;
	private String terminalTipo;
	private String transacaoId;
	public Integer getPeridoContabilistico() {
		return peridoContabilistico;
	}
	public void setPeridoContabilistico(Integer peridoContabilistico) {
		this.peridoContabilistico = peridoContabilistico;
	}
	public Date getPeriodoContabilisticoInicio() {
		return periodoContabilisticoInicio;
	}
	public void setPeriodoContabilisticoInicio(Date periodoContabilisticoInicio) {
		this.periodoContabilisticoInicio = periodoContabilisticoInicio;
	}
	public Date getPeriodoContabilisticoFim() {
		return periodoContabilisticoFim;
	}
	public void setPeriodoContabilisticoFim(Date periodoContabilisticoFim) {
		this.periodoContabilisticoFim = periodoContabilisticoFim;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public CustomFields getCustomFields() {
		return customFields;
	}
	public void setCustomFields(CustomFields customFields) {
		this.customFields = customFields;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public String getEntidadeId() {
		return entidadeId;
	}
	public void setEntidadeId(String entidadeId) {
		this.entidadeId = entidadeId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(String produtoId) {
		this.produtoId = produtoId;
	}
	public String getReferenciaId() {
		return referenciaId;
	}
	public void setReferenciaId(String referenciaId) {
		this.referenciaId = referenciaId;
	}
	public Integer getReferencia() {
		return referencia;
	}
	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getTerminalLocalizacao() {
		return terminalLocalizacao;
	}
	public void setTerminalLocalizacao(String terminalLocalizacao) {
		this.terminalLocalizacao = terminalLocalizacao;
	}
	public String getTerminalTransacaoId() {
		return terminalTransacaoId;
	}
	public void setTerminalTransacaoId(String terminalTransacaoId) {
		this.terminalTransacaoId = terminalTransacaoId;
	}
	public String getTerminalTipo() {
		return terminalTipo;
	}
	public void setTerminalTipo(String terminalTipo) {
		this.terminalTipo = terminalTipo;
	}
	public String getTransacaoId() {
		return transacaoId;
	}
	public void setTransacaoId(String transacaoId) {
		this.transacaoId = transacaoId;
	}
}
