package digiturnos.dao.dto;

import java.io.Serializable;

public class Horarios implements Serializable, Cloneable {

    private final static int SEED = 37;

    /** idhorario  4  null */
    protected Integer idhorario;
    /** idprofesional  4  Foriegn Key: idprofesional  null */
    protected Integer idprofesional;
    /** dia  12  null */
    protected String dia;
    /** desde  92  null */
    protected java.sql.Time desde;
    /** hasta  92  null */
    protected java.sql.Time hasta;

    /** Creates a dto for the horarios table */
    public Horarios() {
    }


    /** 
     * Gets the value for idhorario  null
     * null
     */
    public Integer getIdhorario() {
        return idhorario;
    }

    /** 
     * Sets the value for idhorario  null
     * null
     */
    public void setIdhorario(Integer idhorario) {
        this.idhorario = idhorario;
    }

    /** 
     * Gets the value for idprofesional  null
     * null
     */
    public Integer getIdprofesional() {
        return idprofesional;
    }

    /** 
     * Sets the value for idprofesional  null
     * null
     */
    public void setIdprofesional(Integer idprofesional) {
        this.idprofesional = idprofesional;
    }

    /** 
     * Gets the value for dia  null
     * null
     */
    public String getDia() {
        return dia;
    }

    /** 
     * Sets the value for dia  null
     * null
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    /** 
     * Gets the value for desde  null
     * null
     */
    public java.sql.Time getDesde() {
        return desde;
    }

    /** 
     * Sets the value for desde  null
     * null
     */
    public void setDesde(java.sql.Time desde) {
        this.desde = desde;
    }

    /** 
     * Gets the value for hasta  null
     * null
     */
    public java.sql.Time getHasta() {
        return hasta;
    }

    /** 
     * Sets the value for hasta  null
     * null
     */
    public void setHasta(java.sql.Time hasta) {
        this.hasta = hasta;
    }

    public HorariosPK createPK() {
        return new HorariosPK(idhorario);
    }

    public Object clone() {
        try {
            Horarios o = (Horarios) super.clone();
            o.setIdhorario(new Integer(this.getIdhorario().intValue()));
            o.setIdprofesional(new Integer(this.getIdprofesional().intValue()));
            o.setDia(new String(this.getDia()));
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
                compare(this.getIdhorario(), that.getIdhorario()) &&
                compare(this.getIdprofesional(), that.getIdprofesional()) &&
                compare(this.getDia(), that.getDia()) &&
                compare(this.getDesde(), that.getDesde()) &&
                compare(this.getHasta(), that.getHasta())
                );
    }

    private boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    public int hashCode() {
        int result = SEED;
        result = hash(result, this.getIdhorario());
        result = hash(result, this.getIdprofesional());
        result = hash(result, this.getDia());
        result = hash(result, this.getDesde());
        result = hash(result, this.getHasta());
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
