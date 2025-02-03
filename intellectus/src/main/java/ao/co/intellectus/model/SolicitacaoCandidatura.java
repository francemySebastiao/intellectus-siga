package ao.co.intellectus.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_solicitacao_candidatura")
public class SolicitacaoCandidatura {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(length=100)
	private String documento;
	private LocalDate dataRegisto;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public LocalDate getDataRegisto() {
		return dataRegisto;
	}
	public void setDataRegisto(LocalDate dataRegisto) {
		this.dataRegisto = dataRegisto;
	}
}
