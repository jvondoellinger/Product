package jvondoellinger.ShopFree.service.implementations;

import jvondoellinger.ShopFree.core.entity.Product;
import jvondoellinger.ShopFree.core.entity.ProductBuilder;
import jvondoellinger.ShopFree.core.entity.ProductFactory;
import jvondoellinger.ShopFree.core.entity.ProductFields;
import jvondoellinger.ShopFree.core.repository.IProductRepository;
import jvondoellinger.ShopFree.core.strategy.product.ProductValidator;
import jvondoellinger.ShopFree.exceptions.PartialEntityException;
import jvondoellinger.ShopFree.service.promisse.IProductService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductService implements IProductService {
    private final IProductRepository repository;
    private final ProductFactory factory;
    private final ProductValidator validator;

    public ProductService(IProductRepository repository, ProductFactory factory, ProductValidator validator) {
        this.repository = repository;
        this.factory = factory;
        this.validator = validator;
    }

    @Override
    public Mono<Void> delete(Product product) {
        if(product.getId() == null) {
            return Mono.error(PartialEntityException::new);
        }
        return repository.delete(product);
    }

    @Override
    public Mono<Void> update(Product product) {
        if(product == null) {
            Mono.error(NullPointerException::new);
        }
        validator.validate(product);
        return repository.update(product);
    }
    @Override
    public Mono<Void> create(ProductFields fields) {
        if(fields == null) {
            return Mono.error(NullPointerException::new);
        }
        var product = ProductBuilder.builder()
                .name(fields.getName())
                .publishedBy(fields.getPublishedBy())
                .amount(fields.getAmount())
                .build();
        return repository.create(product);
    }
}
