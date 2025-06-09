package jvondoellinger.ShopFree.core.entity;

import jvondoellinger.ShopFree.core.strategy.product.ProductValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductFactory {
    private final ProductValidator validator;

    public ProductFactory(ProductValidator validator) {
        this.validator = validator;
    }

    public Product produce(String publishedAt, String name, BigDecimal amount) {
        var p = new Product(publishedAt, name, amount);
        validator.validate(p);
        return p;
    }
}
