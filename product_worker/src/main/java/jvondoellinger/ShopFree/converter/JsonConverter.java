package jvondoellinger.ShopFree.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jvondoellinger.ShopFree.core.strategy.list.ListValidator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class JsonConverter {
    private final ObjectMapper mapper;
    private final ListValidator validator;

    public JsonConverter(ObjectMapper mapper, ListValidator validator) {
        this.mapper = mapper;
        this.validator = validator;
    }

    public <T> Mono<T> convert(String json, Class<T> target) {
        try {
            var entity = mapper.readValue(json, target);
            return Mono.just(entity);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public <T> Mono<List<T>> convertList(List<String> messages, Class<T> target)  {
        validator.validate(messages);
        return Mono.fromCallable(() -> messages.stream()
                .map(message -> {
                    try{
                        return mapper.readValue(message, target);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toList()
        );
    }
}
