package ao.co.intellectus.model.secretaria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_matricula_online")
public class MatriculaOnline {
	@Id
	@GeneratedValue
	private Integer id; 
	@Column(length=4000)  
	private String atestado;
	@Column(name="registo_criminal",length=4000)
	private String registoCriminal;
	@Column(length=4000)
	private String bi;
	@Column( length=100)
	private String numero;
	@Column( length=255)
	private String nome;
	@Column( length=255)
	private String curso;
	@Column( length=100)
	private String sexo;
	@Column( length=100)
	private String telefone;
	@Column( length=100)
	private String turno;
	//NOVOS CAMPOS PARA CONFIRMAÇÃO
	@Column( length=100)
	private String evento;
	@Column( length=100)
	private String estadoFinanceiro;
	@Column( length=100)
	private String tipoInscricao;
	@Column( length=100)
	private String divida;
	@Column(nullable=true)
	private boolean geradaReferencia;
	@Column(nullable=true)
	private boolean eventoExecutado;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAtestado() {
		return atestado;
	}
	public void setAtestado(String atestado) {
		this.atestado = atestado;
	}
	public String getRegistoCriminal() {
		return registoCriminal;
	}
	public void setRegistoCriminal(String registoCriminal) {
		this.registoCriminal = registoCriminal;
	}
	public String getBi() {
		return bi;
	}
	public void setBi(String bi) {
		this.bi = bi;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getEstadoFinanceiro() {
		return estadoFinanceiro;
	}
	public void setEstadoFinanceiro(String estadoFinanceiro) {
		this.estadoFinanceiro = estadoFinanceiro;
	}
	public String getTipoInscricao() {
		return tipoInscricao;
	}
	public void setTipoInscricao(String tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}
	public String getDivida() {
		return divida;
	}
	public void setDivida(String divida) {
		this.divida = divida;
	}
	public boolean isGeradaReferencia() {
		return geradaReferencia;
	}
	public void setGeradaReferencia(boolean geradaReferencia) {
		this.geradaReferencia = geradaReferencia;
	}
	public boolean isEventoExecutado() {
		return eventoExecutado;
	}
	public void setEventoExecutado(boolean eventoExecutado) {
		this.eventoExecutado = eventoExecutado;
	}
}