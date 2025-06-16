package jvondoellinger.ShopFree.database.config;

import jvondoellinger.ShopFree.aws.config.AwsBaseConfig;
import jvondoellinger.ShopFree.core.entity.Product;
import jvondoellinger.ShopFree.core.entity.ProductBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags;
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticTableSchema;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Component
public class ProductTableConfig extends DynamoDBConfig {
    public ProductTableConfig(AwsBaseConfig config) {
        super(config);
    }

    @Bean
    public DynamoDbAsyncTable<Product> dynamoDbAsyncTable(DynamoDbEnhancedAsyncClient enhancedAsyncClient) {
        System.out.println(super.getTable());
        return enhancedAsyncClient.table(super.getTable(), new ProductTableSchema()); // Custom schema
    }

    public StaticTableSchema<Product> makeSchema() {
        return StaticTableSchema.builder(Product.class)
                .newItemSupplier(() -> ProductBuilder.builder().build())
                .addAttribute(String.class, a -> a.name("id")
                        .getter(Product::getId)
                        .tags(StaticAttributeTags.primaryPartitionKey()))
                .addAttribute(String.class, a -> a.name("name")
                        .getter(Product::getName))
                .addAttribute(String.class, a -> a.name("publishedBy")
                        .getter(Product::getPublishedBy))
                .addAttribute(BigDecimal.class, a -> a.name("amount")
                        .getter(Product::getAmount))
                .addAttribute(OffsetDateTime.class, a -> a.name("createdAt")
                        .getter(Product::getCreatedAt))
                .addAttribute(OffsetDateTime.class, a -> a.name("updatedAt")
                        .getter(Product::getUpdatedAt))
                .build();
    }


}
