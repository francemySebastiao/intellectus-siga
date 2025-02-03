package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class HistoricoCreditoCliente {
	   private Integer id; 
		@Column(length=50,unique=true)
		private String borderoInterno;
		@Column(length=50,unique=true)
		private String borderoExterno;
		private float valorDeposito;	
		@Temporal(TemporalType.TIMESTAMP)
		private Date dataRegisto;
		@Temporal(TemporalType.DATE)
		private Date dataDepositoExterno;
		private Integer aluno;
		private Integer numeroDeAluno;
		private Integer instituicao;
		private Integer banco;
		private Integer anoLectivo;
		private String userName;
		private Integer userCode;
		private boolean candidato;
		private Integer numeroCandidato;
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		
		public String getBorderoInterno() {
			return borderoInterno;
		}
		public void setBorderoInterno(String borderoInterno) {
			this.borderoInterno = borderoInterno;
		}
		public String getBorderoExterno() {
			return borderoExterno;
		}
		public void setBorderoExterno(String borderoExterno) {
			this.borderoExterno = borderoExterno;
		}
		public float getValorDeposito() {
			return valorDeposito;
		}
		public void setValorDeposito(float valorDeposito) {
			this.valorDeposito = valorDeposito;
		}
		public Date getDataRegisto() {
			return dataRegisto;
		}
		public void setDataRegisto(Date dataRegisto) {
			this.dataRegisto = dataRegisto;
		}
		public Date getDataDepositoExterno() {
			return dataDepositoExterno;
		}
		public void setDataDepositoExterno(Date dataDepositoExterno) {
			this.dataDepositoExterno = dataDepositoExterno;
		}
		public Integer getAluno() {
			return aluno;
		}
		public void setAluno(Integer aluno) {
			this.aluno = aluno;
		}
		public Integer getNumeroDeAluno() {
			return numeroDeAluno;
		}
		public void setNumeroDeAluno(Integer numeroDeAluno) {
			this.numeroDeAluno = numeroDeAluno;
		}
		public Integer getInstituicao() {
			return instituicao;
		}
		public void setInstituicao(Integer instituicao) {
			this.instituicao = instituicao;
		}
		public Integer getBanco() {
			return banco;
		}
		public void setBanco(Integer banco) {
			this.banco = banco;
		}
		
		public Integer getAnoLectivo() {
			return anoLectivo;
		}
		public void setAnoLectivo(Integer anoLectivo) {
			this.anoLectivo = anoLectivo;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public Integer getUserCode() {
			return userCode;
		}
		public void setUserCode(Integer userCode) {
			this.userCode = userCode;
		}
		public boolean isCandidato() {
			return candidato;
		}
		public void setCandidato(boolean candidato) {
			this.candidato = candidato;
		}
		public Integer getNumeroCandidato() {
			return numeroCandidato;
		}
		public void setNumeroCandidato(Integer numeroCandidato) {
			this.numeroCandidato = numeroCandidato;
		}
}
