package kz.zhanbolat.shop.exception;

public class NoProductFoundException extends DaoException {
    public NoProductFoundException() {
        super();
    }

    public NoProductFoundException(String message) {
        super(message);
    }

    public NoProductFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoProductFoundException(Throwable cause) {
        super(cause);
    }
}
