package ao.co.intellectus.DTO.Auditoria.form;

import org.hibernate.validator.constraints.NotBlank;

public class TesorariaForm {
	
	@NotBlank private String  nuneroAluno;
	private Integer anolectivo;
	private boolean anulado;
	public String getNuneroAluno() {
		return nuneroAluno;
	}
	public void setNuneroAluno(String nuneroAluno) {
		this.nuneroAluno = nuneroAluno;
	}
	public Integer getAnolectivo() {
		return anolectivo;
	}
	public void setAnolectivo(Integer anolectivo) {
		this.anolectivo = anolectivo;
	}
	public boolean isAnulado() {
		return anulado;
	}
	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}
	
	

}
