package ao.co.intellectus.DTO;

public class TipoInscricaoMatricula {
	private Integer idMatricula;
	private Integer idTipoInscricao;
	private Integer userCode;
	private String userName;
	
	public Integer getIdMatricula() {
		return idMatricula;
	}
	public void setIdMatricula(Integer idMatricula) {
		this.idMatricula = idMatricula;
	}
	public Integer getIdTipoInscricao() {
		return idTipoInscricao;
	}
	public void setIdTipoInscricao(Integer idTipoInscricao) {
		this.idTipoInscricao = idTipoInscricao;
	}
	public Integer getUserCode() {
		return userCode;
	}
	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
