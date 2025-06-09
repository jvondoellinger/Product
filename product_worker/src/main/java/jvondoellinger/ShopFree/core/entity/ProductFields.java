package jvondoellinger.ShopFree.core.entity;

import java.math.BigDecimal;

public class ProductFields {
    protected String publishedBy; // Can represent an identifier, for example...
    protected String name;
    protected BigDecimal amount;

    // Getters
    public String getName() {
        return name;
    }
    public String getPublishedBy() {
        return publishedBy;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    // Setters
    protected void setPublishedBy(String publishedBy) {
        this.publishedBy = publishedBy;
    }
    protected void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    protected void setName(String name) {
        this.name = name;
    }
}
