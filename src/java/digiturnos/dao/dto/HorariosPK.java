package digiturnos.dao.dto;

import java.util.*;
import java.io.Serializable;


/** 
 * Primary key object for horarios
 */
public class HorariosPK implements Serializable, Cloneable {

    private final static int SEED = 37;

    protected Integer idhorario;

    public HorariosPK() {
    }

    public HorariosPK(Integer idhorario) {
        this.idhorario = idhorario;
    }

    /** 
     * Gets the value for idhorario  null
     * null
     */
    public Integer getIdhorario() {
        return idhorario;
    }

    /** 
     * Sets the value foridhorario  null
     * null
     */
    public void setIdhorario(Integer idhorario) {
        this.idhorario = idhorario;
    }

    public Object clone() {
        try {
            Horarios o = (Horarios) super.clone();
            o.setIdhorario(new Integer(this.getIdhorario().intValue()));
            return o;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof Horarios))
            return false;
        Horarios that = (Horarios) o;
        return (
                compare(this.getIdhorario(), that.getIdhorario())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
        result = hash(result, this.getIdhorario());
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
