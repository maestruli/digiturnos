package digiturnos.dao.dto;

import java.util.*;
import java.io.Serializable;

public class TiposUsuario implements Serializable, Cloneable {

    private final static int SEED = 37;

    /** idtipousuario  4  null */
    protected Integer idtipousuario;
    /** tipo  12  null */
    protected String tipo;

    /** Creates a dto for the tipos_usuario table */
    public TiposUsuario() {
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

    /** 
     * Gets the value for tipo  null
     * null
     */
    public String getTipo() {
        return tipo;
    }

    /** 
     * Sets the value for tipo  null
     * null
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public TiposUsuarioPK createPK() {
        return new TiposUsuarioPK(idtipousuario);
    }

    public Object clone() {
        try {
            TiposUsuario o = (TiposUsuario) super.clone();
            o.setIdtipousuario(new Integer(this.getIdtipousuario().intValue()));
            o.setTipo(new String(this.getTipo()));
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
                compare(this.getIdtipousuario(), that.getIdtipousuario()) &&
                compare(this.getTipo(), that.getTipo())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
        result = hash(result, this.getIdtipousuario());
        result = hash(result, this.getTipo());
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
