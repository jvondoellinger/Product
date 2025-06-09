package jvondoellinger.ShopFree.database.config;

import jvondoellinger.ShopFree.core.entity.Product;
import jvondoellinger.ShopFree.core.entity.ProductBuilder;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

public class ProductTableSchema implements TableSchema<Product> {

    @Override
    public Product mapToItem(Map<String, AttributeValue> attributes) {
        ProductBuilder builder = ProductBuilder.builder();

        if (attributes.containsKey("id") && attributes.get("id").s() != null) {
            builder.id(attributes.get("id").s());
        }

        if (attributes.containsKey("name") && attributes.get("name").s() != null) {
            builder.name(attributes.get("name").s());
        }

        if (attributes.containsKey("publishedBy") && attributes.get("publishedBy").s() != null) {
            builder.publishedBy(attributes.get("publishedBy").s());
        }

        if (attributes.containsKey("amount") && attributes.get("amount").n() != null) {
            var amount = new BigDecimal(attributes.get("amount").n());
            builder.amount(amount);
        }

        if (attributes.containsKey("createdAt") && attributes.get("createdAt").s() != null) {
            var createdTime = OffsetDateTime.parse(attributes.get("createdAt").s());
            builder.createdAt(createdTime);
        }

        if (attributes.containsKey("updatedAt") && attributes.get("updatedAt").s() != null) {
            var updatedTime = OffsetDateTime.parse(attributes.get("updatedAt").s());
            builder.updatedAt(updatedTime);
        }

        return builder.build();
    }

    @Override
    public Map<String, AttributeValue> itemToMap(Product item, boolean ignoreNulls) {
        Map<String, AttributeValue> map = new HashMap<>();

        if (item.getId() != null || !ignoreNulls)
            map.put("id", AttributeValue.builder().s(item.getId()).build());

        if (item.getName() != null || !ignoreNulls)
            map.put("name", AttributeValue.builder().s(item.getName()).build());

        if (item.getPublishedBy() != null || !ignoreNulls)
            map.put("publishedBy", AttributeValue.builder().s(item.getPublishedBy()).build());

        if (item.getAmount() != null || !ignoreNulls)
            map.put("amount", AttributeValue.builder().n(item.getAmount().toPlainString()).build());

        if (item.getCreatedAt() != null || !ignoreNulls)
            map.put("createdAt", AttributeValue.builder().s(item.getCreatedAt().toString()).build());

        if (item.getUpdatedAt() != null || !ignoreNulls)
            map.put("updatedAt", AttributeValue.builder().s(item.getUpdatedAt().toString()).build());

        return map;
    }

    @Override
    public Map<String, AttributeValue> itemToMap(Product item, Collection<String> attributes) {
        Map<String, AttributeValue> fullMap = itemToMap(item, true);
        Map<String, AttributeValue> filteredMap = new HashMap<>();
        for (String attr : attributes) {
            if (fullMap.containsKey(attr)) {
                filteredMap.put(attr, fullMap.get(attr));
            }
        }
        return filteredMap;
    }

    @Override
    public AttributeValue attributeValue(Product item, String attributeName) {
        return switch (attributeName) {
            case "id" -> AttributeValue.builder().s(item.getId()).build();
            case "name" -> AttributeValue.builder().s(item.getName()).build();
            case "publishedBy" -> AttributeValue.builder().s(item.getPublishedBy()).build();
            case "amount" -> AttributeValue.builder().n(item.getAmount().toPlainString()).build();
            case "createdAt" -> AttributeValue.builder().s(item.getCreatedAt().toString()).build();
            case "updatedAt" -> AttributeValue.builder().s(item.getUpdatedAt().toString()).build();
            default -> null;
        };
    }

    @Override
    public TableMetadata tableMetadata() {
        return new ProductTableMetadata(); // Custom table metadata!
    }

    @Override
    public EnhancedType<Product> itemType() {
        return EnhancedType.of(Product.class);
    }

    @Override
    public List<String> attributeNames() {
        return List.of("id", "name", "publishedBy", "amount", "createdAt", "updatedAt");
    }

    @Override
    public boolean isAbstract() {
        return false;
    }
}
