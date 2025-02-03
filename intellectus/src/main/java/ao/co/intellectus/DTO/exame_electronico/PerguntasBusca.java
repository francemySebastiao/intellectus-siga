package ao.co.intellectus.DTO.exame_electronico;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.exame_eletronico.TipoExame;

public class PerguntasBusca {
    private Integer codigoCurso;
    private Integer anoLectivo;
    @Enumerated(EnumType.STRING)
	private TipoExame tipoExame;
    private Boolean estado;
    
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Integer getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(Integer codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public TipoExame getTipoExame() {
		return tipoExame;
	}
	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}
}
