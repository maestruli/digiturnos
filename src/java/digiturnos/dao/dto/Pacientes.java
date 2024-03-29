package digiturnos.dao.dto;

import java.util.*;
import java.io.Serializable;

public class Pacientes implements Serializable, Cloneable {

    private final static int SEED = 37;

    /** idpaciente  4  null */
    protected Integer idpaciente;
    /** dni  4  null */
    protected Integer dni;
    /** nombre  12  null */
    protected String nombre;
    /** sexo  1  null */
    protected String sexo;
    /** domicilio  12  null */
    protected String domicilio;
    /** telefono  1  null */
    protected String telefono;
    /** celular  1  null */
    protected String celular;
    /** email  12  null */
    protected String email;
    /** fechanacimiento  91  null */
    protected java.sql.Date fechanacimiento;
    /** observaciones  12  null */
    protected String observaciones;

    /** Creates a dto for the pacientes table */
    public Pacientes() {
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

    public PacientesPK createPK() {
        return new PacientesPK(idpaciente);
    }

    public Object clone() {
        try {
            Pacientes o = (Pacientes) super.clone();
            o.setIdpaciente(new Integer(this.getIdpaciente().intValue()));
            o.setDni(new Integer(this.getDni().intValue()));
            o.setNombre(new String(this.getNombre()));
            o.setSexo(new String(this.getSexo()));
            o.setDomicilio(new String(this.getDomicilio()));
            o.setTelefono(new String(this.getTelefono()));
            o.setCelular(new String(this.getCelular()));
            o.setEmail(new String(this.getEmail()));
            o.setFechanacimiento((java.sql.Date) this.getFechanacimiento().clone());
            o.setObservaciones(new String(this.getObservaciones()));
            return o;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof Pacientes))
            return false;
        Pacientes that = (Pacientes) o;
        return (
                compare(this.getIdpaciente(), that.getIdpaciente()) &&
                compare(this.getDni(), that.getDni()) &&
                compare(this.getNombre(), that.getNombre()) &&
                compare(this.getSexo(), that.getSexo()) &&
                compare(this.getDomicilio(), that.getDomicilio()) &&
                compare(this.getTelefono(), that.getTelefono()) &&
                compare(this.getCelular(), that.getCelular()) &&
                compare(this.getEmail(), that.getEmail()) &&
                compare(this.getFechanacimiento(), that.getFechanacimiento()) &&
                compare(this.getObservaciones(), that.getObservaciones())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
        result = hash(result, this.getIdpaciente());
        result = hash(result, this.getDni());
        result = hash(result, this.getNombre());
        result = hash(result, this.getSexo());
        result = hash(result, this.getDomicilio());
        result = hash(result, this.getTelefono());
        result = hash(result, this.getCelular());
        result = hash(result, this.getEmail());
        result = hash(result, this.getFechanacimiento());
        result = hash(result, this.getObservaciones());
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
