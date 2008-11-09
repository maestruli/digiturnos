package digiturnos.dao.exception;

public class HorariosDaoException extends DaoException {

    public HorariosDaoException(String message) {
        super(message);
    }

    public HorariosDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
