package jvondoellinger.ShopFree.core.entity;

import java.math.BigDecimal;

public class ProductFieldsBuilder {
    private final ProductFields fields;

    protected ProductFieldsBuilder() {
        this.fields = new ProductFields();
    }

    public static ProductFieldsBuilder builder() {
        return new ProductFieldsBuilder();
    }


    public ProductFields build() {
        return fields;
    }
}
