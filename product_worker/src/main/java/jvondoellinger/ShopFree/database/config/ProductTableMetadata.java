package jvondoellinger.ShopFree.database.config;

import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType;
import software.amazon.awssdk.enhanced.dynamodb.IndexMetadata;
import software.amazon.awssdk.enhanced.dynamodb.KeyAttributeMetadata;
import software.amazon.awssdk.enhanced.dynamodb.TableMetadata;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductTableMetadata implements TableMetadata {
    @Override
    public String indexPartitionKey(String indexName) {
        return "id";
    }

    @Override
    public Optional<String> indexSortKey(String indexName) {
        return Optional.empty();
    }

    @Override
    public <T> Optional<T> customMetadataObject(String key, Class<? extends T> objectClass) {
        return Optional.empty();
    }

    @Override
    public Collection<String> indexKeys(String indexName) {
        return List.of();
    }

    @Override
    public Collection<String> allKeys() {
        return List.of("id");  // chave primária da tabela
    }

    @Override
    public Collection<IndexMetadata> indices() {
        return List.of();
    }

    @Override
    public Map<String, Object> customMetadata() {
        return Map.of();
    }

    @Override
    public Collection<KeyAttributeMetadata> keyAttributes() {
        return List.of(new KeyAttributeMetadata() {
            @Override
            public String name() {
                return "id";
            }

            @Override
            public AttributeValueType attributeValueType() {
                return AttributeValueType.S;
            }
        });
    }

    @Override
    public Optional<ScalarAttributeType> scalarAttributeType(String keyAttribute) {
        if ("id".equals(keyAttribute)) {
            return Optional.of(ScalarAttributeType.S);
        }
        return Optional.empty();
    }
}
