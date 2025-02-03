package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_lancamento_faculdade")
public class LancamentoFaculdade {
	
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name="codigo_prova")
	private Prova prova;
	@ManyToOne
	@JoinColumn(name="codigo_faculdade")
	private Faculdade faculdade;
	@Enumerated(EnumType.STRING)
	private Permissao permissao;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Prova getProva() {
		return prova;
	}
	public void setProva(Prova prova) {
		this.prova = prova;
	}
	public Faculdade getFaculdade() {
		return faculdade;
	}
	public void setFaculdade(Faculdade faculdade) {
		this.faculdade = faculdade;
	}
	public Permissao getPermissao() {
		return permissao;
	}
	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
}
