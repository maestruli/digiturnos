/*
 * frmEspecialidad.java
 *
 * Created on 08/11/2008, 00:20:11
 */
 
package digiturnos;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.webui.jsf.component.DropDown;
import com.sun.webui.jsf.component.HiddenField;
import com.sun.webui.jsf.component.TextField;
import com.sun.webui.jsf.model.Option;
import digiturnos.dao.dao.EspecialidadesDao;
import digiturnos.dao.dto.Especialidades;
import digiturnos.dao.dto.EspecialidadesPK;
import digiturnos.dao.dto.Servicios;
import digiturnos.dao.exception.EspecialidadesDaoException;
import digiturnos.dao.exception.ServiciosDaoException;
import digiturnos.dao.factory.DaoFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.servlet.http.HttpSession;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @author Augusto
 */
public class frmEspecialidad extends AbstractPageBean {
    private DropDown ddServicio = new DropDown ();
    private TextField txtEspecialidad = new TextField();
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
    public frmEspecialidad() {
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
            log("frmEspecialidad Initialization Failure", e);
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
        Integer id = new Integer(getRequestBean1().getIdEspecialidad());
        
        try {   
            Servicios[] servicios  = DaoFactory.getDaoFactory().getServiciosDao().findAll();
            Option combo[] = new Option[servicios.length];
            
            for (int i = 0; i < servicios.length; i++) {
                combo[i] = new Option(servicios[i].getIdservicio(), servicios[i].getServicio());
            }
            ddServicio.setItems(combo);
            ddServicio.setSelected(combo[0]);
        } catch (ServiciosDaoException ex) {
            Logger.getLogger(frmEspecialidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hdnId.setText(id);
        if(id.intValue()!=0) {
            EspecialidadesDao edao = DaoFactory.getDaoFactory().getEspecialidadesDao();
            try {
                Especialidades e = edao.findByPrimaryKey(id);
                txtEspecialidad.setText(e.getEspecialidad());
                
                Option[] servicios = (Option[]) ddServicio.getItems();
                for(int i=0; i<servicios.length; i++) {
                    if (((Integer)servicios[i].getValue()).intValue() == e.getIdservicio().intValue()){
                        ddServicio.setSelected(servicios[i].getValue());
                        break;
                    }
                }
            } catch (EspecialidadesDaoException ex) {
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

    public String lnkCerrarSesion_action() {
        HttpSession sesion = (HttpSession) getExternalContext().getSession(true);
        sesion.invalidate();
        return "cerrarSesion";
    }

    public String cmdAceptar_action() {
        Integer  id = (Integer)hdnId.getText();
        
         EspecialidadesDao sdao =  DaoFactory.getDaoFactory().getEspecialidadesDao();
         Especialidades especialidad = new Especialidades();
         especialidad.setEspecialidad(txtEspecialidad.getText().toString());
         especialidad.setIdservicio(new Integer((String) ddServicio.getValue()));
         
         try {
             if (id.intValue()!=0) {
                 especialidad.setIdespecialidad(id);
                sdao.update(new EspecialidadesPK(id), especialidad);
             }
             else {
                 sdao.insert(especialidad);
             }
        } catch (EspecialidadesDaoException ex) {
            Logger.getLogger(frmServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return "Aceptar";
    }

    public String cmdCancelar_action() {
        // TODO: Replace with your code
        return "Cancelar";
    }

    public DropDown getDdServicio() {
        return ddServicio;
    }

    public void setDdServicio(DropDown ddServicio) {
        this.ddServicio = ddServicio;
    }

    public TextField getTxtEspecialidad() {
        return txtEspecialidad;
    }

    public void setTxtEspecialidad(TextField txtEspecialidad) {
        this.txtEspecialidad = txtEspecialidad;
    }

    public HiddenField getHdnId() {
        return hdnId;
    }

    public void setHdnId(HiddenField hdnId) {
        this.hdnId = hdnId;
    }

    
}

