/*
 * frmProfesional.java
 *
 * Created on 08/11/2008, 00:20:49
 */
 
package digiturnos;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.webui.jsf.component.Calendar;
import com.sun.webui.jsf.component.DropDown;
import com.sun.webui.jsf.component.HiddenField;
import com.sun.webui.jsf.component.TextField;
import com.sun.webui.jsf.model.Option;
import digiturnos.dao.dao.ProfesionalesDao;
import digiturnos.dao.dto.Especialidades;
import digiturnos.dao.dto.Profesionales;
import digiturnos.dao.dto.ProfesionalesPK;
import digiturnos.dao.exception.EspecialidadesDaoException;
import digiturnos.dao.exception.ProfesionalesDaoException;
import digiturnos.dao.factory.DaoFactory;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class frmProfesional extends AbstractPageBean {
    private TextField txtDNI = new TextField();
    private TextField txtNombre = new TextField();
    private TextField txtDomicilio = new TextField();
    private TextField txtTelefono = new TextField();
    private TextField txtCelular = new TextField();
    private TextField txtEmail = new TextField();
    private DropDown ddEspecialidad = new DropDown();
    private Calendar calFecha = new Calendar();
    private HiddenField hdnId = new HiddenField();
    
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
    public frmProfesional() {
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
            log("frmProfesional Initialization Failure", e);
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
        Integer id = new Integer(getRequestBean1().getIdProfesional());
        getHdnId().setText(id);
        this.llenarEspecialidades();
        
        if(id.intValue()!=0) {
             ProfesionalesDao pdao = DaoFactory.getDaoFactory().getProfesionalesDao();
            try {
                Profesionales e = pdao.findByPrimaryKey(id);
                txtDNI.setText(e.getDni().toString());
                txtNombre.setText(e.getNombre());
                txtCelular.setText(e.getCelular());
                txtDomicilio.setText(e.getDomicilio());
                txtEmail.setText(e.getEmail());
                txtTelefono.setText(e.getTelefono());
                calFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(e.getFechanacimiento()));
                
                Option[] especialidades = (Option[]) ddEspecialidad.getItems();
                for(int i=0; i<especialidades.length; i++) {
                    if (((Integer)especialidades[i].getValue()).intValue() == e.getIdespecialidad().intValue()){
                        ddEspecialidad.setSelected(especialidades[i].getValue());
                        break;
                    }
                }
            } catch (ProfesionalesDaoException ex) {
                Logger.getLogger(frmServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
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
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
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

    public TextField getTxtDNI() {
        return txtDNI;
    }

    public void setTxtDNI(TextField txtDNI) {
        this.txtDNI = txtDNI;
    }

    public TextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(TextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public TextField getTxtDomicilio() {
        return txtDomicilio;
    }

    public void setTxtDomicilio(TextField txtDomicilio) {
        this.txtDomicilio = txtDomicilio;
    }

    public TextField getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(TextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public TextField getTxtCelular() {
        return txtCelular;
    }

    public void setTxtCelular(TextField txtCelular) {
        this.txtCelular = txtCelular;
    }

    public TextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(TextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public DropDown getDdEspecialidad() {
        return ddEspecialidad;
    }

    public void setDdEspecialidad(DropDown ddEspecialidad) {
        this.ddEspecialidad = ddEspecialidad;
    }

    public Calendar getCalFecha() {
        return calFecha;
    }

    public void setCalFecha(Calendar calFecha) {
        this.calFecha = calFecha;
    }

    public String lnkCerrarSesion_action() {
        // TODO: Replace with your code
        return "case1";
    }

    public String cmdCancelar_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        return "cancelar";
    }

    public String cmdAceptar_action() {
        Integer  id = (Integer)hdnId.getText();
        
         ProfesionalesDao pdao =  DaoFactory.getDaoFactory().getProfesionalesDao();
         Profesionales profesional = new Profesionales();
         
         profesional.setCelular((String) txtCelular.getText());
         profesional.setDni( new Integer((String)txtDNI.getText()));
         profesional.setDomicilio((String) txtDomicilio.getText());
         profesional.setEmail((String) txtEmail.getText());
         profesional.setFechanacimiento( Date.valueOf( new SimpleDateFormat("yyyy-MM-dd").format(calFecha.getText()) ) );
         profesional.setIdespecialidad(new Integer(ddEspecialidad.getValue().toString()));
         profesional.setNombre((String) txtNombre.getText());
         profesional.setTelefono((String) txtTelefono.getText());
         
         try {
             if (id.intValue()!=0) {
                profesional.setIdprofesional(id);
                pdao.update(new ProfesionalesPK(id), profesional);
             }
             else {
                 pdao.insert(profesional);
             }
        } catch ( ProfesionalesDaoException ex) {
            Logger.getLogger(frmServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return "aceptar";
    }

    private void llenarEspecialidades() {
        try {   
            Especialidades[] especialidades  = DaoFactory.getDaoFactory().getEspecialidadesDao().findAll();
            Option combo[] = new Option[especialidades.length];
            
            for (int i = 0; i < especialidades.length; i++) {
                combo[i] = new Option(especialidades[i].getIdespecialidad(), especialidades[i].getEspecialidad());
            }
            ddEspecialidad.setItems(combo);
            ddEspecialidad.setSelected(combo[0]);
        } catch (EspecialidadesDaoException ex) {
            Logger.getLogger(frmEspecialidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HiddenField getHdnId() {
        return hdnId;
    }

    public void setHdnId(HiddenField hdnId) {
        this.hdnId = hdnId;
    }
    
}

