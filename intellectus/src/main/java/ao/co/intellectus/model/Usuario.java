package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_usuario")
public class Usuario {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(length=100)
	private String name;
	@Column(length=50)
	private String userName;
	@Column(length=100)
	private String email;
	private Integer userCode;
    private Integer instCode;
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;
    @Column(nullable=true)
    private boolean finaceiro;
    private String senha;
  
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getUserCode() {
		return userCode;
	}
	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}
	public Integer getInstCode() {
		return instCode;
	}
	public void setInstCode(Integer instCode) {
		this.instCode = instCode;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public boolean isFinaceiro() {
		return finaceiro;
	}
	public void setFinaceiro(boolean finaceiro) {
		this.finaceiro = finaceiro;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	public Usuario() {
	}
	
	public Usuario(Integer id) {
	
		this.id = id;
	}
	
	
}