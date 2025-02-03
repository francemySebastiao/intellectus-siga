package ao.co.intellectus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_faculdade")
public class Faculdade implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	private String faculdade;
	private String cargo;
	private String responsavel;
	@ManyToOne
	@JoinColumn(name = "codigo_instituicao")
	private Instituicao instituicao;
	@Column(length = 100)
	private String responsavel2;
	@Column(length = 50)
	private String cargo2;
	@Column(length = 100)
	private String coordenadorMestrado;
	@Column(length = 50)
	private String cargoMestrado;
	@Column(length = 50)
	private String titulo;
	private String email;
	private String emailDecano;

	public Faculdade() {
	}

	public Faculdade(Integer id, String faculdade, String cargo, String responsavel) {
		this.id = id;
		this.faculdade = faculdade;
		this.cargo = cargo;
		this.responsavel = responsavel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFaculdade() {
		return faculdade;
	}

	public void setFaculdade(String faculdade) {
		this.faculdade = faculdade;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public String getResponsavel2() {
		return responsavel2;
	}

	public void setResponsavel2(String responsavel2) {
		this.responsavel2 = responsavel2;
	}

	public String getCargo2() {
		return cargo2;
	}

	public void setCargo2(String cargo2) {
		this.cargo2 = cargo2;
	}

	public String getCoordenadorMestrado() {
		return coordenadorMestrado;
	}

	public void setCoordenadorMestrado(String coordenadorMestrado) {
		this.coordenadorMestrado = coordenadorMestrado;
	}

	public String getCargoMestrado() {
		return cargoMestrado;
	}

	public void setCargoMestrado(String cargoMestrado) {
		this.cargoMestrado = cargoMestrado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailDecano() {
		return emailDecano;
	}

	public void setEmailDecano(String emailDecano) {
		this.emailDecano = emailDecano;
	}
}
