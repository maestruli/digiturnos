package digiturnos.dao.dto;

import java.util.*;
import java.io.Serializable;


/** 
 * Primary key object for profesionales
 */
public class ProfesionalesPK implements Serializable, Cloneable {

    private final static int SEED = 37;

    protected Integer idprofesional;

    public ProfesionalesPK() {
    }

    public ProfesionalesPK(Integer idprofesional) {
        this.idprofesional = idprofesional;
    }

    /** 
     * Gets the value for idprofesional  null
     * null
     */
    public Integer getIdprofesional() {
        return idprofesional;
    }

    /** 
     * Sets the value foridprofesional  null
     * null
     */
    public void setIdprofesional(Integer idprofesional) {
        this.idprofesional = idprofesional;
    }

    public Object clone() {
        try {
            Profesionales o = (Profesionales) super.clone();
            o.setIdprofesional(new Integer(this.getIdprofesional().intValue()));
            return o;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof Profesionales))
            return false;
        Profesionales that = (Profesionales) o;
        return (
                compare(this.getIdprofesional(), that.getIdprofesional())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
        result = hash(result, this.getIdprofesional());
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
