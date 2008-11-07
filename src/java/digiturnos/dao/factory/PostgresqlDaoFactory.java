package digiturnos.dao.factory;

import digiturnos.dao.dao.*;
import digiturnos.dao.jdbc.*;

public class PostgresqlDaoFactory extends DaoFactory {

    
    public EspecialidadesDao especialidadesDao;
    public PacientesDao pacientesDao;
    public ServiciosDao serviciosDao;
    public TiposUsuarioDao tiposUsuarioDao;
    public UsuariosDao usuariosDao;

    public EspecialidadesDao getEspecialidadesDao() {
        EspecialidadesDao eDao = this.especialidadesDao;
        if (eDao != null)
            return eDao;
        eDao = new EspecialidadesDaoImpl();
        return eDao;
    }

    public PacientesDao getPacientesDao() {
        PacientesDao pDao = this.pacientesDao;
        if (pDao != null)
            return pDao;
        pDao = new PacientesDaoImpl();
        return pDao;
    }

    public ServiciosDao getServiciosDao() {
        ServiciosDao sDao = this.serviciosDao;
        if (sDao != null)
            return sDao;
        sDao = new ServiciosDaoImpl();
        return sDao;
    }

    public TiposUsuarioDao getTiposUsuarioDao() {
        TiposUsuarioDao tuDao = this.tiposUsuarioDao;
        if (tuDao != null)
            return tuDao;
        tuDao = new TiposUsuarioDaoImpl();
        return tuDao;
    }

    public UsuariosDao getUsuariosDao() {
        UsuariosDao uDao = this.usuariosDao;
        if (uDao != null)
            return uDao;
        uDao = new UsuariosDaoImpl();
        return uDao;
    }

}
