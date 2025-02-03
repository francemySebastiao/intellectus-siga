package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_mensalidades_liquidadas")
public class MensalidadesLiquidadas {
	@Id
	@GeneratedValue
	private Long id;
	private Integer numeroDeAluno;
	//MESES
	private String mes;
	private float janeiro;
	@Temporal(TemporalType.DATE)
	private Date dataCreditoJaneiro;
	private float fevereiro;
	@Temporal(TemporalType.DATE)
	private Date dataCreditoFeveiro;
	private float marco;
	@Temporal(TemporalType.DATE)
	private Date dataCreditoMarco;
	private float abril;
	@Temporal(TemporalType.DATE)
	private Date dataCreditoAbril;
	private float maio;
	@Temporal(TemporalType.DATE)
	private Date dataCreditoMaio;
	private float junho;
	@Temporal(TemporalType.DATE)
	private Date dataCreditoJunhi;
	private float julho;
	@Temporal(TemporalType.DATE)
	private Date dataCreditoJulho;
	private float agosto;
	@Temporal(TemporalType.DATE)
	private Date dataCreditoAgosto;
	private float setembro;
	@Temporal(TemporalType.DATE)
	private Date dataCreditoSetembro;
	private float outubro;
	@Temporal(TemporalType.DATE)
	private Date dataCreditoOutubro;
	private float novembro;
	@Temporal(TemporalType.DATE)
	private Date dataCreditoNovembro;
	private float dezembro;
	@Temporal(TemporalType.DATE)
	private Date dataCreditoDezembro;
	//RESUMO DO DA PROPINA
	private boolean mesPago;
	private boolean temMulta;
	private float totalMes;
	private float propina;
    @Temporal(TemporalType.DATE)
	private Date dataVencientoPropina;
	private float totalPropinas;
	private float totalMultas;
	private float totalCreditos;
	private float totalEmDivida;
	//OUTROS EMOLUMENTOS
	private float exame;
	private float recursos;
	private float inscricao;
	private float equivalencia;
	private float acordoVencido;
	private float acordoNaoVencido;
	//CONEXÃO
	@ManyToOne
	@JoinColumn(name="codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	@ManyToOne
	@JoinColumn(name="codigo_aluno")
	private Aluno aluno;
	@ManyToOne
	@JoinColumn(name="codigo_emolumento")
	private Emolumento emolumento;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public float getJaneiro() {
		return janeiro;
	}
	public void setJaneiro(float janeiro) {
		this.janeiro = janeiro;
	}
	public Date getDataCreditoJaneiro() {
		return dataCreditoJaneiro;
	}
	public void setDataCreditoJaneiro(Date dataCreditoJaneiro) {
		this.dataCreditoJaneiro = dataCreditoJaneiro;
	}
	public float getFevereiro() {
		return fevereiro;
	}
	public void setFevereiro(float fevereiro) {
		this.fevereiro = fevereiro;
	}
	public Date getDataCreditoFeveiro() {
		return dataCreditoFeveiro;
	}
	public void setDataCreditoFeveiro(Date dataCreditoFeveiro) {
		this.dataCreditoFeveiro = dataCreditoFeveiro;
	}
	public float getMarco() {
		return marco;
	}
	public void setMarco(float marco) {
		this.marco = marco;
	}
	public Date getDataCreditoMarco() {
		return dataCreditoMarco;
	}
	public void setDataCreditoMarco(Date dataCreditoMarco) {
		this.dataCreditoMarco = dataCreditoMarco;
	}
	public float getAbril() {
		return abril;
	}
	public void setAbril(float abril) {
		this.abril = abril;
	}
	public Date getDataCreditoAbril() {
		return dataCreditoAbril;
	}
	public void setDataCreditoAbril(Date dataCreditoAbril) {
		this.dataCreditoAbril = dataCreditoAbril;
	}
	public float getMaio() {
		return maio;
	}
	public void setMaio(float maio) {
		this.maio = maio;
	}
	public Date getDataCreditoMaio() {
		return dataCreditoMaio;
	}
	public void setDataCreditoMaio(Date dataCreditoMaio) {
		this.dataCreditoMaio = dataCreditoMaio;
	}
	public float getJunho() {
		return junho;
	}
	public void setJunho(float junho) {
		this.junho = junho;
	}
	public Date getDataCreditoJunhi() {
		return dataCreditoJunhi;
	}
	public void setDataCreditoJunhi(Date dataCreditoJunhi) {
		this.dataCreditoJunhi = dataCreditoJunhi;
	}
	public float getJulho() {
		return julho;
	}
	public void setJulho(float julho) {
		this.julho = julho;
	}
	public Date getDataCreditoJulho() {
		return dataCreditoJulho;
	}
	public void setDataCreditoJulho(Date dataCreditoJulho) {
		this.dataCreditoJulho = dataCreditoJulho;
	}
	public float getAgosto() {
		return agosto;
	}
	public void setAgosto(float agosto) {
		this.agosto = agosto;
	}
	public Date getDataCreditoAgosto() {
		return dataCreditoAgosto;
	}
	public void setDataCreditoAgosto(Date dataCreditoAgosto) {
		this.dataCreditoAgosto = dataCreditoAgosto;
	}
	public float getSetembro() {
		return setembro;
	}
	public void setSetembro(float setembro) {
		this.setembro = setembro;
	}
	public Date getDataCreditoSetembro() {
		return dataCreditoSetembro;
	}
	public void setDataCreditoSetembro(Date dataCreditoSetembro) {
		this.dataCreditoSetembro = dataCreditoSetembro;
	}
	public float getOutubro() {
		return outubro;
	}
	public void setOutubro(float outubro) {
		this.outubro = outubro;
	}
	public Date getDataCreditoOutubro() {
		return dataCreditoOutubro;
	}
	public void setDataCreditoOutubro(Date dataCreditoOutubro) {
		this.dataCreditoOutubro = dataCreditoOutubro;
	}
	public float getNovembro() {
		return novembro;
	}
	public void setNovembro(float novembro) {
		this.novembro = novembro;
	}
	public Date getDataCreditoNovembro() {
		return dataCreditoNovembro;
	}
	public void setDataCreditoNovembro(Date dataCreditoNovembro) {
		this.dataCreditoNovembro = dataCreditoNovembro;
	}
	public float getDezembro() {
		return dezembro;
	}
	public void setDezembro(float dezembro) {
		this.dezembro = dezembro;
	}
	public Date getDataCreditoDezembro() {
		return dataCreditoDezembro;
	}
	public void setDataCreditoDezembro(Date dataCreditoDezembro) {
		this.dataCreditoDezembro = dataCreditoDezembro;
	}
	public boolean isMesPago() {
		return mesPago;
	}
	public void setMesPago(boolean mesPago) {
		this.mesPago = mesPago;
	}
	public boolean isTemMulta() {
		return temMulta;
	}
	public void setTemMulta(boolean temMulta) {
		this.temMulta = temMulta;
	}
	public float getTotalMes() {
		return totalMes;
	}
	public void setTotalMes(float totalMes) {
		this.totalMes = totalMes;
	}
	public float getTotalPropinas() {
		return totalPropinas;
	}
	public void setTotalPropinas(float totalPropinas) {
		this.totalPropinas = totalPropinas;
	}
	public float getTotalMultas() {
		return totalMultas;
	}
	public void setTotalMultas(float totalMultas) {
		this.totalMultas = totalMultas;
	}
	public float getTotalCreditos() {
		return totalCreditos;
	}
	public void setTotalCreditos(float totalCreditos) {
		this.totalCreditos = totalCreditos;
	}
	public float getTotalEmDivida() {
		return totalEmDivida;
	}
	public void setTotalEmDivida(float totalEmDivida) {
		this.totalEmDivida = totalEmDivida;
	}
	public float getExame() {
		return exame;
	}
	public void setExame(float exame) {
		this.exame = exame;
	}
	public float getRecursos() {
		return recursos;
	}
	public void setRecursos(float recursos) {
		this.recursos = recursos;
	}
	public float getInscricao() {
		return inscricao;
	}
	public void setInscricao(float inscricao) {
		this.inscricao = inscricao;
	}
	public float getEquivalencia() {
		return equivalencia;
	}
	public void setEquivalencia(float equivalencia) {
		this.equivalencia = equivalencia;
	}
	public float getAcordoVencido() {
		return acordoVencido;
	}
	public void setAcordoVencido(float acordoVencido) {
		this.acordoVencido = acordoVencido;
	}
	public float getAcordoNaoVencido() {
		return acordoNaoVencido;
	}
	public void setAcordoNaoVencido(float acordoNaoVencido) {
		this.acordoNaoVencido = acordoNaoVencido;
	}
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Emolumento getEmolumento() {
		return emolumento;
	}
	public void setEmolumento(Emolumento emolumento) {
		this.emolumento = emolumento;
	}
	public float getPropina() {
		return propina;
	}
	public void setPropina(float propina) {
		this.propina = propina;
	}
	public Date getDataVencientoPropina() {
		return dataVencientoPropina;
	}
	public void setDataVencientoPropina(Date dataVencientoPropina) {
		this.dataVencientoPropina = dataVencientoPropina;
	}
}
