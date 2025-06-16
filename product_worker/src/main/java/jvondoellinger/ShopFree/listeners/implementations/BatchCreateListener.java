package jvondoellinger.ShopFree.listeners.implementations;

import io.awspring.cloud.sqs.annotation.SqsListener;
import jvondoellinger.ShopFree.converter.JsonConverter;
import jvondoellinger.ShopFree.core.entity.ProductFields;
import jvondoellinger.ShopFree.listeners.interfaces.BatchProcessor;
import jvondoellinger.ShopFree.service.promisse.IBatchProductService;
import jvondoellinger.ShopFree.service.promisse.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BatchCreateListener implements BatchProcessor {
    private final IBatchProductService batchService;
    private final IProductService service;
    private final JsonConverter converter;
    public BatchCreateListener(IBatchProductService batchService, IProductService service, JsonConverter converter) {
        this.batchService = batchService;
        this.service = service;
        this.converter = converter;
    }

    @Override
	@SqsListener(value = "product-creation-test")
	public CompletableFuture<Void> process(List<String> messages) {
		return converter.convertList(messages, ProductFields.class)
				.flatMap(batchService::batchCreate)
                .toFuture();
	}

    // Method to process unitary
    @Deprecated
    public CompletableFuture<Void> process(String messages) {
        return converter.convert(messages, ProductFields.class)
                .flatMap(service::create)
                .toFuture();
    }
}
