package ao.co.intellectus.DTO;

public class TurmaDisciplinaAlterar {
	private Integer idHistorico;
	private Integer idTurma;
	
	private Integer userCode;
	private String userName;

	public Integer getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(Integer idHistorico) {
		this.idHistorico = idHistorico;
	}

	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
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