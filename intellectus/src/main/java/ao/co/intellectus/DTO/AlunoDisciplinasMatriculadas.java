package ao.co.intellectus.DTO;

import java.util.List;

public class AlunoDisciplinasMatriculadas {
    private Integer aluno;
    private String nome;
    private String curso;
    private Integer anoLectivo;
    private boolean fimCurso;
    private Integer anoCorricular;
    private boolean contencioso;
    private String grau;
    private List<InscricaoPorAno> inscricaoPorAno;
    private List<PedidoDocumentoCliente> pedidos;
	private List<DisciplinasInscritasCleinte> disciplinasInscritas;
	public Integer getAluno() {
		return aluno;
	}
	public void setAluno(Integer aluno) {
		this.aluno = aluno;
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
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public boolean isFimCurso() {
		return fimCurso;
	}
	public void setFimCurso(boolean fimCurso) {
		this.fimCurso = fimCurso;
	}
	public Integer getAnoCorricular() {
		return anoCorricular;
	}
	public void setAnoCorricular(Integer anoCorricular) {
		this.anoCorricular = anoCorricular;
	}
	public List<InscricaoPorAno> getInscricaoPorAno() {
		return inscricaoPorAno;
	}
	public void setInscricaoPorAno(List<InscricaoPorAno> inscricaoPorAno) {
		this.inscricaoPorAno = inscricaoPorAno;
	}
	public List<DisciplinasInscritasCleinte> getDisciplinasInscritas() {
		return disciplinasInscritas;
	}
	public void setDisciplinasInscritas(List<DisciplinasInscritasCleinte> disciplinasInscritas) {
		this.disciplinasInscritas = disciplinasInscritas;
	}
	public List<PedidoDocumentoCliente> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<PedidoDocumentoCliente> pedidos) {
		this.pedidos = pedidos;
	}
	public boolean isContencioso() {
		return contencioso;
	}
	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}
	public String getGrau() {
		return grau;
	}
	public void setGrau(String grau) {
		this.grau = grau;
	}
}