package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_modelo_avaliacao")
public class ModeloAvaliacao {
	@Id
	@GeneratedValue
	private Integer id;
	private float peso;
	private boolean obrigatoria;
	private float notaMinima;
	private float notaMinimaAprovacao;
	private float notaMaximaAprovacao;
	private Integer prioridade;
	private boolean perguntaNotaFinal;
	@ManyToOne
	@JoinColumn(name="codigo_prova")
	private Prova prova;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public boolean isObrigatoria() {
		return obrigatoria;
	}
	public void setObrigatoria(boolean obrigatoria) {
		this.obrigatoria = obrigatoria;
	}
	public float getNotaMinima() {
		return notaMinima;
	}
	public void setNotaMinima(float notaMinima) {
		this.notaMinima = notaMinima;
	}
	public float getNotaMinimaAprovacao() {
		return notaMinimaAprovacao;
	}
	public void setNotaMinimaAprovacao(float notaMinimaAprovacao) {
		this.notaMinimaAprovacao = notaMinimaAprovacao;
	}
	public float getNotaMaximaAprovacao() {
		return notaMaximaAprovacao;
	}
	public void setNotaMaximaAprovacao(float notaMaximaAprovacao) {
		this.notaMaximaAprovacao = notaMaximaAprovacao;
	}
	public Integer getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}
	public boolean isPerguntaNotaFinal() {
		return perguntaNotaFinal;
	}
	public void setPerguntaNotaFinal(boolean perguntaNotaFinal) {
		this.perguntaNotaFinal = perguntaNotaFinal;
	}
	public Prova getProva() {
		return prova;
	}
	public void setProva(Prova prova) {
		this.prova = prova;
	}	
}