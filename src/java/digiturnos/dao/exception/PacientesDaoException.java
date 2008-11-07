package digiturnos.dao.exception;

public class PacientesDaoException extends DaoException {

    public PacientesDaoException(String message) {
        super(message);
    }

    public PacientesDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
