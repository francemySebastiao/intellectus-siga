package ao.co.intellectus.DTO;

public class TransferenciaMatricula {
	private Integer numeroDeAluno;
	private Integer id;
	private Integer codigoAnoLectivo;
	private Integer codigoCursoDestino;
	private String userName;
	private Integer userCode;
	
	
	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCodigoAnoLectivo() {
		return codigoAnoLectivo;
	}
	public void setCodigoAnoLectivo(Integer codigoAnoLectivo) {
		this.codigoAnoLectivo = codigoAnoLectivo;
	}
	public Integer getCodigoCursoDestino() {
		return codigoCursoDestino;
	}
	public void setCodigoCursoDestino(Integer codigoCursoDestino) {
		this.codigoCursoDestino = codigoCursoDestino;
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
