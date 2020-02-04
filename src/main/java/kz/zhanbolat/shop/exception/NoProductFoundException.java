package kz.zhanbolat.shop.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class NoProductFoundException extends DaoException {
    public NoProductFoundException() {
        super();
    }

    public NoProductFoundException(String message) {
        super(message);
    }

    public NoProductFoundException(Throwable cause) {
        super(cause);
    }
}
