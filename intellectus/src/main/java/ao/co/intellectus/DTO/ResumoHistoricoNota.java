package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ResumoHistoricoNota {
     private String disciplina;
     @Temporal(TemporalType.TIMESTAMP)
     private float nota;
     private Date dataAlteracao;
     private String usuario;
     private String accao;
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getAccao() {
		return accao;
	}
	public void setAccao(String accao) {
		this.accao = accao;
	}
}
