package ao.co.intellectus.DTO.Auditoria;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ao.co.intellectus.DTO.AnosInscricoes;
@JsonInclude(Include.NON_NULL)
public class FichaAcademicaAlunoAuditoriaDto {
	
 
    private boolean contencioso;
    private boolean fimCurso;
    private Date dataFimCurso;
    private String numeroAluno;
    private String nomeAluno;
    private String curso;
    private boolean anulado;
    private boolean bolseiro;
	private List<AlunoAuditDTO> alunosAuditDTO;
	private List<AnosInscricoes> anosInscricoes;
	

   



	public boolean isAnulado() {
		return anulado;
	}



	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}



	public String getNomeAluno() {
		return nomeAluno;
	}



	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}



	public List<AlunoAuditDTO> getAlunosAuditDTO() {
		return alunosAuditDTO;
	}



	public void setAlunosAuditDTO(List<AlunoAuditDTO> alunosAuditDTO) {
		this.alunosAuditDTO = alunosAuditDTO;
	}



	public boolean isContencioso() {
		return contencioso;
	}



	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}



	public boolean isFimCurso() {
		return fimCurso;
	}



	public void setFimCurso(boolean fimCurso) {
		this.fimCurso = fimCurso;
	}



	public Date getDataFimCurso() {
		return dataFimCurso;
	}



	public void setDataFimCurso(Date dataFimCurso) {
		this.dataFimCurso = dataFimCurso;
	}



	public String getNumeroAluno() {
		return numeroAluno;
	}



	public void setNumeroAluno(String numeroAluno) {
		this.numeroAluno = numeroAluno;
	}



	public String getCurso() {
		return curso;
	}



	public void setCurso(String curso) {
		this.curso = curso;
	}



	public boolean isBolseiro() {
		return bolseiro;
	}



	public void setBolseiro(boolean bolseiro) {
		this.bolseiro = bolseiro;
	}


	public List<AnosInscricoes> getAnosInscricoes() {
		return anosInscricoes;
	}

	public void setAnosInscricoes(List<AnosInscricoes> anosInscricoes) {
		this.anosInscricoes = anosInscricoes;
	}

	

}
