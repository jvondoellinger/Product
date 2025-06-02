package jvondoellinger.ShopFree.workers.consumer;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

@Service
public class BasicConsumer {
    private final SqsAsyncClient sqsAsyncClient;

    public BasicConsumer(SqsAsyncClient sqsAsyncClient) {
        this.sqsAsyncClient = sqsAsyncClient;
    }

    public Mono<ReceiveMessageResponse> consume(String queueUrl, Integer maxMessages, Integer waitTimeInSeconds) {
        return Mono.fromFuture(() -> sqsAsyncClient.receiveMessage(
                ReceiveMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .maxNumberOfMessages(maxMessages)
                        .waitTimeSeconds(waitTimeInSeconds)
                        .build()
        ));
    }
}
