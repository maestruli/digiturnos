package digiturnos.dao.factory;

import digiturnos.dao.dao.*;
import digiturnos.dao.jdbc.*;

public class PostgresqlDaoFactory extends DaoFactory {

    public TiposUsuarioDao tiposUsuarioDao;
    public UsuariosDao usuariosDao;

    public TiposUsuarioDao getTiposUsuarioDao() {
        TiposUsuarioDao tUsuarioDao = this.tiposUsuarioDao;
        if (tUsuarioDao != null)
            return tUsuarioDao;
        tUsuarioDao = new TiposUsuarioDaoImpl();
        return tUsuarioDao;
    }

    public UsuariosDao getUsuariosDao() {
        UsuariosDao uDao = this.usuariosDao;
        if (uDao != null)
            return uDao;
        uDao = new UsuariosDaoImpl();
        return uDao;
    }

}
