package digiturnos.dao.dto;

import java.util.*;
import java.io.Serializable;


/** 
 * Primary key object for servicios
 */
public class ServiciosPK implements Serializable, Cloneable {

    private final static int SEED = 37;

    protected Integer idservicio;

    public ServiciosPK() {
    }

    public ServiciosPK(Integer idservicio) {
        this.idservicio = idservicio;
    }

    /** 
     * Gets the value for idservicio  null
     * null
     */
    public Integer getIdservicio() {
        return idservicio;
    }

    /** 
     * Sets the value foridservicio  null
     * null
     */
    public void setIdservicio(Integer idservicio) {
        this.idservicio = idservicio;
    }

    public Object clone() {
        try {
            Servicios o = (Servicios) super.clone();
            o.setIdservicio(new Integer(this.getIdservicio().intValue()));
            return o;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof Servicios))
            return false;
        Servicios that = (Servicios) o;
        return (
                compare(this.getIdservicio(), that.getIdservicio())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
        result = hash(result, this.getIdservicio());
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
