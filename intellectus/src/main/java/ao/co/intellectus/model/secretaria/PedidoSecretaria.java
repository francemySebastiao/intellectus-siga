package ao.co.intellectus.model.secretaria;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.Guia;

@Entity
@Table(name = "tbl_pedidos")
public class PedidoSecretaria {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "tipo_solicitacao_pedido")
	private String tipoSolicitacaoPedido;
	@Column(name = "aluno_pedido")
	private String alunoPedido;
	@Column(name = "telefone_pedido")
	private String telefone;
	@Column(length = 4000, name = "bilhete_pedido")
	private String bilhete;
	@Temporal(TemporalType.DATE)
	private Date dataPedido;
	@Column(name = "guia_gerada")
	private Boolean guiaGerada;
	@ManyToOne
	@JoinColumn(name="codigo_guia")
	private Guia guia;
	@Column(name="declaracao_destino")
	private String declaracaoDestino;
	
	private double divida;
	 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoSolicitacaoPedido() {
		return tipoSolicitacaoPedido;
	}

	public void setTipoSolicitacaoPedido(String tipoSolicitacaoPedido) {
		this.tipoSolicitacaoPedido = tipoSolicitacaoPedido;
	}

	public String getAlunoPedido() {
		return alunoPedido;
	}

	public void setAlunoPedido(String alunoPedido) {
		this.alunoPedido = alunoPedido;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getBilhete() {
		return bilhete;
	}

	public void setBilhete(String bilhete) {
		this.bilhete = bilhete;
	}

	public Boolean getGuiaGerada() {
		return guiaGerada;
	}

	public void setGuiaGerada(Boolean guiaGerada) {
		this.guiaGerada = guiaGerada;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Guia getGuia() {
		return guia;
	}

	public void setGuia(Guia guia) {
		this.guia = guia;
	}

	public String getDeclaracaoDestino() {
		return declaracaoDestino;
	}

	public void setDeclaracaoDestino(String declaracaoDestino) {
		this.declaracaoDestino = declaracaoDestino;
	}

	public double getDivida() {
		return divida;
	}

	public void setDivida(double divida) {
		this.divida = divida;
	}
}
