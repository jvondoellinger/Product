package jvondoellinger.ShopFree.core.strategy.abstractions;

public interface IValidator<T> {
    void validate(T source);
}
