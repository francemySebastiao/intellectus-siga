package ao.co.intellectus.DTO.exame_electronico;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.enumeracao.TipoCandidatura;

public class CandidatoView {

	private Integer id;
	private Integer anoLectivo;
	@Enumerated(EnumType.STRING)
	private Grau grau;
	@Enumerated(EnumType.STRING)
	private TipoCandidatura tipoCandidatura;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Grau getGrau() {
		return grau;
	}

	public void setGrau(Grau grau) {
		this.grau = grau;
	}

	public TipoCandidatura getTipoCandidatura() {
		return tipoCandidatura;
	}

	public void setTipoCandidatura(TipoCandidatura tipoCandidatura) {
		this.tipoCandidatura = tipoCandidatura;
	}

}
