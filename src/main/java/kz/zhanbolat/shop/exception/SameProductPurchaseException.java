package kz.zhanbolat.shop.exception;

public class SameProductPurchaseException extends RuntimeException {
    public SameProductPurchaseException(String message) {
        super(message);
    }
}
