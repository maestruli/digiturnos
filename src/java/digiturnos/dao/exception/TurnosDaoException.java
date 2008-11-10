package digiturnos.dao.exception;

public class TurnosDaoException extends DaoException {

    public TurnosDaoException(String message) {
        super(message);
    }

    public TurnosDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
