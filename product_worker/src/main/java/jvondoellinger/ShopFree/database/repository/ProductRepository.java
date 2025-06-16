package jvondoellinger.ShopFree.database.repository;

import jvondoellinger.ShopFree.core.entity.Product;
import jvondoellinger.ShopFree.core.repository.IProductRepository;
import jvondoellinger.ShopFree.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.model.BatchWriteItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.WriteBatch;

import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {
    private final DynamoDbAsyncTable<Product> table;
    private final DynamoDbEnhancedAsyncClient client;

    public ProductRepository(DynamoDbAsyncTable<Product> table, DynamoDbEnhancedAsyncClient client) {
        this.table = table;
        this.client = client;
    }

    @Override
    public Mono<Void> create(Product product) {
        return Mono.fromFuture(table.putItem(product));
    }

    @Override
    public Mono<Void> batchCreate(List<Product> products) {
        var writeBatchBuilder = getBuilder();
        for(var product : products) {
            writeBatchBuilder.addPutItem(product);
        }
        return Mono.fromFuture(client.batchWriteItem(getRequest(writeBatchBuilder)))
                .then();
    }

    @Override
    public Mono<Void> update(Product product) {
        return Mono.fromFuture(table.putItem(product));
    }

    @Override
    public Mono<Void> batchUpdate(List<Product> partialProduct) {
        if (!validList(partialProduct)) {
            return Mono.empty();
        }
        var batchBuilder = WriteBatch.builder(Product.class).mappedTableResource(table);
        partialProduct.forEach(product -> {
            product.onUpdated();
            batchBuilder.addPutItem(product);
        });
        var request = getRequest(batchBuilder);
        return Mono.just(client.batchWriteItem(request))
                .then();
    }

    @Override
    public Mono<Void> delete(Product product) {
        var future = table.deleteItem(x -> {
            x.key(k -> k.partitionValue(product.getId()));
        });
        return Mono.fromFuture(future).then();
    }

    @Override
    public Mono<Void> batchDelete(List<Product> partialProducts) {
        if (!validList(partialProducts)) {
            return Mono.empty();
        }
        var writeBatchBuilder = getBuilder();
        for (var product : partialProducts) {
            writeBatchBuilder.addDeleteItem(r -> {
                r.key(k -> k.partitionValue(product.getId()));
            });
        }
        var batchWriteRequest = getRequest(writeBatchBuilder);
        return Mono.fromFuture(client.batchWriteItem(batchWriteRequest))
                .then();
    }

    @Override
    public Mono<Product> getById(String identifier) {
        return Mono.fromFuture(() -> table.getItem(r -> r.key(k -> k.partitionValue(identifier))))
                .flatMap(product -> {
                    if (product == null) {
                        return Mono.empty();
                    }
                    return Mono.just(product);
                })
                .switchIfEmpty(Mono.error(EntityNotFoundException.getException()));
    }

    @Override
    public Flux<Product> scan() {
        var items = table.scan().items();
        return Flux.from(items);
    }

    private WriteBatch.Builder<Product> getBuilder() {
        return WriteBatch.builder(Product.class)
                .mappedTableResource(table);
    }

    private BatchWriteItemEnhancedRequest getRequest(WriteBatch.Builder<Product> builder) {
        return BatchWriteItemEnhancedRequest.builder()
                .writeBatches(builder.build())
                .build();
    }

    private boolean validList(List<Product> partialProducts){
        if (partialProducts == null){
            return false;
        }
        return !partialProducts.isEmpty();
    }
}
