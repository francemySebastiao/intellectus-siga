package ao.co.intellectus.DTO;

public class EmolumentoCursoCliente {
	private Integer id;
	private Integer codigoMoeda;
	private String emolumento;
	private String curso;
	private String moeda;
	private float valor;
	private Integer anoLectivo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodigoMoeda() {
		return codigoMoeda;
	}
	public void setCodigoMoeda(Integer codigoMoeda) {
		this.codigoMoeda = codigoMoeda;
	}
	public String getEmolumento() {
		return emolumento;
	}
	public void setEmolumento(String emolumento) {
		this.emolumento = emolumento;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getMoeda() {
		return moeda;
	}
	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}
	
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
}
