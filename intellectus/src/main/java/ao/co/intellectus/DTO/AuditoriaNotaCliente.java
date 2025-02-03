package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class AuditoriaNotaCliente {

	private String usuarioPrimeiraAvaliacao;
	@Temporal(TemporalType.DATE)
	private Date dataPrimeiraAvaliacao;
	private String usuarioSegundaAvaliacao;
	@Temporal(TemporalType.DATE)
	private Date dataSegundaAvaliacao;
	private String usuarioTerceiraAvaliacao;
	@Temporal(TemporalType.DATE)
	private Date dataTerceiraAvaliacao;
	private String usuarioQuartaAvaliacao;
	@Temporal(TemporalType.DATE)
	private Date dataquartaAvaliacao;
	private String usuarioExameAvaliacao;
	@Temporal(TemporalType.DATE)
	private Date dataExameAvaliacao;
	private String usuarioExameOralAvaliacao;
	@Temporal(TemporalType.DATE)
	private Date dataExameOralAvaliacao;
	private String usuarioRecursoAvaliacao;
	@Temporal(TemporalType.DATE)
	private Date dataRecursoAvaliacao;
	private String usuarioEpocaEspcialAvaliacao;
	@Temporal(TemporalType.DATE)
	private Date dataEpocaEspcialAvaliacao;
	private String usuarioVeraoAvaliacao;
	@Temporal(TemporalType.DATE)
	private Date dataVeraoAvaliacao;
	private String usuarioMelhoriaAvaliacao;
	@Temporal(TemporalType.DATE)
	private Date dataMelhoriaAvaliacao;

	public String getUsuarioPrimeiraAvaliacao() {
		return usuarioPrimeiraAvaliacao;
	}

	public void setUsuarioPrimeiraAvaliacao(String usuarioPrimeiraAvaliacao) {
		this.usuarioPrimeiraAvaliacao = usuarioPrimeiraAvaliacao;
	}

	public Date getDataPrimeiraAvaliacao() {
		return dataPrimeiraAvaliacao;
	}

	public void setDataPrimeiraAvaliacao(Date dataPrimeiraAvaliacao) {
		this.dataPrimeiraAvaliacao = dataPrimeiraAvaliacao;
	}

	public String getUsuarioSegundaAvaliacao() {
		return usuarioSegundaAvaliacao;
	}

	public void setUsuarioSegundaAvaliacao(String usuarioSegundaAvaliacao) {
		this.usuarioSegundaAvaliacao = usuarioSegundaAvaliacao;
	}

	public Date getDataSegundaAvaliacao() {
		return dataSegundaAvaliacao;
	}

	public void setDataSegundaAvaliacao(Date dataSegundaAvaliacao) {
		this.dataSegundaAvaliacao = dataSegundaAvaliacao;
	}

	public String getUsuarioTerceiraAvaliacao() {
		return usuarioTerceiraAvaliacao;
	}

	public void setUsuarioTerceiraAvaliacao(String usuarioTerceiraAvaliacao) {
		this.usuarioTerceiraAvaliacao = usuarioTerceiraAvaliacao;
	}

	public Date getDataTerceiraAvaliacao() {
		return dataTerceiraAvaliacao;
	}

	public void setDataTerceiraAvaliacao(Date dataTerceiraAvaliacao) {
		this.dataTerceiraAvaliacao = dataTerceiraAvaliacao;
	}

	public String getUsuarioQuartaAvaliacao() {
		return usuarioQuartaAvaliacao;
	}

	public void setUsuarioQuartaAvaliacao(String usuarioQuartaAvaliacao) {
		this.usuarioQuartaAvaliacao = usuarioQuartaAvaliacao;
	}

	public Date getDataquartaAvaliacao() {
		return dataquartaAvaliacao;
	}

	public void setDataquartaAvaliacao(Date dataquartaAvaliacao) {
		this.dataquartaAvaliacao = dataquartaAvaliacao;
	}

	public String getUsuarioExameAvaliacao() {
		return usuarioExameAvaliacao;
	}

	public void setUsuarioExameAvaliacao(String usuarioExameAvaliacao) {
		this.usuarioExameAvaliacao = usuarioExameAvaliacao;
	}

	public Date getDataExameAvaliacao() {
		return dataExameAvaliacao;
	}

	public void setDataExameAvaliacao(Date dataExameAvaliacao) {
		this.dataExameAvaliacao = dataExameAvaliacao;
	}

	public String getUsuarioExameOralAvaliacao() {
		return usuarioExameOralAvaliacao;
	}

	public void setUsuarioExameOralAvaliacao(String usuarioExameOralAvaliacao) {
		this.usuarioExameOralAvaliacao = usuarioExameOralAvaliacao;
	}

	public Date getDataExameOralAvaliacao() {
		return dataExameOralAvaliacao;
	}

	public void setDataExameOralAvaliacao(Date dataExameOralAvaliacao) {
		this.dataExameOralAvaliacao = dataExameOralAvaliacao;
	}

	public String getUsuarioRecursoAvaliacao() {
		return usuarioRecursoAvaliacao;
	}

	public void setUsuarioRecursoAvaliacao(String usuarioRecursoAvaliacao) {
		this.usuarioRecursoAvaliacao = usuarioRecursoAvaliacao;
	}

	public Date getDataRecursoAvaliacao() {
		return dataRecursoAvaliacao;
	}

	public void setDataRecursoAvaliacao(Date dataRecursoAvaliacao) {
		this.dataRecursoAvaliacao = dataRecursoAvaliacao;
	}

	public String getUsuarioEpocaEspcialAvaliacao() {
		return usuarioEpocaEspcialAvaliacao;
	}

	public void setUsuarioEpocaEspcialAvaliacao(String usuarioEpocaEspcialAvaliacao) {
		this.usuarioEpocaEspcialAvaliacao = usuarioEpocaEspcialAvaliacao;
	}

	public Date getDataEpocaEspcialAvaliacao() {
		return dataEpocaEspcialAvaliacao;
	}

	public void setDataEpocaEspcialAvaliacao(Date dataEpocaEspcialAvaliacao) {
		this.dataEpocaEspcialAvaliacao = dataEpocaEspcialAvaliacao;
	}

	public String getUsuarioVeraoAvaliacao() {
		return usuarioVeraoAvaliacao;
	}

	public void setUsuarioVeraoAvaliacao(String usuarioVeraoAvaliacao) {
		this.usuarioVeraoAvaliacao = usuarioVeraoAvaliacao;
	}

	public Date getDataVeraoAvaliacao() {
		return dataVeraoAvaliacao;
	}

	public void setDataVeraoAvaliacao(Date dataVeraoAvaliacao) {
		this.dataVeraoAvaliacao = dataVeraoAvaliacao;
	}

	public String getUsuarioMelhoriaAvaliacao() {
		return usuarioMelhoriaAvaliacao;
	}

	public void setUsuarioMelhoriaAvaliacao(String usuarioMelhoriaAvaliacao) {
		this.usuarioMelhoriaAvaliacao = usuarioMelhoriaAvaliacao;
	}

	public Date getDataMelhoriaAvaliacao() {
		return dataMelhoriaAvaliacao;
	}

	public void setDataMelhoriaAvaliacao(Date dataMelhoriaAvaliacao) {
		this.dataMelhoriaAvaliacao = dataMelhoriaAvaliacao;
	}

}
