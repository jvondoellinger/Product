package jvondoellinger.ShopFree.database.repository;

import jvondoellinger.ShopFree.core.entity.Product;
import jvondoellinger.ShopFree.core.repository.IProductRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;

@Repository
public class ProductRepository implements IProductRepository {
    private final DynamoDbAsyncTable<Product> table;

    public ProductRepository(DynamoDbAsyncTable<Product> table) {
        this.table = table;
    }

    @Override
    public Mono<Void> create(Product product) {
        return Mono.fromFuture(table.putItem(product));
    }

    @Override
    public Mono<Void> update(Product product) {
        return Mono.fromFuture(table.putItem(product));
    }

    @Override
    public Mono<Void> delete(Product product) {
        return Mono.fromFuture(table.deleteItem(product)).then();
    }
}
