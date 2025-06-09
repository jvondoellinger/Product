package jvondoellinger.ShopFree.workers.listeners;

import jvondoellinger.ShopFree.workers.config.ListenerConfig;
import jvondoellinger.ShopFree.workers.consumer.BasicConsumer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.Message;

import java.util.List;

public abstract class AbstractListener {
	private final BasicConsumer consumer;

	public AbstractListener(BasicConsumer consumer, ListenerConfig config) {
        this.consumer = consumer;
        this.config = config;
	}
	
	protected final ListenerConfig config;
    /// Listen
    public Mono<Void> listen() {
		return consumer.consume(config.getQueueUrl(),
						config.getMaxMessage(),
						config.getWaitTime())
				.flatMap(x
						-> process(x.messages()));
	}
    protected abstract Mono<Void> process(List<Message> messages);
    
}
