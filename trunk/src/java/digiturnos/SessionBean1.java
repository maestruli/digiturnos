/*
 * SessionBean1.java
 *
 * Created on 04/09/2008, 01:06:54
 */
 
package digiturnos;

import com.sun.data.provider.impl.ObjectArrayDataProvider;
import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import digiturnos.dao.dao.EspecialidadesDao;
import digiturnos.dao.exception.EspecialidadesDaoException;
import digiturnos.dao.factory.DaoFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;

/**
 * <p>Session scope data bean for your application.  Create properties
 *  here to represent cached data that should be made available across
 *  multiple HTTP requests for an individual user.</p>
 *
 * <p>An instance of this class will be created for you automatically,
 * the first time your application evaluates a value binding expression
 * or method binding expression that references a managed bean using
 * this class.</p>
 *
 * @author Leandro
 */
public class SessionBean1 extends AbstractSessionBean {
    private String nombre;
    private boolean profesional;
    private boolean paciente;
    private boolean empleado;
    private boolean admin;
    private Integer idPaciente;
    
    private ObjectArrayDataProvider dpServicios;
    private ObjectArrayDataProvider dpEspecialidades;
    private ObjectArrayDataProvider dpPacientes;
    private ObjectArrayDataProvider dpProfesionales;
    private ObjectArrayDataProvider dpUsuarios;
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    /**
     * <p>Construct a new session data bean instance.</p>
     */
    public SessionBean1() {
    }

    /**
     * <p>This method is called when this bean is initially added to
     * session scope.  Typically, this occurs as a result of evaluating
     * a value binding or method binding expression, which utilizes the
     * managed bean facility to instantiate this bean and store it into
     * session scope.</p>
     * 
     * <p>You may customize this method to initialize and cache data values
     * or resources that are required for the lifetime of a particular
     * user session.</p>
     */
    @Override
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here
        
        // <editor-fold defaultstate="collapsed" desc="Managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("SessionBean1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
    }

    /**
     * <p>This method is called when the session containing it is about to be
     * passivated.  Typically, this occurs in a distributed servlet container
     * when the session is about to be transferred to a different
     * container instance, after which the <code>activate()</code> method
     * will be called to indicate that the transfer is complete.</p>
     * 
     * <p>You may customize this method to release references to session data
     * or resources that can not be serialized with the session itself.</p>
     */
    @Override
    public void passivate() {
    }

    /**
     * <p>This method is called when the session containing it was
     * reactivated.</p>
     * 
     * <p>You may customize this method to reacquire references to session
     * data or resources that could not be serialized with the
     * session itself.</p>
     */
    @Override
    public void activate() {
    }

    /**
     * <p>This method is called when this bean is removed from
     * session scope.  Typically, this occurs as a result of
     * the session timing out or being terminated by the application.</p>
     * 
     * <p>You may customize this method to clean up resources allocated
     * during the execution of the <code>init()</code> method, or
     * at any later time during the lifetime of the application.</p>
     */
    @Override
    public void destroy() {
    }
    
    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isProfesional() {
        return profesional;
    }

    public void setProfesional(boolean profesional) {
        this.profesional = profesional;
    }

    public boolean isPaciente() {
        return paciente;
    }

    public void setPaciente(boolean paciente) {
        this.paciente = paciente;
    }

    public boolean isEmpleado() {
        return empleado;
    }

    public void setEmpleado(boolean empleado) {
        this.empleado = empleado;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public ObjectArrayDataProvider getDpServicios() {
        if (this.dpServicios==null) {
            DaoFactory df  = DaoFactory.getDaoFactory();
            EspecialidadesDao edao = df.getEspecialidadesDao();
            edao.setOrderByColumn(edao.COLUMN_ESPECIALIDAD);
            
            this.dpServicios = new ObjectArrayDataProvider();
            try {
                this.dpServicios.setArray((Object[]) edao.findAll());
            } catch (EspecialidadesDaoException ex) {
                Logger.getLogger(SessionBean1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dpServicios;
    }

    public void setDpServicios(ObjectArrayDataProvider dpServicios) {
        this.dpServicios = dpServicios;
    }

    public ObjectArrayDataProvider getDpEspecialidades() {
        return dpEspecialidades;
    }

    public void setDpEspecialidades(ObjectArrayDataProvider dpEspecialidades) {
        this.dpEspecialidades = dpEspecialidades;
    }

    public ObjectArrayDataProvider getDpPacientes() {
        return dpPacientes;
    }

    public void setDpPacientes(ObjectArrayDataProvider dpPacientes) {
        this.dpPacientes = dpPacientes;
    }

    public ObjectArrayDataProvider getDpUsuarios() {
        return dpUsuarios;
    }

    public void setDpUsuarios(ObjectArrayDataProvider dpUsuarios) {
        this.dpUsuarios = dpUsuarios;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }


}
