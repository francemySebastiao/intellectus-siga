package ao.co.intellectus.DTO;

import java.util.List;

public class FichaAcademicaRetorno {
	private Integer numeroAluno;
	private String nome;
	private String curso;
	private String nomeDoPai;
	private String nomeDaMae;
	private String naturalidade;
	private String provinciaNascimento;
	private Integer diaNascimento;
	private String mesNascimento;
	private Integer anoNascimento;
	private String tipoDocumento;
	private String numeroDocumento;
	private String nacionalidade;
	
	private List<FichaAcademicaCliente> historicoAluno;
	
	public Integer getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
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
	public List<FichaAcademicaCliente> getHistoricoAluno() {
		return historicoAluno;
	}
	public void setHistoricoAluno(List<FichaAcademicaCliente> historicoAluno) {
		this.historicoAluno = historicoAluno;
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
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	public String getProvinciaNascimento() {
		return provinciaNascimento;
	}
	public void setProvinciaNascimento(String provinciaNascimento) {
		this.provinciaNascimento = provinciaNascimento;
	}
	
	public Integer getDiaNascimento() {
		return diaNascimento;
	}
	public String getMesNascimento() {
		return mesNascimento;
	}
	public void setMesNascimento(String mesNascimento) {
		this.mesNascimento = mesNascimento;
	}
	
	public Integer getAnoNascimento() {
		return anoNascimento;
	}
	public void setAnoNascimento(Integer anoNascimento) {
		this.anoNascimento = anoNascimento;
	}
	public void setDiaNascimento(Integer diaNascimento) {
		this.diaNascimento = diaNascimento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
}
