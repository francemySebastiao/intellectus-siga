package ao.co.intellectus.DTO;

public class PassagemDeCredito {
	private Integer alunoOrigemId;
	private String alunoOrigemNome;
	private Integer alunoDestinoId;
	private String alunoDestinoNome;
	private double valorOrigem;
	private double valorAPassar;

	public Integer getAlunoOrigemId() {
		return alunoOrigemId;
	}

	public void setAlunoOrigemId(Integer alunoOrigemId) {
		this.alunoOrigemId = alunoOrigemId;
	}

	public String getAlunoOrigemNome() {
		return alunoOrigemNome;
	}

	public void setAlunoOrigemNome(String alunoOrigemNome) {
		this.alunoOrigemNome = alunoOrigemNome;
	}

	public Integer getAlunoDestinoId() {
		return alunoDestinoId;
	}

	public void setAlunoDestinoId(Integer alunoDestinoId) {
		this.alunoDestinoId = alunoDestinoId;
	}

	public String getAlunoDestinoNome() {
		return alunoDestinoNome;
	}

	public void setAlunoDestinoNome(String alunoDestinoNome) {
		this.alunoDestinoNome = alunoDestinoNome;
	}

	public double getValorOrigem() {
		return valorOrigem;
	}

	public void setValorOrigem(double valorOrigem) {
		this.valorOrigem = valorOrigem;
	}

	public double getValorAPassar() {
		return valorAPassar;
	}

	public void setValorAPassar(double valorAPassar) {
		this.valorAPassar = valorAPassar;
	}
}
