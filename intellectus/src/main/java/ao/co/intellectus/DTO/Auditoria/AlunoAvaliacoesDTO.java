package ao.co.intellectus.DTO.Auditoria;

import java.util.Date;
import java.util.List;

public class AlunoAvaliacoesDTO {
	   private boolean contencioso;
	    private boolean fimCurso;
	    private Date dataFimCurso;
	    private String numeroAluno;
	    private String nomeAluno;
	    private String curso;
	    private boolean anulado;
	    private boolean bolseiro;
	    List<AvaliacoAuditoriaDto> avaliacoes;
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
		public String getNomeAluno() {
			return nomeAluno;
		}
		public void setNomeAluno(String nomeAluno) {
			this.nomeAluno = nomeAluno;
		}
		public String getCurso() {
			return curso;
		}
		public void setCurso(String curso) {
			this.curso = curso;
		}
		public boolean isAnulado() {
			return anulado;
		}
		public void setAnulado(boolean anulado) {
			this.anulado = anulado;
		}
		public boolean isBolseiro() {
			return bolseiro;
		}
		public void setBolseiro(boolean bolseiro) {
			this.bolseiro = bolseiro;
		}
		public List<AvaliacoAuditoriaDto> getAvaliacoes() {
			return avaliacoes;
		}
		public void setAvaliacoes(List<AvaliacoAuditoriaDto> avaliacoes) {
			this.avaliacoes = avaliacoes;
		}
}
