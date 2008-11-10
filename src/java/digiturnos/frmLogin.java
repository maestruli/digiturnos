/*
 * frmLogin.java
 *
 * Created on 28/10/2008, 20:31:48
 */
 
package digiturnos;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.webui.jsf.component.Button;
import com.sun.webui.jsf.component.PasswordField;
import com.sun.webui.jsf.component.TextField;
import digiturnos.dao.dao.PacientesDao;
import digiturnos.dao.dao.ProfesionalesDao;
import digiturnos.dao.dao.UsuariosDao;
import digiturnos.dao.dto.Pacientes;
import digiturnos.dao.dto.Profesionales;
import digiturnos.dao.dto.Usuarios;
import digiturnos.dao.exception.ProfesionalesDaoException;
import digiturnos.dao.exception.UsuariosDaoException;
import digiturnos.dao.exception.PacientesDaoException;
import digiturnos.dao.factory.DaoFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @author Augusto
 */
public class frmLogin extends AbstractPageBean {
    
    private TextField txtUsuario = new TextField();
    private PasswordField txtPassword = new PasswordField ();
    private Button cmdLogin = new Button();
    
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
     * <p>Construct a new Page bean instance.</p>
     */
    public frmLogin() {
    }

    /**
     * <p>Callback method that is called whenever a page is navigated to,
     * either directly via a URL, or indirectly via page navigation.
     * Customize this method to acquire resources that will be needed
     * for event handlers and lifecycle methods, whether or not this
     * page is performing post back processing.</p>
     * 
     * <p>Note that, if the current request is a postback, the property
     * values of the components do <strong>not</strong> represent any
     * values submitted with this request.  Instead, they represent the
     * property values that were saved for this view when it was rendered.</p>
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
            log("frmLogin Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
    }

    /**
     * <p>Callback method that is called after the component tree has been
     * restored, but before any event processing takes place.  This method
     * will <strong>only</strong> be called on a postback request that
     * is processing a form submit.  Customize this method to allocate
     * resources that will be required in your event handlers.</p>
     */
    @Override
    public void preprocess() {
    }

    /**
     * <p>Callback method that is called just before rendering takes place.
     * This method will <strong>only</strong> be called for the page that
     * will actually be rendered (and not, for example, on a page that
     * handled a postback and then navigated to a different page).  Customize
     * this method to allocate resources that will be required for rendering
     * this page.</p>
     */
    @Override
    public void prerender() {
    }

    /**
     * <p>Callback method that is called after rendering is completed for
     * this request, if <code>init()</code> was called (regardless of whether
     * or not this was the page that was actually rendered).  Customize this
     * method to release resources acquired in the <code>init()</code>,
     * <code>preprocess()</code>, or <code>prerender()</code> methods (or
     * acquired during execution of an event handler).</p>
     */
    @Override
    public void destroy() {
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
    }

    public String cmdLogin_action() throws PacientesDaoException, ProfesionalesDaoException {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        
        String usuario = (String) txtUsuario.getText();
        String clave = "MD5('" + txtPassword.getText() + "')";
        String where="password=" + clave + " AND dni=" +  usuario;
        String wherep = "dni = " + usuario;
        
        PacientesDao pacdao = DaoFactory.getDaoFactory().getPacientesDao();
        Pacientes pac[];

        ProfesionalesDao prodao = DaoFactory.getDaoFactory().getProfesionalesDao();
        Profesionales pro[];

        UsuariosDao udao = DaoFactory.getDaoFactory().getUsuariosDao();
        Usuarios resultado[];
        try {
            resultado = udao.findByWhere(where, null);
            if (resultado.length==1) {
                getSessionBean1().setPaciente(resultado[0].getIdtipousuario().intValue()==1);
                getSessionBean1().setEmpleado(resultado[0].getIdtipousuario().intValue()==2);
                getSessionBean1().setProfesional(resultado[0].getIdtipousuario().intValue()==3);
                getSessionBean1().setAdmin(resultado[0].getIdtipousuario().intValue()==4);
                getSessionBean1().setNombre(resultado[0].getNombre());
                if (getSessionBean1().isPaciente()) {
                    pac = pacdao.findByWhere(wherep, null);
                    getSessionBean1().setIdLogueado(pac[0].getIdpaciente());
                }
                if (getSessionBean1().isProfesional()) {
                    pro = prodao.findByWhere(wherep, null);
                    getSessionBean1().setIdLogueado(pro[0].getIdprofesional());
                }
                return "logueoExitoso";
            }
        } catch (UsuariosDaoException ex) {
            Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public TextField getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(TextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public Button getCmdLogin() {
        return cmdLogin;
    }

    public void setCmdLogin(Button cmdLogin) {
        this.cmdLogin = cmdLogin;
    }

    public PasswordField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(PasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }
    
}

