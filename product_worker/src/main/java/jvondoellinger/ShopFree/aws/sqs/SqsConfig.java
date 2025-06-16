package jvondoellinger.ShopFree.aws.sqs;

import jvondoellinger.ShopFree.aws.config.AwsBaseConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class SqsConfig {
    private final AwsBaseConfig config;

    public SqsConfig(AwsBaseConfig config) {
        this.config = config;
    }

    @Bean
    public SqsAsyncClient sqsClient() {
        var c = config.getCredentials();
        var credentials = AwsBasicCredentials.create(c.getAccessKey(), c.getSecretKey());
        return SqsAsyncClient.builder()
                .endpointOverride(URI.create(config.getEndpoint().getStatic()))
                .region(software.amazon.awssdk.regions.Region.of(config.getRegion().getStatic()))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
}
