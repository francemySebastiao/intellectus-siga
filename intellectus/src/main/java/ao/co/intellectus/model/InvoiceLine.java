package ao.co.intellectus.model;

import javax.xml.bind.annotation.XmlElement;

public class InvoiceLine {

	private String lineNumber;
    private String productCode;
    private String productDescription;
    private double quantity;
    private double unitPrice;
    private double taxAmount;
    private double lineExtensionAmount;

    @XmlElement(name = "LineNumber")
    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    @XmlElement(name = "ProductCode")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @XmlElement(name = "ProductDescription")
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @XmlElement(name = "Quantity")
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @XmlElement(name = "UnitPrice")
    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @XmlElement(name = "TaxAmount")
    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    @XmlElement(name = "LineExtensionAmount")
    public double getLineExtensionAmount() {
        return lineExtensionAmount;
    }

    public void setLineExtensionAmount(double lineExtensionAmount) {
        this.lineExtensionAmount = lineExtensionAmount;
    }
}
