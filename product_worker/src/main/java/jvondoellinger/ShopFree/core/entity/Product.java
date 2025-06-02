package jvondoellinger.ShopFree.core.entity;

import jvondoellinger.ShopFree.fields.ProductFields;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Product extends ProductFields {
    private Integer id;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    protected Product(String publishedBy, String name, BigDecimal amount) {
        super.publishedBy = publishedBy;
        super.name = name;
        super.amount = amount;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    // Getters
    public Integer getId() {
        return id;
    }
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    // Setters
    protected void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    protected void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
    protected void setId(Integer id) {
        this.id = id;
    }


}
