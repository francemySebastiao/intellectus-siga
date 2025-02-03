package ao.co.intellectus.DTO.Auditoria.form;

import java.util.Date;

public class EquivalenciaForm {
	
	private Integer  NumeroAluno;
	private Date     Data;
	private Integer  anolectivo;
	
	public Integer getNumeroAluno() {
		return NumeroAluno;
	}
	public void setNumeroAluno(Integer numeroAluno) {
		NumeroAluno = numeroAluno;
	}
	public Date getData() {
		return Data;
	}
	public void setData(Date data) {
		Data = data;
	}
	public Integer getAnolectivo() {
		return anolectivo;
	}
	public void setAnolectivo(Integer anolectivo) {
		this.anolectivo = anolectivo;
	}

	
	
	
}
