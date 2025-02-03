package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_mapa_mensalidade")
public class MapaMensalidadeAluno {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "codigo_aluno")
	private Aluno aluno;
	private Integer numeroDeAluno;
	@ManyToOne
	@JoinColumn(name = "codigo_tipo_inscricao")
	private TipoInscricao tipoInscricao;
	@ManyToOne
	@JoinColumn(name = "codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	@Temporal(TemporalType.DATE)
	private Date DataInscricao;
	@Column(nullable=true)
	private Float matricula;
	@Column(nullable=true)
	private Float janeiro;
	@Column(nullable=true)
	private Float fereiro;
	@Column(nullable=true)
	private Float marco;
	@Column(nullable=true)
	private Float abril;
	@Column(nullable=true)
	private Float maio;
	@Column(nullable=true)
	private Float junho;
	@Column(nullable=true)
	private Float julho;
	@Column(nullable=true)
	private Float agosto;
	@Column(nullable=true)
	private Float setembro;
	@Column(nullable=true)
	private Float outubro;
	@Column(nullable=true)
	private Float novembro;
	@Column(nullable=true)
	private Float dezembro;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public TipoInscricao getTipoInscricao() {
		return tipoInscricao;
	}
	public void setTipoInscricao(TipoInscricao tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Date getDataInscricao() {
		return DataInscricao;
	}
	public void setDataInscricao(Date dataInscricao) {
		DataInscricao = dataInscricao;
	}
	public Float getMatricula() {
		return matricula;
	}
	public void setMatricula(Float matricula) {
		this.matricula = matricula;
	}
	public Float getJaneiro() {
		return janeiro;
	}
	public void setJaneiro(Float janeiro) {
		this.janeiro = janeiro;
	}
	public Float getFereiro() {
		return fereiro;
	}
	public void setFereiro(Float fereiro) {
		this.fereiro = fereiro;
	}
	public Float getMarco() {
		return marco;
	}
	public void setMarco(Float marco) {
		this.marco = marco;
	}
	public Float getAbril() {
		return abril;
	}
	public void setAbril(Float abril) {
		this.abril = abril;
	}
	public Float getMaio() {
		return maio;
	}
	public void setMaio(Float maio) {
		this.maio = maio;
	}
	public Float getJunho() {
		return junho;
	}
	public void setJunho(Float junho) {
		this.junho = junho;
	}
	public Float getJulho() {
		return julho;
	}
	public void setJulho(Float julho) {
		this.julho = julho;
	}
	public Float getAgosto() {
		return agosto;
	}
	public void setAgosto(Float agosto) {
		this.agosto = agosto;
	}
	public Float getSetembro() {
		return setembro;
	}
	public void setSetembro(Float setembro) {
		this.setembro = setembro;
	}
	public Float getOutubro() {
		return outubro;
	}
	public void setOutubro(Float outubro) {
		this.outubro = outubro;
	}
	public Float getNovembro() {
		return novembro;
	}
	public void setNovembro(Float novembro) {
		this.novembro = novembro;
	}
	public Float getDezembro() {
		return dezembro;
	}
	public void setDezembro(Float dezembro) {
		this.dezembro = dezembro;
	}
	
	
}
