package jvondoellinger.ShopFree.core.strategy.product;

import jvondoellinger.ShopFree.core.entity.Product;
import jvondoellinger.ShopFree.core.strategy.abstractions.IValidator;
import org.springframework.stereotype.Service;

@Service
public class ProductValidator {
    private void validate(IValidator<Product> validator, Product product) {
        validator.validate(product);
    }

    public void validate(Product product) {
        validate(new AmountValidator(), product);
        validate(new NameValidator(), product);
    }
}
