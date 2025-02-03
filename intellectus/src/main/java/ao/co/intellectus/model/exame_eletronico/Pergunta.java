package ao.co.intellectus.model.exame_eletronico;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Curso;

@Entity
@Table(name = "t_Pergunta")
public class Pergunta {
	@Id
	@GeneratedValue
	private Integer id;
	private String descricao;
	private Integer classificacao;
	@Enumerated(EnumType.STRING)
	private TipoExame tipoExame;
	@ManyToOne
	@JoinColumn(name = "codigo_curso")
	private Curso curso;
	@ManyToOne
	@JoinColumn(name = "codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<Resposta> respota;
	private Boolean estado;

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Integer classificacao) {
		this.classificacao = classificacao;
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

	public List<Resposta> getRespota() {
		return respota;
	}

	public void setRespota(List<Resposta> respota) {
		this.respota = respota;
	}
	
}