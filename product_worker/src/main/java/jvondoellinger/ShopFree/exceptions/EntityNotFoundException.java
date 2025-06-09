package jvondoellinger.ShopFree.exceptions;

public class EntityNotFoundException extends RuntimeException {
    private EntityNotFoundException(String message) {
        super(message);
    }

    private EntityNotFoundException(String message, RuntimeException exception) {
        super(message, exception);
    }

    private EntityNotFoundException() {
        super("Entity not found");
    }

    public static EntityNotFoundException getException() {
        return new EntityNotFoundException();
    }
    public static EntityNotFoundException getException(String message) {
        return new EntityNotFoundException(message);
    }
    public static EntityNotFoundException getException(String message, RuntimeException exception) {
        return new EntityNotFoundException(message, exception);
    }
}
