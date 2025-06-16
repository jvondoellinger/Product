package jvondoellinger.ShopFree.listeners.implementations;

import io.awspring.cloud.sqs.annotation.SqsListener;
import jvondoellinger.ShopFree.converter.JsonConverter;
import jvondoellinger.ShopFree.core.entity.Product;
import jvondoellinger.ShopFree.listeners.interfaces.BatchProcessor;
import jvondoellinger.ShopFree.service.promisse.IBatchProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BatchUpdateListener implements BatchProcessor {
    private final IBatchProductService batchService;
    private final JsonConverter converter;

    public BatchUpdateListener(IBatchProductService batchService, JsonConverter converter) {
        this.batchService = batchService;
        this.converter = converter;
    }

    @Override
    @SqsListener(value = "product-update-test")
    public CompletableFuture<Void> process(List<String> messages) {
        return converter.convertList(messages, Product.class)
                .flatMap(batchService::batchUpdate)
                .toFuture();
    }
}
