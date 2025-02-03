package ao.co.intellectus.model.portal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.TipoAcesso;
import ao.co.intellectus.model.TipoUsuario;

@Entity
@Table(name = "t_portal_usuario")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "user_name", length = 110, unique = true, nullable = false)
	private String userName;
	@Column(nullable = false)
	private String password;
	private String name;
	@Column(length = 150)
	private String email;
	private boolean active;
	@Enumerated(EnumType.STRING)
	private TipoUsuario userType;
	@Column(length = 10, name = "inst_code")
	private String instCode;
	private boolean admin;
	@Enumerated(EnumType.STRING)
	private TipoAcesso tipoAcesso;
	@Temporal(TemporalType.DATE)
	@Column(name = "temporal_in")
	private Date temporalIn;
	@Temporal(TemporalType.DATE)
	@Column(name = "temporal_end")
	private Date temporalEnd;
	@Column(name = "user_code")
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.userCode))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	
	
}
