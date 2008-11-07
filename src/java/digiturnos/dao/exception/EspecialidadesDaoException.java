package digiturnos.dao.exception;

public class EspecialidadesDaoException extends DaoException {

    public EspecialidadesDaoException(String message) {
        super(message);
    }

    public EspecialidadesDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
