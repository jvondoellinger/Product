package jvondoellinger.ShopFree.products.worker.creation;

import jvondoellinger.ShopFree.core.entity.Product;
import jvondoellinger.ShopFree.core.entity.ProductBuilder;
import jvondoellinger.ShopFree.core.entity.ProductFactory;
import jvondoellinger.ShopFree.core.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrudsProducts {
    private final IProductRepository repository;
    private final ProductFactory factory;
    private final List<Product> products = new ArrayList<>();

    private final short MAX_SIZE = 25;

    public CrudsProducts(IProductRepository repository, ProductFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public void createLoop() {
        for (int i = 0; i < MAX_SIZE; i++) {
            var p = factory.produce("Batata", "123"+i, BigDecimal.valueOf(1500.20));
            products.add(p);
            repository.create(p).block();
        }
    }
    public void deleteLoop() {
        repository.scan()
                .flatMap(p -> repository.delete(p).thenReturn(p))
                .collectList()
                .block();
    }
    public void updateLoop() {
        repository.scan()
                .map(p -> {
                    var pb = ProductBuilder.clone(p)
                            .name("Einstein")
                            .updatedAt(OffsetDateTime.now())
                            .build();
                    return repository.update(pb).thenReturn(pb);
                })
                .collectList()
                .block();
    }

    public void batchCreate() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < MAX_SIZE; i++) {
            products.add(factory.produce("System", "System", new BigDecimal(i)));
            products.add(factory.produce("System", "System", new BigDecimal(1500)));
        }
        repository.batchCreate(products).block();
    }

    public void batchUpdate() {
        repository.scan()
                .map(x ->
                        ProductBuilder.clone(x)
                                .name("System-test-batch-update")
                                .updatedAt(OffsetDateTime.now())
                                .build()
                )
                .buffer(MAX_SIZE)
                .flatMap(batch -> repository.batchUpdate(batch))
                .then()
                .block();
    }
    public void batchDelete() {
        repository.scan()
                .buffer(MAX_SIZE) // Agrupa em lotes de até 25
                .flatMap(batch -> repository.batchDelete(batch))
                .then()
                .block();
    }
}
