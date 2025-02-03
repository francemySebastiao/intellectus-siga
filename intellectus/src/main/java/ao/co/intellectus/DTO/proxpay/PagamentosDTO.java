package ao.co.intellectus.DTO.proxpay;

import java.math.BigDecimal;
import java.util.Date;

public class PagamentosDTO {
	  private Integer accounting_period;
	    private Date accounting_period_end;
	    private Date accounting_period_start;
	    private BigDecimal amount;
	    private CustomFields custom_fields;
	    private Date datetime;
	    private String entity_id;
	    private String id;
	    private String product_id;
	    private String reference_id;
	    private Integer reference_number;
	    private String terminal_id;
	    private String terminal_location;
	    private String terminal_transaction_id;
	    private String terminal_type;
	    private String transaction_id;
		public Integer getAccounting_period() {
			return accounting_period;
		}
		public void setAccounting_period(Integer accounting_period) {
			this.accounting_period = accounting_period;
		}
		public Date getAccounting_period_end() {
			return accounting_period_end;
		}
		public void setAccounting_period_end(Date accounting_period_end) {
			this.accounting_period_end = accounting_period_end;
		}
		public Date getAccounting_period_start() {
			return accounting_period_start;
		}
		public void setAccounting_period_start(Date accounting_period_start) {
			this.accounting_period_start = accounting_period_start;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		public CustomFields getCustom_fields() {
			return custom_fields;
		}
		public void setCustom_fields(CustomFields custom_fields) {
			this.custom_fields = custom_fields;
		}
		public Date getDatetime() {
			return datetime;
		}
		public void setDatetime(Date datetime) {
			this.datetime = datetime;
		}
		public String getEntity_id() {
			return entity_id;
		}
		public void setEntity_id(String entity_id) {
			this.entity_id = entity_id;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getProduct_id() {
			return product_id;
		}
		public void setProduct_id(String product_id) {
			this.product_id = product_id;
		}
		public String getReference_id() {
			return reference_id;
		}
		public void setReference_id(String reference_id) {
			this.reference_id = reference_id;
		}
		public Integer getReference_number() {
			return reference_number;
		}
		public void setReference_number(Integer reference_number) {
			this.reference_number = reference_number;
		}
		public String getTerminal_id() {
			return terminal_id;
		}
		public void setTerminal_id(String terminal_id) {
			this.terminal_id = terminal_id;
		}
		public String getTerminal_location() {
			return terminal_location;
		}
		public void setTerminal_location(String terminal_location) {
			this.terminal_location = terminal_location;
		}
		public String getTerminal_transaction_id() {
			return terminal_transaction_id;
		}
		public void setTerminal_transaction_id(String terminal_transaction_id) {
			this.terminal_transaction_id = terminal_transaction_id;
		}
		public String getTerminal_type() {
			return terminal_type;
		}
		public void setTerminal_type(String terminal_type) {
			this.terminal_type = terminal_type;
		}
		public String getTransaction_id() {
			return transaction_id;
		}
		public void setTransaction_id(String transaction_id) {
			this.transaction_id = transaction_id;
		}
	    
	    
}
