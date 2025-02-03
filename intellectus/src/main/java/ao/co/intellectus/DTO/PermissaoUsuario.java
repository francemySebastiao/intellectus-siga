package ao.co.intellectus.DTO;

import java.util.List;

public class PermissaoUsuario {
    private Integer codigo;
    private String nome;
    private List<CursoAndPermissao> cursoPermissao;
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<CursoAndPermissao> getCursoPermissao() {
		return cursoPermissao;
	}
	public void setCursoPermissao(List<CursoAndPermissao> cursoPermissao) {
		this.cursoPermissao = cursoPermissao;
	}
}
