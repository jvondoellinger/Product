package jvondoellinger.ShopFree.core.entity;

import jvondoellinger.ShopFree.core.strategy.product.AmountValidator;
import jvondoellinger.ShopFree.core.strategy.product.NameValidator;
import jvondoellinger.ShopFree.core.strategy.product.ProductValidator;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class ProductFactory {
    private final ProductValidator validator = new ProductValidator();

    public Product produce(String publishedAt, String name, BigDecimal amount) {
        var p = new Product(publishedAt, name, amount);
        validator.validate(new AmountValidator(), p);
        validator.validate(new NameValidator(), p);
        return p;
    }
}
