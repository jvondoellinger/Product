package jvondoellinger.ShopFree.core.strategy.product;

import jvondoellinger.ShopFree.core.entity.Product;
import java.math.BigDecimal;

public class AmountValidator implements IProductValidator {
    @Override
    public void validate(Product product) {
        if (product.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The value must be greater than zero!");
        }
    }
}
