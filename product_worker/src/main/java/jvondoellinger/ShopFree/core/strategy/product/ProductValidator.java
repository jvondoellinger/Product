package jvondoellinger.ShopFree.core.strategy.product;

import jvondoellinger.ShopFree.core.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductValidator {
    private void validate(IProductValidator validator, Product product) {
        validator.validate(product);
    }

    public void validate(Product product) {
        validate(new AmountValidator(), product);
        validate(new NameValidator(), product);
    }
}
