package jvondoellinger.ShopFree.service.promisse;

import jvondoellinger.ShopFree.core.entity.Product;
import jvondoellinger.ShopFree.core.entity.ProductFields;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IProductService {
    /// Requires ID
    Mono<Void> delete(Product product);
    Mono<Void> update(Product product);
    Mono<Void> create(ProductFields product);

}
