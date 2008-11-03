package digiturnos.dao.exception;

public class TiposUsuarioDaoException extends DaoException {

    public TiposUsuarioDaoException(String message) {
        super(message);
    }

    public TiposUsuarioDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
