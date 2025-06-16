package jvondoellinger.ShopFree.database.config;

import jvondoellinger.ShopFree.aws.config.AwsBaseConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import java.net.URI;

@Configuration

@ConfigurationProperties(prefix = "aws.dynamodb")
public class DynamoDBConfig {
    private final AwsBaseConfig awsConfig;
    private String table;

    public DynamoDBConfig(AwsBaseConfig awsConfig) {
        this.awsConfig = awsConfig;
    }

    @Bean
    public DynamoDbAsyncClient dynamoDbAsyncClient() {
        var uri = URI.create(awsConfig.getEndpoint().getStatic());
        var region = Region.of(awsConfig.getRegion().getStatic());
        var credentials = AwsBasicCredentials.create(awsConfig.getCredentials().getAccessKey(), awsConfig.getCredentials().getSecretKey());
        return DynamoDbAsyncClient.builder().endpointOverride(uri)
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
    @Bean
    public DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient(DynamoDbAsyncClient dynamoDbAsyncClient) {
        return DynamoDbEnhancedAsyncClient.builder()
                .dynamoDbClient(dynamoDbAsyncClient)
                .build();
    }


    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
