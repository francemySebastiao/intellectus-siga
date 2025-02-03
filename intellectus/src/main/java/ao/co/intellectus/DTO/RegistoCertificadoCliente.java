package ao.co.intellectus.DTO;

public class RegistoCertificadoCliente {

	private String numerodoAluno;
	private String numeroDoCertificado;
	private Integer userCode;

	public String getNumerodoAluno() {
		return numerodoAluno;
	}

	public void setNumerodoAluno(String numerodoAluno) {
		this.numerodoAluno = numerodoAluno;
	}

	public String getNumeroDoCertificado() {
		return numeroDoCertificado;
	}

	public void setNumeroDoCertificado(String numeroDoCertificado) {
		this.numeroDoCertificado = numeroDoCertificado;
	}

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}

}
