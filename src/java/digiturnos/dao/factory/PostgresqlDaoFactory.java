package digiturnos.dao.factory;

import digiturnos.dao.dao.*;
import digiturnos.dao.jdbc.*;

public class PostgresqlDaoFactory extends DaoFactory {

    public EspecialidadesDao especialidadesDao;
    public ServiciosDao serviciosDao;
    public TiposUsuarioDao tiposUsuarioDao;
    public UsuariosDao usuariosDao;
    public PacientesDao pacientesDao;
    public ProfesionalesDao profesionalesDao;
    public TurnosDao turnosDao;
    public HorariosDao horariosDao;

    public EspecialidadesDao getEspecialidadesDao() {
        EspecialidadesDao eDao = this.especialidadesDao;
        if (eDao != null)
            return eDao;
        eDao = new EspecialidadesDaoImpl();
        return eDao;
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

    public PacientesDao getPacientesDao() {
        PacientesDao pacientesDao = this.pacientesDao;
        if (pacientesDao != null)
            return pacientesDao;
        pacientesDao = new PacientesDaoImpl();
        return pacientesDao;
    }

    public ProfesionalesDao getProfesionalesDao() {
        ProfesionalesDao profesionalesDao = this.profesionalesDao;
        if (profesionalesDao != null)
            return profesionalesDao;
        profesionalesDao = new ProfesionalesDaoImpl();
        return profesionalesDao;
    }
    
    public HorariosDao getHorariosDao() {
        HorariosDao horariosDao = this.horariosDao;
        if (horariosDao != null)
            return horariosDao;
        horariosDao = new HorariosDaoImpl();
        return horariosDao;
    }

    public TurnosDao getTurnosDao() {
        TurnosDao turnosDao = this.turnosDao;
        if (turnosDao != null)
            return turnosDao;
        turnosDao = new TurnosDaoImpl();
        return turnosDao;
    }

}
