package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_conta_candidato")
public class ContaCorrenteCandidato {
	@Id
	@GeneratedValue
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date dataMovimento;
	@OneToOne
	@JoinColumn(name = "codigo_candidato")
	private Candidato candidato;
	private String numeroDeCandidato;
	@ManyToOne
	@JoinColumn(name = "codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	private Double valor;
	private Double valorAnterior;
	@ManyToOne
	@JoinColumn(name = "codigo_instituicao")
	private Instituicao instituicao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public String getNumeroDeCandidato() {
		return numeroDeCandidato;
	}

	public void setNumeroDeCandidato(String numeroDeCandidato) {
		this.numeroDeCandidato = numeroDeCandidato;
	}

	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(Double valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
}
