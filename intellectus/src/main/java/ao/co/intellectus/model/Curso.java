package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="t_curso")
@NamedQuery(name = Curso.QUERY_CONSULTA_ALL, query = "SELECT c.id,c.descricao FROM Curso c")
public class Curso {
	public static final String QUERY_CONSULTA_ALL = "Curso.ConsultaAll";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    private String descricao;
    private String sigla;
    private boolean status;
    private int duracao;
    @Column(name="quantidade_cadeiras")
    private int qtdCadeiras;
    private String codigo;
    private int inicio;
    private String plano;
    @ManyToOne
    @JoinColumn(name="codigo_faculdade")
    private Faculdade faculdade;
    @Enumerated(EnumType.STRING)
    private Grau grau;
    private String decreto;

	public Curso() { 
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public int getQtdCadeiras() {
		return qtdCadeiras;
	}

	public void setQtdCadeiras(int qtdCadeiras) {
		this.qtdCadeiras = qtdCadeiras;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}
	public Faculdade getFaculdade() {
		return faculdade;
	}

	public void setFaculdade(Faculdade faculdade) {
		this.faculdade = faculdade;
	}

	public Grau getGrau() {
		return grau;
	}

	public void setGrau(Grau grau) {
		this.grau = grau;
	}
	public String getDecreto() {
		return decreto;
	}
	public void setDecreto(String decreto) {
		this.decreto = decreto;
	}
	
	
}
