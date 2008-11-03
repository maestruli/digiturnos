package digiturnos.dao.dto;

import java.util.*;
import java.io.Serializable;


/** 
 * Primary key object for tipos_usuario
 */
public class TiposUsuarioPK implements Serializable, Cloneable {

    private final static int SEED = 37;

    protected Integer idtipousuario;

    public TiposUsuarioPK() {
    }

    public TiposUsuarioPK(Integer idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    /** 
     * Gets the value for idtipousuario  null
     * null
     */
    public Integer getIdtipousuario() {
        return idtipousuario;
    }

    /** 
     * Sets the value foridtipousuario  null
     * null
     */
    public void setIdtipousuario(Integer idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    public Object clone() {
        try {
            TiposUsuario o = (TiposUsuario) super.clone();
            o.setIdtipousuario(new Integer(this.getIdtipousuario().intValue()));
            return o;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof TiposUsuario))
            return false;
        TiposUsuario that = (TiposUsuario) o;
        return (
                compare(this.getIdtipousuario(), that.getIdtipousuario())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
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
