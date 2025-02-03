package ao.co.intellectus.DTO;

public class ModeloAlteracaoNota {
	private Integer id;
	private Integer prova;
	private Float nota;
	private String userName;
	private Integer userCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProva() {
		return prova;
	}

	public void setProva(Integer prova) {
		this.prova = prova;
	}

	public Float getNota() {
		return nota;
	}

	public void setNota(Float nota) {
		this.nota = nota;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}

}
