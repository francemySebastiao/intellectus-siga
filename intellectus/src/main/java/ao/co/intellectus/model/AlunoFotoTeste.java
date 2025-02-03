package ao.co.intellectus.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_aluno_foto_teste")
public class AlunoFotoTeste {
	@Id
	@GeneratedValue
	private Integer id;
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] foto;
	@ManyToOne
	@JoinColumn(name="codigo_aluno")
	private Aluno aluno;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
}
