package jvondoellinger.ShopFree.core.strategy.product;

import jvondoellinger.ShopFree.core.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductValidator {
    public void validate(IProductValidator validator, Product product) {
        validator.validate(product);
    }
}
