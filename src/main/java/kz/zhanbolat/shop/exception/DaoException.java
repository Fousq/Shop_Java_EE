package kz.zhanbolat.shop.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class DaoException extends RuntimeException {
    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
