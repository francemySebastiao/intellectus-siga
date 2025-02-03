package ao.co.intellectus.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PAGAMENTO_REMOTO")
public class PagamentosRemoto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numeroGuia;
	private String numeroAluno;
	private String telefone;
	private String bordereux;
	private String referencia;
	private BigDecimal valor;
	private String dataPagmento;
	private String dataExportado;
	private boolean compensado;
	private String dataCompensado;
	private String unidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroGuia() {
		return numeroGuia;
	}

	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}

	public String getBordereux() {
		return bordereux;
	}

	public void setBordereux(String bordereux) {
		this.bordereux = bordereux;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDataPagmento() {
		return dataPagmento;
	}

	public void setDataPagmento(String dataPagmento) {
		this.dataPagmento = dataPagmento;
	}

	public String getDataExportado() {
		return dataExportado;
	}

	public void setDataExportado(String dataExportado) {
		this.dataExportado = dataExportado;
	}

	public boolean isCompensado() {
		return compensado;
	}

	public void setCompensado(boolean compensado) {
		this.compensado = compensado;
	}

	public String getDataCompensado() {
		return dataCompensado;
	}

	public void setDataCompensado(String dataCompensado) {
		this.dataCompensado = dataCompensado;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getNumeroAluno() {
		return numeroAluno;
	}

	public void setNumeroAluno(String numeroAluno) {
		this.numeroAluno = numeroAluno;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
