package digiturnos.dao.exception;

public class UsuariosDaoException extends DaoException {

    public UsuariosDaoException(String message) {
        super(message);
    }

    public UsuariosDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
