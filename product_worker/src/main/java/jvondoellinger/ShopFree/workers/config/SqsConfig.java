package jvondoellinger.ShopFree.workers.config;

import jvondoellinger.ShopFree.aws.config.AwsBaseConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

import java.net.URI;

@Configuration
@ConfigurationProperties(prefix = "aws.sqs")
public class SqsConfig {
    private final AwsBaseConfig awsConfig;

    public SqsConfig(AwsBaseConfig awsConfig){
        this.awsConfig = awsConfig;
    }

    // Settings from application-?.yaml
    private String region; // AWS region

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .endpointOverride(URI.create(awsConfig.getEndpoint()))
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(awsConfig.getAccessKeyId(), awsConfig.getSecretKey())
                )).build();
    }
}
