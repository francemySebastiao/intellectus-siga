package ao.co.intellectus.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.TipoProvaExtraOrdinaria;

public class AlunoAndAnoLectivoCliente {
    private Integer aluno;
    private Integer anoLectivo;
    @Enumerated(EnumType.STRING)
    private TipoProvaExtraOrdinaria tipoProva;
    
	public Integer getAluno() {
		return aluno;
	}
	public void setAluno(Integer aluno) {
		this.aluno = aluno;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public TipoProvaExtraOrdinaria getTipoProva() {
		return tipoProva;
	}
	public void setTipoProva(TipoProvaExtraOrdinaria tipoProva) {
		this.tipoProva = tipoProva;
	}
}
