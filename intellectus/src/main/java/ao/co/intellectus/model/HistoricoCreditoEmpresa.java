package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.enumeracao.TipoFatura;

@Entity
@Table(name = "t_historico_credito_empresa")
public class HistoricoCreditoEmpresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 50)
	private String borderoInterno;
	private String borderoExterno;
	private float valorDeposito;
	@Temporal(TemporalType.DATE)
	private Date dataRegisto;
	@Temporal(TemporalType.DATE)
	private Date dataDepositoExterno;
	@ManyToOne
	@JoinColumn(name = "codigo_empresa")
	private EmpresaConvenio empresa;
	@ManyToOne
	@JoinColumn(name = "codigo_banco")
	private Banco banco;
	@ManyToOne
	@JoinColumn(name = "codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	@ManyToOne
	@JoinColumn(name = "codigo_bordero")
	private Bordero bordero;
	// AUDITORIA
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_emitiu")
	private Usuario usuarioEmitiu;

	private String hashFacturaRecibo;

	@Column(name = "n_facturaRecibo")
	private String numeroFacturaRecibo;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_factura")
	private TipoFatura tipoFactura;
	private String dataSistemaFr;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emissao_fr")
	private Date dataEmissaoFr;
	
	@Column(name = "liquidacao_agt")
	private Boolean liquidacaoAGT;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBorderoInterno() {
		return borderoInterno;
	}

	public void setBorderoInterno(String borderoInterno) {
		this.borderoInterno = borderoInterno;
	}

	public String getBorderoExterno() {
		return borderoExterno;
	}

	public void setBorderoExterno(String borderoExterno) {
		this.borderoExterno = borderoExterno;
	}

	public float getValorDeposito() {
		return valorDeposito;
	}

	public void setValorDeposito(float valorDeposito) {
		this.valorDeposito = valorDeposito;
	}

	public Date getDataRegisto() {
		return dataRegisto;
	}

	public void setDataRegisto(Date dataRegisto) {
		this.dataRegisto = dataRegisto;
	}

	public Date getDataDepositoExterno() {
		return dataDepositoExterno;
	}

	public void setDataDepositoExterno(Date dataDepositoExterno) {
		this.dataDepositoExterno = dataDepositoExterno;
	}

	public EmpresaConvenio getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaConvenio empresa) {
		this.empresa = empresa;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Bordero getBordero() {
		return bordero;
	}

	public void setBordero(Bordero bordero) {
		this.bordero = bordero;
	}

	public Usuario getUsuarioEmitiu() {
		return usuarioEmitiu;
	}

	public void setUsuarioEmitiu(Usuario usuarioEmitiu) {
		this.usuarioEmitiu = usuarioEmitiu;
	}

	public String getHashFacturaRecibo() {
		return hashFacturaRecibo;
	}

	public void setHashFacturaRecibo(String hashFacturaRecibo) {
		this.hashFacturaRecibo = hashFacturaRecibo;
	}

	public String getNumeroFacturaRecibo() {
		return numeroFacturaRecibo;
	}

	public void setNumeroFacturaRecibo(String numeroFacturaRecibo) {
		this.numeroFacturaRecibo = numeroFacturaRecibo;
	}

	public TipoFatura getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(TipoFatura tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public String getDataSistemaFr() {
		return dataSistemaFr;
	}

	public void setDataSistemaFr(String dataSistemaFr) {
		this.dataSistemaFr = dataSistemaFr;
	}

	public Date getDataEmissaoFr() {
		return dataEmissaoFr;
	}

	public void setDataEmissaoFr(Date dataEmissaoFr) {
		this.dataEmissaoFr = dataEmissaoFr;
	}

	public Boolean getLiquidacaoAGT() {
		return liquidacaoAGT;
	}

	public void setLiquidacaoAGT(Boolean liquidacaoAGT) {
		this.liquidacaoAGT = liquidacaoAGT;
	}
	
	
}
