/*
 * frmVerAgenda.java
 *
 * Created on 07/11/2008, 23:53:34
 */
 
package digiturnos;

import com.sun.data.provider.impl.ObjectArrayDataProvider;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.webui.jsf.component.Calendar;
import com.sun.webui.jsf.component.TableRowGroup;
import digiturnos.dao.dao.TurnosDao;
import digiturnos.dao.exception.TurnosDaoException;
import digiturnos.dao.factory.DaoFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class frmVerAgenda extends AbstractPageBean {
    private ObjectArrayDataProvider dpTurnos;
    private TableRowGroup rowGroup = new TableRowGroup();
    
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    private Calendar calFecha = new Calendar();

    public Calendar getCalFecha() {
        return calFecha;
    }

    public void setCalFecha(Calendar c) {
        this.calFecha = c;
    }

    // </editor-fold>

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public frmVerAgenda() {
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
            log("frmVerAgenda Initialization Failure", e);
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
        if (getRequestBean1().getFechaAgenda() == null)
            calFecha.setSelectedDate(new Date());
        else
            calFecha.setSelectedDate(getRequestBean1().getFechaAgenda());
        getRequestBean1().setFechaAgenda(null);
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

    public ObjectArrayDataProvider getDpTurnos() throws ParseException {
        
        TurnosDao tdao = DaoFactory.getDaoFactory().getTurnosDao();

        String fechaWhere = new SimpleDateFormat("yyyy-MM-dd").format(calFecha.getSelectedDate());
        
        String where = "idprofesional = " + getSessionBean1().getIdLogueado().toString() +
                " AND fecha = '" + fechaWhere + "'";

        if (this.dpTurnos==null) {
            
            tdao.setOrderByColumn(tdao.COLUMN_HORA);
            
            this.dpTurnos = new ObjectArrayDataProvider();
            try {
                this.dpTurnos.setArray((Object[]) tdao.findByWhere(where, null));
            } catch (TurnosDaoException ex) {
                Logger.getLogger(SessionBean1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return dpTurnos;
    }

    public void setDpTurnos(ObjectArrayDataProvider dpTurnos) {
        this.dpTurnos = dpTurnos;
    }

    public TableRowGroup getRowGroup() {
        return rowGroup;
    }

    public void setRowGroup(TableRowGroup rowGroup) {
        this.rowGroup = rowGroup;
    }

    public String cmdAceptar_action() {
        setDpTurnos(null);
        getRequestBean1().setFechaAgenda(calFecha.getSelectedDate());
        return "filtrarAgenda";
    }
    
}

