package digiturnos.dao.dto;

import java.util.*;
import java.io.Serializable;


/** 
 * Primary key object for turnos
 */
public class TurnosPK implements Serializable, Cloneable {

    private final static int SEED = 37;

    protected Integer idturno;

    public TurnosPK() {
    }

    public TurnosPK(Integer idturno) {
        this.idturno = idturno;
    }

    /** 
     * Gets the value for idturno  null
     * null
     */
    public Integer getIdturno() {
        return idturno;
    }

    /** 
     * Sets the value foridturno  null
     * null
     */
    public void setIdturno(Integer idturno) {
        this.idturno = idturno;
    }

    public Object clone() {
        try {
            Turnos o = (Turnos) super.clone();
            o.setIdturno(new Integer(this.getIdturno().intValue()));
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
                compare(this.getIdturno(), that.getIdturno())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
        result = hash(result, this.getIdturno());
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
