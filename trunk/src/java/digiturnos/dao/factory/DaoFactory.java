package digiturnos.dao.factory;

import javax.naming.*;
import digiturnos.dao.dao.*;
import digiturnos.dao.*;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public static DaoFactory getDaoFactory() {

        if (daoFactory != null)
            return daoFactory;

        String daoImpl;

        try {
            Context ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:comp/env");
            daoImpl = (String) env.lookup("DaoFactoryImplementation");
        } catch (Exception e) {
            daoImpl = "PostgresqlDaoFactory";
            e.printStackTrace();
        }

        try {
            return new PostgresqlDaoFactory(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract TiposUsuarioDao getTiposUsuarioDao();
    public abstract UsuariosDao getUsuariosDao();
}
