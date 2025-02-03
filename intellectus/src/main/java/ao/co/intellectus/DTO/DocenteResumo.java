package ao.co.intellectus.DTO;

public class DocenteResumo {
    private Integer docenteId;
    private String nome;
    private String telefone;
    private String sexo;
    
    
    private Integer usuarioId;
 
    
	
	public Integer getDocenteId() {
		return docenteId;
	}
	public void setDocenteId(Integer docenteId) {
		this.docenteId = docenteId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
}
