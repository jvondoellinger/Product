package jvondoellinger.ShopFree.service.promisse;

import jvondoellinger.ShopFree.core.entity.Product;
import jvondoellinger.ShopFree.core.entity.ProductFields;
import reactor.core.publisher.Mono;
import java.util.List;


public interface IBatchProductService {
    Mono<Void> batchCreate(List<ProductFields> fields);
    Mono<Void> batchUpdate(List<Product> products);
    Mono<Void> batchDelete(List<Product> partialProduct);
}
