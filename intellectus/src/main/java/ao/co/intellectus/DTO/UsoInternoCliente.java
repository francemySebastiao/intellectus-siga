package ao.co.intellectus.DTO;

import java.util.Date;
import java.util.List;

public class UsoInternoCliente {
	private String nome;
    private String numeroDeAluno;
    private String nomeDoPai;
    private String nomeDaMae;
    private String numeroDocumento;
    private Date dataDeEmissao;
    private String arquivoDeIdentificacao;
    private Date dataNascimento;
    private String cidadeNascimento;
    private String provincia;
    private String curso;
	private List<AcademicoCliente> academico;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(String numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public String getNomeDoPai() {
		return nomeDoPai;
	}
	public void setNomeDoPai(String nomeDoPai) {
		this.nomeDoPai = nomeDoPai;
	}
	public String getNomeDaMae() {
		return nomeDaMae;
	}
	public void setNomeDaMae(String nomeDaMae) {
		this.nomeDaMae = nomeDaMae;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public Date getDataDeEmissao() {
		return dataDeEmissao;
	}
	public void setDataDeEmissao(Date dataDeEmissao) {
		this.dataDeEmissao = dataDeEmissao;
	}
	public String getArquivoDeIdentificacao() {
		return arquivoDeIdentificacao;
	}
	public void setArquivoDeIdentificacao(String arquivoDeIdentificacao) {
		this.arquivoDeIdentificacao = arquivoDeIdentificacao;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCidadeNascimento() {
		return cidadeNascimento;
	}
	public void setCidadeNascimento(String cidadeNascimento) {
		this.cidadeNascimento = cidadeNascimento;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public List<AcademicoCliente> getAcademico() {
		return academico;
	}
	public void setAcademico(List<AcademicoCliente> academico) {
		this.academico = academico;
	}
}
