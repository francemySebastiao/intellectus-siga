package ao.co.intellectus.DTO;

public class AnexoCliente {

	private String titulo;
	private byte[] binario;
	private String extensao;
	
	public AnexoCliente() {
		
	}
	public AnexoCliente(String titulo, byte[] binario, String extensao) {
		this.titulo = titulo;
		this.binario = binario;
		this.extensao = extensao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public byte[] getBinario() {
		return binario;
	}

	public void setBinario(byte[] binario) {
		this.binario = binario;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

}
