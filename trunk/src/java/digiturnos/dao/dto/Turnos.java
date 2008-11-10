package digiturnos.dao.dto;

import digiturnos.dao.dao.PacientesDao;
import digiturnos.dao.dao.ProfesionalesDao;
import digiturnos.dao.exception.PacientesDaoException;
import digiturnos.dao.exception.ProfesionalesDaoException;
import digiturnos.dao.factory.DaoFactory;
import java.io.Serializable;

public class Turnos implements Serializable, Cloneable {

    private final static int SEED = 37;

    /** idturno  4  null */
    protected Integer idturno;
    /** idpaciente  4  Foriegn Key: idpaciente  null */
    protected Integer idpaciente;
    /** idprofesional  4  Foriegn Key: idprofesional  null */
    protected Integer idprofesional;
    /** fecha  91  null */
    protected java.sql.Date fecha;
    /** hora  92  null */
    protected java.sql.Time hora;
    
    private Profesionales profesional;
    private String nombreProfesional;
    private String nombreEspecialidad;
    private Pacientes paciente;
    private String nombrePaciente;

    /** Creates a dto for the turnos table */
    public Turnos() {
    }


    /** 
     * Gets the value for idturno  null
     * null
     */
    public Integer getIdturno() {
        return idturno;
    }

    /** 
     * Sets the value for idturno  null
     * null
     */
    public void setIdturno(Integer idturno) {
        this.idturno = idturno;
    }

    /** 
     * Gets the value for idpaciente  null
     * null
     */
    public Integer getIdpaciente() {
        return idpaciente;
    }

    /** 
     * Sets the value for idpaciente  null
     * null
     */
    public void setIdpaciente(Integer idpaciente) {
        this.idpaciente = idpaciente;
    }

    /** 
     * Gets the value for idprofesional  null
     * null
     */
    public Integer getIdprofesional() {
        return idprofesional;
    }

    /** 
     * Sets the value for idprofesional  null
     * null
     */
    public void setIdprofesional(Integer idprofesional) {
        this.idprofesional = idprofesional;
    }

    /** 
     * Gets the value for fecha  null
     * null
     */
    public java.sql.Date getFecha() {
        return fecha;
    }

    /** 
     * Sets the value for fecha  null
     * null
     */
    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    /** 
     * Gets the value for hora  null
     * null
     */
    public java.sql.Time getHora() {
        return hora;
    }

    /** 
     * Sets the value for hora  null
     * null
     */
    public void setHora(java.sql.Time hora) {
        this.hora = hora;
    }

    public TurnosPK createPK() {
        return new TurnosPK(idturno);
    }

    public Object clone() {
        try {
            Turnos o = (Turnos) super.clone();
            o.setIdturno(new Integer(this.getIdturno().intValue()));
            o.setIdpaciente(new Integer(this.getIdpaciente().intValue()));
            o.setIdprofesional(new Integer(this.getIdprofesional().intValue()));
            o.setFecha((java.sql.Date) this.getFecha().clone());
            return o;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof Turnos))
            return false;
        Turnos that = (Turnos) o;
        return (
                compare(this.getIdturno(), that.getIdturno()) &&
                compare(this.getIdpaciente(), that.getIdpaciente()) &&
                compare(this.getIdprofesional(), that.getIdprofesional()) &&
                compare(this.getFecha(), that.getFecha()) &&
                compare(this.getHora(), that.getHora())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
        result = hash(result, this.getIdturno());
        result = hash(result, this.getIdpaciente());
        result = hash(result, this.getIdprofesional());
        result = hash(result, this.getFecha());
        result = hash(result, this.getHora());
        return result;
    }

    private int hash( int seed , Object o ) {
        int result = seed;
        if ( o == null) {
            result = 37 * seed;
        } else {
            result = 37 * seed * o.hashCode();
        }
        return result;
    }

    public Profesionales getProfesional() throws ProfesionalesDaoException {
        if (this.profesional==null) {
            ProfesionalesDao pdao = DaoFactory.getDaoFactory().getProfesionalesDao();
            Profesionales p;
            p = pdao.findByPrimaryKey(this.idprofesional);
            setProfesional(p);
            setNombreProfesional(p.nombre);
            setNombreEspecialidad(p.getEspecialidad());
        }
        return profesional;
    }

    public void setProfesional(Profesionales profesional) {
        this.profesional = profesional;
    }

    public String getNombreProfesional() {
        return nombreProfesional;
    }

    public void setNombreProfesional(String nombreProfesional) {
        this.nombreProfesional = nombreProfesional;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreServicio) {
        this.nombreEspecialidad = nombreServicio;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public Pacientes getPaciente() throws PacientesDaoException {
        if (this.paciente==null) {
            PacientesDao pdao = DaoFactory.getDaoFactory().getPacientesDao();
            Pacientes p;
            p = pdao.findByPrimaryKey(this.idpaciente);
            setPaciente(p);
            setNombrePaciente(p.nombre);
        }
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }
}
