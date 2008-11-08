package digiturnos.dao.dto;

import java.io.Serializable;

public class Especialidades implements Serializable, Cloneable {

    private final static int SEED = 37;

    /** idespecialidad  4  null */
    protected Integer idespecialidad;
    /** especialidad  12  null */
    protected String especialidad;
    /** idservicio  4  Foriegn Key: idservicio  null */
    protected Integer idservicio;

    private String servicio;
    
    /** Creates a dto for the especialidades table */
    public Especialidades() {
    }


    /** 
     * Gets the value for idespecialidad  null
     * null
     */
    public Integer getIdespecialidad() {
        return idespecialidad;
    }

    /** 
     * Sets the value for idespecialidad  null
     * null
     */
    public void setIdespecialidad(Integer idespecialidad) {
        this.idespecialidad = idespecialidad;
    }

    /** 
     * Gets the value for especialidad  null
     * null
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /** 
     * Sets the value for especialidad  null
     * null
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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

    public EspecialidadesPK createPK() {
        return new EspecialidadesPK(idespecialidad);
    }

    public Object clone() {
        try {
            Especialidades o = (Especialidades) super.clone();
            o.setIdespecialidad(new Integer(this.getIdespecialidad().intValue()));
            o.setEspecialidad(new String(this.getEspecialidad()));
            o.setIdservicio(new Integer(this.getIdservicio().intValue()));
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
                compare(this.getIdespecialidad(), that.getIdespecialidad()) &&
                compare(this.getEspecialidad(), that.getEspecialidad()) &&
                compare(this.getIdservicio(), that.getIdservicio())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
        result = hash(result, this.getIdespecialidad());
        result = hash(result, this.getEspecialidad());
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

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
    
    @Override
    public String toString() { 
        return this.getServicio();
    } 
    

}
