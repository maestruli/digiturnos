package digiturnos.dao.exception;

public class ServiciosDaoException extends DaoException {

    public ServiciosDaoException(String message) {
        super(message);
    }

    public ServiciosDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
