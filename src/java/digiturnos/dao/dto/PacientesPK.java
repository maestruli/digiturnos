package digiturnos.dao.dto;

import java.util.*;
import java.io.Serializable;


/** 
 * Primary key object for pacientes
 */
public class PacientesPK implements Serializable, Cloneable {

    private final static int SEED = 37;

    protected Integer idpaciente;

    public PacientesPK() {
    }

    public PacientesPK(Integer idpaciente) {
        this.idpaciente = idpaciente;
    }

    /** 
     * Gets the value for idpaciente  null
     * null
     */
    public Integer getIdpaciente() {
        return idpaciente;
    }

    /** 
     * Sets the value foridpaciente  null
     * null
     */
    public void setIdpaciente(Integer idpaciente) {
        this.idpaciente = idpaciente;
    }

    public Object clone() {
        try {
            Pacientes o = (Pacientes) super.clone();
            o.setIdpaciente(new Integer(this.getIdpaciente().intValue()));
            return o;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof Pacientes))
            return false;
        Pacientes that = (Pacientes) o;
        return (
                compare(this.getIdpaciente(), that.getIdpaciente())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
        result = hash(result, this.getIdpaciente());
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
