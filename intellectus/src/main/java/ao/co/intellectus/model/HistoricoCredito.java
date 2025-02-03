package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.enumeracao.TipoFatura;

@Entity
@Table(name="t_historico_credito")
public class HistoricoCredito {
	@Id
	@GeneratedValue
    private Integer id; 
	@Column(length=50) 
	private String borderoInterno;
	private String borderoExterno; 
	private float valorDeposito;	
	@Temporal(TemporalType.DATE)
	private Date dataRegisto;
	@Temporal(TemporalType.DATE)
	private Date dataDepositoExterno;
	@ManyToOne
	@JoinColumn(name="codigo_aluno")
	private Aluno aluno;
	private Integer numeroDeAluno;
	@ManyToOne
	@JoinColumn(name="codigo_instituicao")
	private Instituicao instituicao; 
	@ManyToOne
	@JoinColumn(name="codigo_banco")
	private Banco banco;
	@ManyToOne
	@JoinColumn(name="codigo_moeda")
	private Moeda moeda;
	@ManyToOne
	@JoinColumn(name="codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	@ManyToOne
	@JoinColumn(name="codigo_bordero")	
	private Bordero bordero;
	//AUDITORIA
	@ManyToOne
	@JoinColumn(name="codigo_usuario_emitiu")
	private Usuario usuarioEmitiu;
	@ManyToOne
	@JoinColumn(name="codigo_usuario_liquidou")
	private Usuario usuarioLiquidou;
	@ManyToOne
	@JoinColumn(name="codigo_usuario_anulou")
	private Usuario usuarioAnulou;
	@ManyToOne
	@JoinColumn(name="codigo_usuario_acordo")
	private Usuario usuarioAcordo;
	//goza fedelho
	@Column(nullable=true)
	private boolean podeAnular;
	@Column(nullable=true)
	private boolean anulado;
	@Temporal(TemporalType.DATE)
	private Date dataAnulado;
	@Column(length=255)
	private String motivoDeAnulacao;
	@ManyToOne
	@JoinColumn(name="codigo_candidato")
	private Candidato candidato;
	@Enumerated(EnumType.STRING)
    private StatusCredito status;
	@Column(nullable=true)
	private float valorUsado;
	
	@ManyToOne
	@JoinColumn(name="codigo_empresa")
	private EmpresaConvenio empresaConvenio;
	
	/*@Column(name = "n_facturaRecibo")
	private String numeroFacturaRecibo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_factura")
	private TipoFatura tipoFactura;
	private String dataSistemaFr;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emissao_fr")
	private Date dataEmissaoFr;
	private String hashFacturaRecibo;
	@Column(name = "liquidacao_agt")
	private Boolean liquidacaoAGT;*/
	
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
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public Instituicao getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	public Banco getBanco() {
		return banco;
	}
	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	public Moeda getMoeda() {
		return moeda;
	}
	public void setMoeda(Moeda moeda) {
		this.moeda = moeda;
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
	public Usuario getUsuarioLiquidou() {
		return usuarioLiquidou;
	}
	public void setUsuarioLiquidou(Usuario usuarioLiquidou) {
		this.usuarioLiquidou = usuarioLiquidou;
	}
	public Usuario getUsuarioAnulou() {
		return usuarioAnulou;
	}
	public void setUsuarioAnulou(Usuario usuarioAnulou) {
		this.usuarioAnulou = usuarioAnulou;
	}
	public Usuario getUsuarioAcordo() {
		return usuarioAcordo;
	}
	public void setUsuarioAcordo(Usuario usuarioAcordo) {
		this.usuarioAcordo = usuarioAcordo;
	}
	public boolean isPodeAnular() {
		return podeAnular;
	}
	public void setPodeAnular(boolean podeAnular) {
		this.podeAnular = podeAnular;
	}
	public boolean isAnulado() {
		return anulado;
	}
	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}
	public Date getDataAnulado() {
		return dataAnulado;
	}
	public void setDataAnulado(Date dataAnulado) {
		this.dataAnulado = dataAnulado;
	}
	public String getMotivoDeAnulacao() {
		return motivoDeAnulacao;
	}
	public void setMotivoDeAnulacao(String motivoDeAnulacao) {
		this.motivoDeAnulacao = motivoDeAnulacao;
	}
	public Candidato getCandidato() {
		return candidato;
	}
	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}
	public StatusCredito getStatus() {
		return status;
	}
	public void setStatus(StatusCredito status) {
		this.status = status;
	}
	public float getValorUsado() {
		return valorUsado;
	}
	public void setValorUsado(float valorUsado) {
		this.valorUsado = valorUsado;
	}
	public EmpresaConvenio getEmpresaConvenio() {
		return empresaConvenio;
	}
	public void setEmpresaConvenio(EmpresaConvenio empresaConvenio) {
		this.empresaConvenio = empresaConvenio;
	}
	/*public String getNumeroFacturaRecibo() {
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
	public String getHashFacturaRecibo() {
		return hashFacturaRecibo;
	}
	public void setHashFacturaRecibo(String hashFacturaRecibo) {
		this.hashFacturaRecibo = hashFacturaRecibo;
	}
	public Boolean getLiquidacaoAGT() {
		return liquidacaoAGT;
	}
	public void setLiquidacaoAGT(Boolean liquidacaoAGT) {
		this.liquidacaoAGT = liquidacaoAGT;
	}
	
	*/
}
