package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "V_PROG_PROCESSAMENTO_FATURA_RECIBO")
public class GuiaPagamentoCodigo {

    public GuiaPagamentoCodigo(Integer codigo){
        setId(codigo);
    }
    @Id
    private Integer id;

    private String numeroGuia;

    private String hashFacturaRecibo;

    private String numeroFacturaRecibo;

    private Date dataEmissaoFr;
    
    @Temporal(TemporalType.DATE)
	private Date dataLiquidacao;
    

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNumeroGuia() {
        return numeroGuia;
    }
    public void setNumeroGuia(String numero_guia) {
        this.numeroGuia = numero_guia;
    }
    public String getHashFacturaRecibo() {
        return hashFacturaRecibo;
    }
    public void setHashFacturaRecibo(String hashFacturaRecibo) {
        this.hashFacturaRecibo = hashFacturaRecibo;
    }
    public String getNumeroFacturaRecibo() {
        return numeroFacturaRecibo;
    }
    public void setNumeroFacturaRecibo(String numeroFacturaRecibo) {
        this.numeroFacturaRecibo = numeroFacturaRecibo;
    }
    public Date getDataEmissaoFr() {
        return dataEmissaoFr;
    }
    public void setDataEmissaoFr(Date dataEmissaoFr) {
        this.dataEmissaoFr = dataEmissaoFr;
    }
    public Date getDataLiquidacao() {
        return dataLiquidacao;
    }
    public void setDataLiquidacao(Date dataLiquidacao) {
        this.dataLiquidacao = dataLiquidacao;
    }

}
