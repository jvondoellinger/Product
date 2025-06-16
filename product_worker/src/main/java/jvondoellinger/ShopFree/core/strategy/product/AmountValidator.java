package jvondoellinger.ShopFree.core.strategy.product;

import jvondoellinger.ShopFree.core.entity.Product;
import jvondoellinger.ShopFree.core.strategy.abstractions.IValidator;

import java.math.BigDecimal;

public class AmountValidator implements IValidator<Product> {
    @Override
    public void validate(Product product) {
        if (product.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The value must be greater than zero!");
        }
    }
}
