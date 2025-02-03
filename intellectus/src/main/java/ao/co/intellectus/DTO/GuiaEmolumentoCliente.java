package ao.co.intellectus.DTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class GuiaEmolumentoCliente {
	private Integer aluno;
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	private boolean guiaAcordo;
	private boolean guiaParaAcordoPagamento;
	private String userName;
	private Integer userCode;
	private Integer anoInscricao;
	private List<EmolumentoDescCliente> emolumento;

	public Integer getAluno() {
		return aluno;
	}

	public void setAluno(Integer aluno) {
		this.aluno = aluno;
	}

	public List<EmolumentoDescCliente> getEmolumento() {
		return emolumento;
	}

	public void setEmolumento(List<EmolumentoDescCliente> emolumento) {
		this.emolumento = emolumento;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public boolean isGuiaAcordo() {
		return guiaAcordo;
	}

	public void setGuiaAcordo(boolean guiaAcordo) {
		this.guiaAcordo = guiaAcordo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}

	public Integer getAnoInscricao() {
		return anoInscricao;
	}

	public void setAnoInscricao(Integer anoInscricao) {
		this.anoInscricao = anoInscricao;
	}

	public boolean isGuiaParaAcordoPagamento() {
		return guiaParaAcordoPagamento;
	}

	public void setGuiaParaAcordoPagamento(boolean guiaParaAcordo) {
		this.guiaParaAcordoPagamento = guiaParaAcordo;
	}
	
}
