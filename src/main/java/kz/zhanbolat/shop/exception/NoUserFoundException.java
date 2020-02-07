package kz.zhanbolat.shop.exception;

public class NoUserFoundException extends DaoException {
    public NoUserFoundException(String message) {
        super(message);
    }
}
