package jvondoellinger.ShopFree.core.strategy.list;

import jvondoellinger.ShopFree.core.strategy.abstractions.IValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListValidator {
    private void validate(IValidator<List<?>> validator, List<?> product) {
        validator.validate(product);
    }

    public void validate(List<?> list) {
        validate(new NullOrEmptyListValidator(), list);
    }
}
