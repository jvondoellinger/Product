package jvondoellinger.ShopFree.workers.listeners;

import jvondoellinger.ShopFree.workers.config.CreateQueueConfig;
import jvondoellinger.ShopFree.workers.consumer.BasicConsumer;

public class CreateListener implements IListener {
    private final BasicConsumer consumer;
    private final CreateQueueConfig config;
    public CreateListener(BasicConsumer consumer, CreateQueueConfig config) {
        this.consumer = consumer;
        this.config = config;
    }
    @Override
    public void listen() {
        consumer.consume(config.getQueueUrl(),
                config.getMaxMessage(),
                config.getWaitTime());
    }
}
