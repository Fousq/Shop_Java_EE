package kz.zhanbolat.shop.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class NoUserFoundException extends DaoException {
    public NoUserFoundException(String message) {
        super(message);
    }
}
