package jvondoellinger.ShopFree.core.strategy.product;

import jvondoellinger.ShopFree.core.entity.Product;
import jvondoellinger.ShopFree.core.strategy.abstractions.IValidator;

public class NameValidator implements IValidator<Product> {
    @Override
    public void validate(Product source) {
        var name = source.getName();
        if (name == null) {
            throw new NullPointerException("Product name cannot be null");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (name.length() <= 2) {
            throw new IllegalArgumentException("Product name too short!");
        }
    }
}
