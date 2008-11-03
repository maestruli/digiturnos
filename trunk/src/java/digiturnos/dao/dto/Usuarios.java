package digiturnos.dao.dto;

import java.util.*;
import java.io.Serializable;

public class Usuarios implements Serializable, Cloneable {

    private final static int SEED = 37;

    /** idusuario  4  null */
    protected Integer idusuario;
    /** dni  4  null */
    protected Integer dni;
    /** nombre  12  null */
    protected String nombre;
    /** email  12  null */
    protected String email;
    /** password  1  null */
    protected String password;
    /** idtipousuario  4  Foriegn Key: idtipousuario  null */
    protected Integer idtipousuario;

    /** Creates a dto for the usuarios table */
    public Usuarios() {
    }


    /** 
     * Gets the value for idusuario  null
     * null
     */
    public Integer getIdusuario() {
        return idusuario;
    }

    /** 
     * Sets the value for idusuario  null
     * null
     */
    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
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
     * Gets the value for password  null
     * null
     */
    public String getPassword() {
        return password;
    }

    /** 
     * Sets the value for password  null
     * null
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** 
     * Gets the value for idtipousuario  null
     * null
     */
    public Integer getIdtipousuario() {
        return idtipousuario;
    }

    /** 
     * Sets the value for idtipousuario  null
     * null
     */
    public void setIdtipousuario(Integer idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    public UsuariosPK createPK() {
        return new UsuariosPK(idusuario);
    }

    public Object clone() {
        try {
            Usuarios o = (Usuarios) super.clone();
            o.setIdusuario(new Integer(this.getIdusuario().intValue()));
            o.setDni(new Integer(this.getDni().intValue()));
            o.setNombre(new String(this.getNombre()));
            o.setEmail(new String(this.getEmail()));
            o.setPassword(new String(this.getPassword()));
            o.setIdtipousuario(new Integer(this.getIdtipousuario().intValue()));
            return o;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof Usuarios))
            return false;
        Usuarios that = (Usuarios) o;
        return (
                compare(this.getIdusuario(), that.getIdusuario()) &&
                compare(this.getDni(), that.getDni()) &&
                compare(this.getNombre(), that.getNombre()) &&
                compare(this.getEmail(), that.getEmail()) &&
                compare(this.getPassword(), that.getPassword()) &&
                compare(this.getIdtipousuario(), that.getIdtipousuario())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
        result = hash(result, this.getIdusuario());
        result = hash(result, this.getDni());
        result = hash(result, this.getNombre());
        result = hash(result, this.getEmail());
        result = hash(result, this.getPassword());
        result = hash(result, this.getIdtipousuario());
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
