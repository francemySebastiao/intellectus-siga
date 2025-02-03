package ao.co.intellectus.DTO;

import java.util.Date;

import ao.co.intellectus.model.enumeracao.TipoDoc;

public class NotaCreditoEmpresaCliente {

	
	private Long id;
	private Integer codigoEmpresa;
	private String nomeEmpresa;
	private String numeroNotaCredito;
	private Integer idGuia;
	private Integer idGuiaCandidatura;
	private Long idFactura;
	private Long idRecibo;
	private Long idNotaCredito;
	private TipoDoc tipoDoc;
	private Date dataEmissao;
	private Integer usuarioEmitiu;
	private Double valor;
	private Boolean alteracao;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroNotaCredito() {
		return numeroNotaCredito;
	}
	public void setNumeroNotaCredito(String numeroNotaCredito) {
		this.numeroNotaCredito = numeroNotaCredito;
	}
	public Integer getIdGuia() {
		return idGuia;
	}
	public void setIdGuia(Integer idGuia) {
		this.idGuia = idGuia;
	}
	public Integer getIdGuiaCandidatura() {
		return idGuiaCandidatura;
	}
	public void setIdGuiaCandidatura(Integer idGuiaCandidatura) {
		this.idGuiaCandidatura = idGuiaCandidatura;
	}
	public Long getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}
	public Long getIdRecibo() {
		return idRecibo;
	}
	public void setIdRecibo(Long idRecibo) {
		this.idRecibo = idRecibo;
	}
	public TipoDoc getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(TipoDoc tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Integer getUsuarioEmitiu() {
		return usuarioEmitiu;
	}
	public void setUsuarioEmitiu(Integer usuarioEmitiu) {
		this.usuarioEmitiu = usuarioEmitiu;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Boolean getAlteracao() {
		return alteracao;
	}
	public void setAlteracao(Boolean alteracao) {
		this.alteracao = alteracao;
	}
	public Long getIdNotaCredito() {
		return idNotaCredito;
	}
	public void setIdNotaCredito(Long idNotaCredito) {
		this.idNotaCredito = idNotaCredito;
	}
	public Integer getCodigoEmpresa() {
		return codigoEmpresa;
	}
	public void setCodigoEmpresa(Integer codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	
	
	
}
