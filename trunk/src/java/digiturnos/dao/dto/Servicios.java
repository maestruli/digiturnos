package digiturnos.dao.dto;

import java.util.*;
import java.io.Serializable;

public class Servicios implements Serializable, Cloneable {

    private final static int SEED = 37;

    /** idservicio  4  null */
    protected Integer idservicio;
    /** servicio  12  null */
    protected String servicio;

    /** Creates a dto for the servicios table */
    public Servicios() {
    }


    /** 
     * Gets the value for idservicio  null
     * null
     */
    public Integer getIdservicio() {
        return idservicio;
    }

    /** 
     * Sets the value for idservicio  null
     * null
     */
    public void setIdservicio(Integer idservicio) {
        this.idservicio = idservicio;
    }

    /** 
     * Gets the value for servicio  null
     * null
     */
    public String getServicio() {
        return servicio;
    }

    /** 
     * Sets the value for servicio  null
     * null
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public ServiciosPK createPK() {
        return new ServiciosPK(idservicio);
    }

    public Object clone() {
        try {
            Servicios o = (Servicios) super.clone();
            o.setIdservicio(new Integer(this.getIdservicio().intValue()));
            o.setServicio(new String(this.getServicio()));
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
                compare(this.getIdservicio(), that.getIdservicio()) &&
                compare(this.getServicio(), that.getServicio())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
        result = hash(result, this.getIdservicio());
        result = hash(result, this.getServicio());
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
