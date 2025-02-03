package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_PROG_DECLARACOES_USUARIO_DEV")
public class DeclaracoesUsuario {
   
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	@Column(name="USUARIO")
	private Integer usuario;
    @Column(name="TOTAL")
    private Integer total;
    
    
	public Integer getUsuario() {
		return usuario;
	}
	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
}
