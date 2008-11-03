package digiturnos.dao.dto;

import java.util.*;
import java.io.Serializable;


/** 
 * Primary key object for usuarios
 */
public class UsuariosPK implements Serializable, Cloneable {

    private final static int SEED = 37;

    protected Integer idusuario;

    public UsuariosPK() {
    }

    public UsuariosPK(Integer idusuario) {
        this.idusuario = idusuario;
    }

    /** 
     * Gets the value for idusuario  null
     * null
     */
    public Integer getIdusuario() {
        return idusuario;
    }

    /** 
     * Sets the value foridusuario  null
     * null
     */
    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Object clone() {
        try {
            Usuarios o = (Usuarios) super.clone();
            o.setIdusuario(new Integer(this.getIdusuario().intValue()));
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
                compare(this.getIdusuario(), that.getIdusuario())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
        result = hash(result, this.getIdusuario());
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
