package kz.zhanbolat.shop.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class SameProductPurchaseException extends RuntimeException {
    public SameProductPurchaseException(String message) {
        super(message);
    }
}
