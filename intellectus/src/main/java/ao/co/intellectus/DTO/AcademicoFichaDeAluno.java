package ao.co.intellectus.DTO;

import java.util.Date;
import java.util.List;

public class AcademicoFichaDeAluno {
	private Integer anoLectivo;
	private String universidade;
	private String nome;
    private String numeroAluno;
    private String numeroDocumento;
    private Date dataDeEmissao;
    private String arquivoDeIdentificacao;
    private Date dataInscricao;
    private String endereco;
    private String telefone;
    private String email;
    private String sexo;
    private String faculdade;
    private String curso;
    private List<AcademicoFicha> academico;
	
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public String getUniversidade() {
		return universidade;
	}
	public void setUniversidade(String universidade) {
		this.universidade = universidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(String numeroAluno) {
		this.numeroAluno = numeroAluno;
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
	public Date getDataInscricao() {
		return dataInscricao;
	}
	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFaculdade() {
		return faculdade;
	}
	public void setFaculdade(String faculdade) {
		this.faculdade = faculdade;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public List<AcademicoFicha> getAcademico() {
		return academico;
	}
	public void setAcademico(List<AcademicoFicha> academico) {
		this.academico = academico;
	}
}
