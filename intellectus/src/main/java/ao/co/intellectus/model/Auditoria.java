package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_auditoria")
public class Auditoria {
	@Id
	@GeneratedValue
	private Long id;
	private String referencia;
	private String tabela;
	private String nomeDoCampo;
	private Integer chavePrimaria;
	private String valorAntigo;
	private String ValorNovo;
    @ManyToOne
    @JoinColumn(name="codigo_usuario")
	private Usuario usuario;
	private Date datamovimento;
	
	public Auditoria() {
	
	}
	public Auditoria(String tabela, String nomeDoCampo, Integer chavePrimaria, String valorAntigo, String valorNovo,Usuario usuario, Date datamovimento) {
		this.tabela = tabela;
		this.nomeDoCampo = nomeDoCampo;
		this.chavePrimaria = chavePrimaria;
		this.valorAntigo = valorAntigo;
		this.ValorNovo = valorNovo;
		this.usuario = usuario;
		this.datamovimento = datamovimento;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getTabela() {
		return tabela;
	}
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}
	public String getNomeDoCampo() {
		return nomeDoCampo;
	}
	public void setNomeDoCampo(String nomeDoCampo) {
		this.nomeDoCampo = nomeDoCampo;
	}
	public Integer getChavePrimaria() {
		return chavePrimaria;
	}
	public void setChavePrimaria(Integer chavePrimaria) {
		this.chavePrimaria = chavePrimaria;
	}
	public String getValorAntigo() {
		return valorAntigo;
	}
	public void setValorAntigo(String valorAntigo) {
		this.valorAntigo = valorAntigo;
	}
	public String getValorNovo() {
		return ValorNovo;
	}
	public void setValorNovo(String valorNovo) {
		ValorNovo = valorNovo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getDatamovimento() {
		return datamovimento;
	}
	public void setDatamovimento(Date datamovimento) {
		this.datamovimento = datamovimento;
	}
}
