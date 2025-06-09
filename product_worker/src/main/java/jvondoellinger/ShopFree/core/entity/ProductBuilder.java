package jvondoellinger.ShopFree.core.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class ProductBuilder  {
    private final Product product;
    private ProductBuilder() {
        super();
        product = new Product();
    }
    private ProductBuilder(Product product) {
        super();
        this.product = product;
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static ProductBuilder clone(Product product) {
        return new ProductBuilder(product);
    }

    public ProductBuilder id(String id){
        product.setId(id);
        return this;
    }

    public ProductBuilder createdAt(OffsetDateTime dateTime) {
        product.setCreatedAt(dateTime);
        return this;
    }

    public ProductBuilder updatedAt(OffsetDateTime dateTime) {
        product.setUpdatedAt(dateTime);
        return this;
    }

    public ProductBuilder name(String name) {
        product.name = name;
        return this;
    }

    public ProductBuilder amount(BigDecimal amount) {
        product.amount = amount;
        return this;
    }

    public ProductBuilder publishedBy(String publishedBy){
        product.publishedBy = publishedBy;
        return this;
    }

    public Product build() {
        return product;
    }
}
