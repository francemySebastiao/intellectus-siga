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
@Table(name = "t_cartao_matriz_bloqueio")
public class CartaoMatrizBloqueio {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer vezesErros;
	private boolean bloqueiado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataBloqueio;
	@ManyToOne
	@JoinColumn(name = "codigo_docente")
	private Docente docente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVezesErros() {
		return vezesErros;
	}

	public void setVezesErros(Integer vezesErros) {
		this.vezesErros = vezesErros;
	}

	public boolean isBloqueiado() {
		return bloqueiado;
	}

	public void setBloqueiado(boolean bloqueiado) {
		this.bloqueiado = bloqueiado;
	}

	public Date getDataBloqueio() {
		return dataBloqueio;
	}

	public void setDataBloqueio(Date dataBloqueio) {
		this.dataBloqueio = dataBloqueio;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}
}
