package jvondoellinger.ShopFree.workers.config;

import jvondoellinger.ShopFree.aws.config.AwsBaseConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class SqsConfig {
    private final AwsBaseConfig awsConfig;

    public SqsConfig(AwsBaseConfig awsConfig){
        this.awsConfig = awsConfig;
    }

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .endpointOverride(URI.create(awsConfig.getEndpoint().getStatic()))
                .region(Region.of(awsConfig.getRegion().getStatic()))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(awsConfig.getCredentials().getAccessKey(),
                                awsConfig.getCredentials().getSecretKey())
                )).build();
    }
}
