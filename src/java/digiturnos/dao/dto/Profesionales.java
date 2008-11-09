package digiturnos.dao.dto;

import java.util.*;
import java.io.Serializable;

public class Profesionales implements Serializable, Cloneable {

    private final static int SEED = 37;

    /** idprofesional  4  null */
    protected Integer idprofesional;
    /** dni  4  null */
    protected Integer dni;
    /** nombre  12  null */
    protected String nombre;
    /** sexo  1  null */
    protected String sexo;
    /** fechanacimiento  91  null */
    protected java.sql.Date fechanacimiento;
    /** domicilio  12  null */
    protected String domicilio;
    /** telefono  1  null */
    protected String telefono;
    /** celular  1  null */
    protected String celular;
    /** email  12  null */
    protected String email;
    /** observaciones  12  null */
    protected String observaciones;
    /** idespecialidad  4  Foriegn Key: idespecialidad  null */
    protected Integer idespecialidad;

    /** Creates a dto for the profesionales table */
    public Profesionales() {
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
     * Gets the value for dni  null
     * null
     */
    public Integer getDni() {
        return dni;
    }

    /** 
     * Sets the value for dni  null
     * null
     */
    public void setDni(Integer dni) {
        this.dni = dni;
    }

    /** 
     * Gets the value for nombre  null
     * null
     */
    public String getNombre() {
        return nombre;
    }

    /** 
     * Sets the value for nombre  null
     * null
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** 
     * Gets the value for sexo  null
     * null
     */
    public String getSexo() {
        return sexo;
    }

    /** 
     * Sets the value for sexo  null
     * null
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /** 
     * Gets the value for fechanacimiento  null
     * null
     */
    public java.sql.Date getFechanacimiento() {
        return fechanacimiento;
    }

    /** 
     * Sets the value for fechanacimiento  null
     * null
     */
    public void setFechanacimiento(java.sql.Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    /** 
     * Gets the value for domicilio  null
     * null
     */
    public String getDomicilio() {
        return domicilio;
    }

    /** 
     * Sets the value for domicilio  null
     * null
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /** 
     * Gets the value for telefono  null
     * null
     */
    public String getTelefono() {
        return telefono;
    }

    /** 
     * Sets the value for telefono  null
     * null
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /** 
     * Gets the value for celular  null
     * null
     */
    public String getCelular() {
        return celular;
    }

    /** 
     * Sets the value for celular  null
     * null
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /** 
     * Gets the value for email  null
     * null
     */
    public String getEmail() {
        return email;
    }

    /** 
     * Sets the value for email  null
     * null
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** 
     * Gets the value for observaciones  null
     * null
     */
    public String getObservaciones() {
        return observaciones;
    }

    /** 
     * Sets the value for observaciones  null
     * null
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /** 
     * Gets the value for idespecialidad  null
     * null
     */
    public Integer getIdespecialidad() {
        return idespecialidad;
    }

    /** 
     * Sets the value for idespecialidad  null
     * null
     */
    public void setIdespecialidad(Integer idespecialidad) {
        this.idespecialidad = idespecialidad;
    }

    public ProfesionalesPK createPK() {
        return new ProfesionalesPK(idprofesional);
    }

    public Object clone() {
        try {
            Profesionales o = (Profesionales) super.clone();
            o.setIdprofesional(new Integer(this.getIdprofesional().intValue()));
            o.setDni(new Integer(this.getDni().intValue()));
            o.setNombre(new String(this.getNombre()));
            o.setSexo(new String(this.getSexo()));
            o.setFechanacimiento((java.sql.Date) this.getFechanacimiento().clone());
            o.setDomicilio(new String(this.getDomicilio()));
            o.setTelefono(new String(this.getTelefono()));
            o.setCelular(new String(this.getCelular()));
            o.setEmail(new String(this.getEmail()));
            o.setObservaciones(new String(this.getObservaciones()));
            o.setIdespecialidad(new Integer(this.getIdespecialidad().intValue()));
            return o;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof Profesionales))
            return false;
        Profesionales that = (Profesionales) o;
        return (
                compare(this.getIdprofesional(), that.getIdprofesional()) &&
                compare(this.getDni(), that.getDni()) &&
                compare(this.getNombre(), that.getNombre()) &&
                compare(this.getSexo(), that.getSexo()) &&
                compare(this.getFechanacimiento(), that.getFechanacimiento()) &&
                compare(this.getDomicilio(), that.getDomicilio()) &&
                compare(this.getTelefono(), that.getTelefono()) &&
                compare(this.getCelular(), that.getCelular()) &&
                compare(this.getEmail(), that.getEmail()) &&
                compare(this.getObservaciones(), that.getObservaciones()) &&
                compare(this.getIdespecialidad(), that.getIdespecialidad())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
        result = hash(result, this.getIdprofesional());
        result = hash(result, this.getDni());
        result = hash(result, this.getNombre());
        result = hash(result, this.getSexo());
        result = hash(result, this.getFechanacimiento());
        result = hash(result, this.getDomicilio());
        result = hash(result, this.getTelefono());
        result = hash(result, this.getCelular());
        result = hash(result, this.getEmail());
        result = hash(result, this.getObservaciones());
        result = hash(result, this.getIdespecialidad());
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

}
