package jvondoellinger.ShopFree.exceptions;

public class PartialEntityException extends RuntimeException {
    public PartialEntityException() {
        super("The Entity does not contain the required properties!");
    }
    public PartialEntityException(String message) {
        super(message);
    }
    public PartialEntityException(String message, Exception ex) {
        super(message, ex);
    }
}
