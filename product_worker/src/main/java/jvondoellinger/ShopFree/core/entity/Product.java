package jvondoellinger.ShopFree.core.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Product extends ProductFields {

    private String id = UUID.randomUUID().toString(); // identifier
    private OffsetDateTime createdAt = OffsetDateTime.now();
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    public Product() {
    }

    protected Product(String publishedBy, String name, BigDecimal amount) {
        super.publishedBy = publishedBy;
        super.name = name;
        super.amount = amount;
        id = UUID.randomUUID().toString();
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();

    }

    // Getters
    public String getId() {
        return id;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    // Setters
    protected void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
