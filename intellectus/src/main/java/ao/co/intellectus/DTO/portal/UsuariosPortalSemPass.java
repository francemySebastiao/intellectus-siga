package ao.co.intellectus.DTO.portal;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.TipoAcesso;
import ao.co.intellectus.model.TipoUsuario;

public class UsuariosPortalSemPass {
	private Integer id;
	private String userName;
	private String name;
	private String email;
	private boolean active;
	@Enumerated(EnumType.STRING)
	private TipoUsuario userType;
	private String instCode;
	private boolean admin;
	@Enumerated(EnumType.STRING)
	private TipoAcesso tipoAcesso;
	@Temporal(TemporalType.DATE)
	private Date temporalIn;
	@Temporal(TemporalType.DATE)
	private Date temporalEnd;
	private String userCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public TipoUsuario getUserType() {
		return userType;
	}
	public void setUserType(TipoUsuario userType) {
		this.userType = userType;
	}
	public String getInstCode() {
		return instCode;
	}
	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public TipoAcesso getTipoAcesso() {
		return tipoAcesso;
	}
	public void setTipoAcesso(TipoAcesso tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}
	public Date getTemporalIn() {
		return temporalIn;
	}
	public void setTemporalIn(Date temporalIn) {
		this.temporalIn = temporalIn;
	}
	public Date getTemporalEnd() {
		return temporalEnd;
	}
	public void setTemporalEnd(Date temporalEnd) {
		this.temporalEnd = temporalEnd;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
