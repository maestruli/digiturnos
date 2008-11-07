package digiturnos.dao.dto;

import java.util.*;
import java.io.Serializable;


/** 
 * Primary key object for especialidades
 */
public class EspecialidadesPK implements Serializable, Cloneable {

    private final static int SEED = 37;

    protected Integer idespecialidad;

    public EspecialidadesPK() {
    }

    public EspecialidadesPK(Integer idespecialidad) {
        this.idespecialidad = idespecialidad;
    }

    /** 
     * Gets the value for idespecialidad  null
     * null
     */
    public Integer getIdespecialidad() {
        return idespecialidad;
    }

    /** 
     * Sets the value foridespecialidad  null
     * null
     */
    public void setIdespecialidad(Integer idespecialidad) {
        this.idespecialidad = idespecialidad;
    }

    public Object clone() {
        try {
            Especialidades o = (Especialidades) super.clone();
            o.setIdespecialidad(new Integer(this.getIdespecialidad().intValue()));
            return o;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof Especialidades))
            return false;
        Especialidades that = (Especialidades) o;
        return (
                compare(this.getIdespecialidad(), that.getIdespecialidad())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
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
