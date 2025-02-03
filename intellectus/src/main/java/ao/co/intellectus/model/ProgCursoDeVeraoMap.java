package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="V_PROG_CURSO_DE_VERAO_CURSO_MAP")
public class ProgCursoDeVeraoMap {
	@Id
	@GeneratedValue
	private Integer id;
	private String disciplina;
	@Column(name="CODIGO_CURSO")
	private Integer codigoCurso;
	@Column(name="CURSO")
	private String curso;
	private Integer codigoDisciplina;
    @Enumerated(EnumType.STRING)
    private TipoProvaExtraOrdinaria prova;
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INSCRICAO")
	private Date dataInscricao;
	private String pagamento;
	private Integer inscritos;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public Date getDataInscricao() {
		return dataInscricao;
	}
	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}
	public String getPagamento() {
		return pagamento;
	}
	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	public Integer getInscritos() {
		return inscritos;
	}
	public void setInscritos(Integer inscritos) {
		this.inscritos = inscritos;
	}
	public Integer getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(Integer codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Integer getCodigoDisciplina() {
		return codigoDisciplina;
	}
	public void setCodigoDisciplina(Integer codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	public TipoProvaExtraOrdinaria getProva() {
		return prova;
	}
	public void setProva(TipoProvaExtraOrdinaria prova) {
		this.prova = prova;
	}
}
