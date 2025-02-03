package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_MAPA_LIMPO")
public class MapaFinanceiro {
	@Id
	private Integer matricula;
	private Integer lectivo;
	private String bolseiro;
	private Integer numero;
	private Integer desconto;      
	private String nome;
	private String curso;
	private String grau;
	private Integer inscricao;
	private Integer janeiro;
	private Integer fevereiro;
    private Integer marco;
    private Integer abril;
    private Integer maio;
    private Integer junho;
    private Integer julho;
    private Integer agosto;
    private Integer setembro;
    private Integer outubro;
    private Integer novembro;
    private Integer dezembro;
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public Integer getLectivo() {
		return lectivo;
	}
	public void setLectivo(Integer lectivo) {
		this.lectivo = lectivo;
	}
	public String getBolseiro() {
		return bolseiro;
	}
	public void setBolseiro(String bolseiro) {
		this.bolseiro = bolseiro;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Integer getDesconto() {
		return desconto;
	}
	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getGrau() {
		return grau;
	}
	public void setGrau(String grau) {
		this.grau = grau;
	}
	public Integer getInscricao() {
		return inscricao;
	}
	public void setInscricao(Integer inscricao) {
		this.inscricao = inscricao;
	}
	public Integer getJaneiro() {
		return janeiro;
	}
	public void setJaneiro(Integer janeiro) {
		this.janeiro = janeiro;
	}
	public Integer getFevereiro() {
		return fevereiro;
	}
	public void setFevereiro(Integer fevereiro) {
		this.fevereiro = fevereiro;
	}
	public Integer getMarco() {
		return marco;
	}
	public void setMarco(Integer marco) {
		this.marco = marco;
	}
	public Integer getAbril() {
		return abril;
	}
	public void setAbril(Integer abril) {
		this.abril = abril;
	}
	public Integer getMaio() {
		return maio;
	}
	public void setMaio(Integer maio) {
		this.maio = maio;
	}
	public Integer getJunho() {
		return junho;
	}
	public void setJunho(Integer junho) {
		this.junho = junho;
	}
	public Integer getJulho() {
		return julho;
	}
	public void setJulho(Integer julho) {
		this.julho = julho;
	}
	public Integer getAgosto() {
		return agosto;
	}
	public void setAgosto(Integer agosto) {
		this.agosto = agosto;
	}
	public Integer getSetembro() {
		return setembro;
	}
	public void setSetembro(Integer setembro) {
		this.setembro = setembro;
	}
	public Integer getOutubro() {
		return outubro;
	}
	public void setOutubro(Integer outubro) {
		this.outubro = outubro;
	}
	public Integer getNovembro() {
		return novembro;
	}
	public void setNovembro(Integer novembro) {
		this.novembro = novembro;
	}
	public Integer getDezembro() {
		return dezembro;
	}
	public void setDezembro(Integer dezembro) {
		this.dezembro = dezembro;
	}
}
