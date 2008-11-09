package digiturnos.dao.exception;

public class ProfesionalesDaoException extends DaoException {

    public ProfesionalesDaoException(String message) {
        super(message);
    }

    public ProfesionalesDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
