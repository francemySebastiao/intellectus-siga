package ao.co.intellectus.model.exame_eletronico;

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

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.Curso;

@Entity
@Table(name = "t_exame_eletronico")
public class ExameEletronico {
	@Id
	@GeneratedValue
	private Integer id;

	@Enumerated(EnumType.STRING)
	private TipoExame tipoExame;

	@ManyToOne
	@JoinColumn(name = "codigo_curso")
	private Curso curso;

	@ManyToOne
	@JoinColumn(name = "codigo_ano_lectivo")
	private AnoLectivo anoLectivo;

	@ManyToOne
	@JoinColumn(name = "codigo_candidato")
	private Candidato candidato;

	@ManyToOne
	@JoinColumn(name = "codigo_pergunta")
	private Pergunta pergunta;

	@ManyToOne
	@JoinColumn(name = "codigo_resposta")
	private Resposta resposta;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicioProva;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFimProva;

	private Boolean tempoExpirado;
	private Boolean certa;
	private Integer classificacao;
	@Column(name="rpt_sig")
	private boolean rptSig;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoExame getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public Date getDataInicioProva() {
		return dataInicioProva;
	}

	public void setDataInicioProva(Date dataInicioProva) {
		this.dataInicioProva = dataInicioProva;
	}

	public Date getDataFimProva() {
		return dataFimProva;
	}

	public void setDataFimProva(Date dataFimProva) {
		this.dataFimProva = dataFimProva;
	}

	public Boolean getTempoExpirado() {
		return tempoExpirado;
	}

	public void setTempoExpirado(Boolean tempoExpirado) {
		this.tempoExpirado = tempoExpirado;
	}

	public Boolean getCerta() {
		return certa;
	}

	public void setCerta(Boolean certa) {
		this.certa = certa;
	}

	public Integer getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Integer classificacao) {
		this.classificacao = classificacao;
	}

	public boolean isRptSig() {
		return rptSig;
	}

	public void setRptSig(boolean rptSig) {
		this.rptSig = rptSig;
	}
}
