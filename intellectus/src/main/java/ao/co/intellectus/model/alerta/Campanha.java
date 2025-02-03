package ao.co.intellectus.model.alerta;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_campanha")
public class Campanha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String desigancao;
	@Column(nullable = false)
	private String mensagem;
	@Column(name = "data_enviar")
	@Temporal(TemporalType.DATE)
	private Date dataEnviar;
	@Column(name = "data_registo")
	@Temporal(TemporalType.DATE)
	private Date dataRegisto;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesigancao() {
		return desigancao;
	}

	public void setDesigancao(String desigancao) {
		this.desigancao = desigancao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataEnviar() {
		return dataEnviar;
	}

	public void setDataEnviar(Date dataEnviar) {
		this.dataEnviar = dataEnviar;
	}

	public Date getDataRegisto() {
		return dataRegisto;
	}

	public void setDataRegisto(Date dataRegisto) {
		this.dataRegisto = dataRegisto;
	}

	

}
