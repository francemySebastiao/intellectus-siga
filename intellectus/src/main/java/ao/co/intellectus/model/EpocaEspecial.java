package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_epoca_especial")
public class EpocaEspecial {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer numeroAluno;
	private Integer totalCurso;
	private Integer totalFeitas;
	private Integer diferenca;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
	}
	public Integer getTotalCurso() {
		return totalCurso;
	}
	public void setTotalCurso(Integer totalCurso) {
		this.totalCurso = totalCurso;
	}
	public Integer getTotalFeitas() {
		return totalFeitas;
	}
	public void setTotalFeitas(Integer totalFeitas) {
		this.totalFeitas = totalFeitas;
	}
	public Integer getDiferenca() {
		return diferenca;
	}
	public void setDiferenca(Integer diferenca) {
		this.diferenca = diferenca;
	}	
}