package jvondoellinger.ShopFree.core.repository;

import jvondoellinger.ShopFree.core.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IProductRepository {
    // Insertion
    Mono<Void> create(Product product);
    Mono<Void> batchCreate(List<Product> products);
    // Update / Put
    Mono<Void> update(Product partialProduct);
    Mono<Void> batchUpdate(List<Product> partialProducts);
    // Delete
    Mono<Void> delete(Product partialProduct);
    Mono<Void> batchDelete(List<Product> partialProducts);
    // Find
    Mono<Product> getById(String identifier);
    Flux<Product> scan();
}
