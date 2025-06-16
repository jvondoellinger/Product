package jvondoellinger.ShopFree.core.strategy.list;

import jvondoellinger.ShopFree.core.strategy.abstractions.IValidator;

import java.util.List;
import java.util.Objects;

public class NullOrEmptyListValidator implements IValidator<List<?>> {
    @Override
    public void validate(List<?> source) {
        Objects.requireNonNull(source, "The list can't be null");
        if(source.isEmpty()) {
            throw new IllegalArgumentException("List can't be empty");
        }
    }
}
