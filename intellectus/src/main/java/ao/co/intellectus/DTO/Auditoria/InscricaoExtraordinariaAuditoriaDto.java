package ao.co.intellectus.DTO.Auditoria;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ao.co.intellectus.model.InscricaoExtraordinaria;
@JsonInclude(Include.NON_NULL)
public class InscricaoExtraordinariaAuditoriaDto {
	
	private String  turma;
	private String  userEmitiu;
    private String  descricao;
	private String  anoLectivoDescricao;
	private Date  dataPedido;
	private Date    dataRegistro;
	private String  tipoProva;
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public String getUserEmitiu() {
		return userEmitiu;
	}
	public void setUserEmitiu(String userEmitiu) {
		this.userEmitiu = userEmitiu;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getAnoLectivoDescricao() {
		return anoLectivoDescricao;
	}
	public void setAnoLectivoDescricao(String anoLectivoDescricao) {
		this.anoLectivoDescricao = anoLectivoDescricao;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public String getTipoProva() {
		return tipoProva;
	}
	public void setTipoProva(String tipoProva) {
		this.tipoProva = tipoProva;
	}
	

}
