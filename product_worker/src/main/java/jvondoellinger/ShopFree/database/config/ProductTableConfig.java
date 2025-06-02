package jvondoellinger.ShopFree.database.config;

import jvondoellinger.ShopFree.aws.config.AwsBaseConfig;
import jvondoellinger.ShopFree.core.entity.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Component
public class ProductTableConfig extends DynamoDBConfig {
    public ProductTableConfig(AwsBaseConfig config) {
        super(config);
    }

    @Bean
    public DynamoDbAsyncTable<Product> dynamoDbAsyncTable(DynamoDbEnhancedAsyncClient enhancedAsyncClient) {
        return enhancedAsyncClient.table(super.table, TableSchema.fromBean(Product.class));
    }
}
