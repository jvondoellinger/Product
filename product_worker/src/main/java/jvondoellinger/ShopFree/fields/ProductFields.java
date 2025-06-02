package jvondoellinger.ShopFree.fields;

import java.math.BigDecimal;

public class ProductFields {
    protected String publishedBy; // Can represent an identifier, for example...
    protected String name;
    protected BigDecimal amount;

    public String getName() {
        return name;
    }
    public String getPublishedBy() {
        return publishedBy;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    protected void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    protected void setPublishedBy(String publishedBy) {
        this.publishedBy = publishedBy;
    }
    protected void setProduct(String name) {
        this.name = name;
    }

}
