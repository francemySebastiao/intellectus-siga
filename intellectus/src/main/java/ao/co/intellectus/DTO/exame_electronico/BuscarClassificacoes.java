package ao.co.intellectus.DTO.exame_electronico;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class BuscarClassificacoes {
    private Integer curso;
    private Integer anoLectivo;
    @Temporal(TemporalType.DATE)
    private Date dataExame;
  
	public Date getDataExame() {
		return dataExame;
	}
	public void setDataExame(Date dataExame) {
		this.dataExame = dataExame;
	}
	public Integer getCurso() {
		return curso;
	}
	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

}
