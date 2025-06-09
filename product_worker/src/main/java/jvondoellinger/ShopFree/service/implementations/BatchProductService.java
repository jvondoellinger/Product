package jvondoellinger.ShopFree.service.implementations;

import jvondoellinger.ShopFree.core.entity.Product;
import jvondoellinger.ShopFree.core.entity.ProductBuilder;
import jvondoellinger.ShopFree.core.entity.ProductFields;
import jvondoellinger.ShopFree.core.repository.IProductRepository;
import jvondoellinger.ShopFree.service.promisse.IBatchProductService;
import jvondoellinger.ShopFree.service.promisse.IProductService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BatchProductService implements IBatchProductService {
    private final IProductRepository repository;

    public BatchProductService(IProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Void> batchCreate(List<ProductFields> fields) {
        List<Product> products = new ArrayList<>();
        for (var f : fields) {
            var product = ProductBuilder.builder()
                            .name(f.getName())
                            .publishedBy(f.getPublishedBy())
                            .amount(f.getAmount())
                            .build();
            products.add(product);
        }
        return repository.batchCreate(products);
    }

    @Override
    public Mono<Void> batchUpdate(List<Product> products) {
        return repository.batchUpdate(products);
    }

    @Override
    public Mono<Void> batchDelete(List<Product> partialProducts) {
        return repository.batchDelete(partialProducts);
    }
}
