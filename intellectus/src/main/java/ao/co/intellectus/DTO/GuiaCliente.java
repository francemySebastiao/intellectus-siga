package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
public class GuiaCliente {
	private Integer id;
	private String numero;
	private String numeroProforma;
	private String numeroFacturaRecibo;
	@Temporal(TemporalType.DATE)
	private Date dataEmicao;
	private double valor;
	private boolean alterada;
	private boolean anulada;
	@Temporal(TemporalType.DATE)
	private Date dataAnulada;
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	private boolean liquidada;
	private boolean automaticamente;
	private boolean acordo;
	private Integer anoLectivo;
	private String anoLectivoDescricao;
	private double multa;
	private boolean liquidar;
	private String motivoAnulacaoGuia;
	private String motivoAnulacaoRecibo;
	//dados do bordero
	private String numeroBordero;
	@Temporal(TemporalType.DATE)
	private Date dataRegistro;
	private String registradoPor;
	private String emitidoPor;
	@Temporal(TemporalType.DATE)
	private Date dataDeposito;
	private String banco;
	private boolean requerimento;
	
	
	public boolean isRequerimento() {
		return requerimento;
	}
	public void setRequerimento(boolean requerimento) {
		this.requerimento = requerimento;
	}
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
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public Date getDataEmicao() {
		return dataEmicao;
	}
	public void setDataEmicao(Date dataEmicao) {
		this.dataEmicao = dataEmicao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public boolean isAnulada() {
		return anulada;
	}
	public void setAnulada(boolean anulada) {
		this.anulada = anulada;
	}
	public Date getDataAnulada() {
		return dataAnulada;
	}
	public void setDataAnulada(Date dataAnulada) {
		this.dataAnulada = dataAnulada;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public boolean isLiquidada() {
		return liquidada;
	}
	public void setLiquidada(boolean liquidada) {
		this.liquidada = liquidada;
	}
	public boolean isAutomaticamente() {
		return automaticamente;
	}
	public void setAutomaticamente(boolean automaticamente) {
		this.automaticamente = automaticamente;
	}
	public boolean isAcordo() {
		return acordo;
	}
	public void setAcordo(boolean acordo) {
		this.acordo = acordo;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public double getMulta() {
		return multa;
	}
	public void setMulta(double multa) {
		this.multa = multa;
	}
	public boolean isLiquidar() {
		return liquidar;
	}
	public void setLiquidar(boolean liquidar) {
		this.liquidar = liquidar;
	}
	public String getMotivoAnulacaoGuia() {
		return motivoAnulacaoGuia;
	}
	public void setMotivoAnulacaoGuia(String motivoAnulacaoGuia) {
		this.motivoAnulacaoGuia = motivoAnulacaoGuia;
	}
	public String getMotivoAnulacaoRecibo() {
		return motivoAnulacaoRecibo;
	}
	public void setMotivoAnulacaoRecibo(String motivoAnulacaoRecibo) {
		this.motivoAnulacaoRecibo = motivoAnulacaoRecibo;
	}
	public String getNumeroBordero() {
		return numeroBordero;
	}
	public void setNumeroBordero(String numeroBordero) {
		this.numeroBordero = numeroBordero;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public String getRegistradoPor() {
		return registradoPor;
	}
	public void setRegistradoPor(String registradoPor) {
		this.registradoPor = registradoPor;
	}
	public String getEmitidoPor() {
		return emitidoPor;
	}
	public void setEmitidoPor(String emitidoPor) {
		this.emitidoPor = emitidoPor;
	}
	public Date getDataDeposito() {
		return dataDeposito;
	}
	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getNumeroFacturaRecibo() {
		return numeroFacturaRecibo;
	}
	public void setNumeroFacturaRecibo(String numeroFacturaRecibo) {
		this.numeroFacturaRecibo = numeroFacturaRecibo;
	}
	public boolean isAlterada() {
		return alterada;
	}
	public void setAlterada(boolean alterada) {
		this.alterada = alterada;
	}
	public String getNumeroProforma() {
		return numeroProforma;
	}
	public void setNumeroProforma(String numeroProforma) {
		this.numeroProforma = numeroProforma;
	}
	
	
}
