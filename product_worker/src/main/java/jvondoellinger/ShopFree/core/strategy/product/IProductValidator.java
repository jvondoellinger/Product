package jvondoellinger.ShopFree.core.strategy.product;

import jvondoellinger.ShopFree.core.entity.Product;

public interface IProductValidator {
    void validate(Product source);
}
