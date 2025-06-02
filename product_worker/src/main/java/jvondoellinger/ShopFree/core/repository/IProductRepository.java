package jvondoellinger.ShopFree.core.repository;

import jvondoellinger.ShopFree.core.entity.Product;
import reactor.core.publisher.Mono;

public interface IProductRepository {
    Mono<Void> create(Product product);

    Mono<Void> update(Product product);

    Mono<Void> delete(Product product);
}
