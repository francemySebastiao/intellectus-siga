package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.TipoProvaExtraOrdinaria;

public class BuscaCursoDeVerao {
      private String pagamento;
      private Date inicio;
      private Date fim;
      private Integer codigoCurso;
      private Integer codigoDisciplina;
      @Enumerated(EnumType.STRING)
      private TipoProvaExtraOrdinaria prova;
      private String estadoPagamento;

      
	public String getPagamento() {
		return pagamento;
	}
	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	public Integer getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(Integer codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	public Integer getCodigoDisciplina() {
		return codigoDisciplina;
	}
	public void setCodigoDisciplina(Integer codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	public TipoProvaExtraOrdinaria getProva() {
		return prova;
	}
	public void setProva(TipoProvaExtraOrdinaria prova) {
		this.prova = prova;
	}
	public String getEstadoPagamento() {
		return estadoPagamento;
	}
	public void setEstadoPagamento(String estadoPagamento) {
		this.estadoPagamento = estadoPagamento;
	}  
	
	
}
