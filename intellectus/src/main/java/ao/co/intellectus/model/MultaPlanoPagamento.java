package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class MultaPlanoPagamento {
	
	@Id
	@GeneratedValue
	private Integer id;
	private Date dataInsercao;
	private boolean processado;
	private double valor;
	@OneToOne
	@JoinColumn(name="numero_da_guia")
	private Guia guia;
	@ManyToOne
	@JoinColumn(name="numero_de_aluno")
	private Aluno aluno;
	@ManyToOne
	@JoinColumn(name="codigo_ano_lectivo")
	private AnoLectivo anoLectivo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInsercao() {
		return dataInsercao;
	}

	public void setDataInsercao(Date dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	public boolean isProcessado() {
		return processado;
	}

	public void setProcessado(boolean processado) {
		this.processado = processado;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Guia getGuia() {
		return guia;
	}

	public void setGuia(Guia guia) {
		this.guia = guia;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
}
