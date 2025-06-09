package jvondoellinger.ShopFree.workers.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import jvondoellinger.ShopFree.core.entity.ProductFields;
import jvondoellinger.ShopFree.service.promisse.IBatchProductService;
import jvondoellinger.ShopFree.service.promisse.IProductService;
import jvondoellinger.ShopFree.workers.config.CreateQueueConfig;
import jvondoellinger.ShopFree.workers.consumer.BasicConsumer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateListener  {
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final IProductService service;
	private final IBatchProductService batchService;
	public CreateListener(IProductService service,
						  IBatchProductService batchService) {
		this.service = service;
        this.batchService = batchService;
    }
/*	@BadCode

	public Mono<Void> process(String message) {
		try {
			System.out.println(message);
			var deserialized = objectMapper.readValue(message, ProductFields.class);
			return service.create(deserialized);
		} catch (JsonProcessingException e) {
			return Mono.error(e);
		}
	}*/
	@SqsListener(queueNames = "product-creation-test")
	public Mono<Void> processS(List<String> messages) throws JsonProcessingException {
		List<ProductFields> products = new ArrayList<>();
		for (var message : messages) {
			var deserialized = objectMapper.readValue(message, ProductFields.class);
			System.out.println(deserialized.getName());
			System.out.println(deserialized.getName());
			System.out.println(deserialized.getPublishedBy());
			products.add(deserialized);
		}
		if(!products.isEmpty()){
			System.out.println("Processing " + products.stream().count() + " messages!");
			return batchService.batchCreate(products);
		}
		return Mono.empty();
	}
}
