/*
 * frmBandaHoraria.java
 *
 * Created on 08/11/2008, 00:19:56
 */
 
package digiturnos;

import com.sun.data.provider.impl.ObjectArrayDataProvider;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.webui.jsf.component.DropDown;
import com.sun.webui.jsf.component.HiddenField;
import com.sun.webui.jsf.component.StaticText;
import com.sun.webui.jsf.component.TableRowGroup;
import com.sun.webui.jsf.component.TextField;
import com.sun.webui.jsf.model.Option;
import digiturnos.dao.dao.HorariosDao;
import digiturnos.dao.dao.ProfesionalesDao;
import digiturnos.dao.dto.Horarios;
import digiturnos.dao.dto.HorariosPK;
import digiturnos.dao.exception.HorariosDaoException;
import digiturnos.dao.exception.ProfesionalesDaoException;
import digiturnos.dao.factory.DaoFactory;
import java.sql.Time;
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
public class frmHorarios extends AbstractPageBean {
    private StaticText txtEtiqueta = new StaticText();
    private DropDown ddDias = new DropDown();
    private TextField txtDesde = new TextField();
    private TextField txtHasta = new TextField();
    private ObjectArrayDataProvider dpHorarios;
    private TableRowGroup rowGroup = new TableRowGroup();
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
    public frmHorarios() {
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
            log("frmBandaHoraria Initialization Failure", e);
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
        
        Option combo[] = new Option[7];
        combo[0] = new Option("Lunes");
        combo[1] = new Option("Martes");
        combo[2] = new Option("Miercoles");
        combo[3] = new Option("Jueves");
        combo[4] = new Option("Viernes");
        combo[5] = new Option("Sabado");
        combo[6] = new Option("Domingo");
        ddDias.setItems(combo);
        
        ProfesionalesDao pdao = DaoFactory.getDaoFactory().getProfesionalesDao();
        try {
            txtEtiqueta.setText("Horarios del Dr. " + pdao.findByPrimaryKey(id).getNombre());
        } catch (ProfesionalesDaoException ex) {
            Logger.getLogger(frmHorarios.class.getName()).log(Level.SEVERE, null, ex);
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

    public String cmdAgregar_action() {
        Horarios horario = new Horarios();
        horario.setIdprofesional((Integer)hdnId.getText());
        horario.setDia((String)ddDias.getValue());
        horario.setDesde(Time.valueOf((String) txtDesde.getText()));
        horario.setHasta(Time.valueOf((String) txtHasta.getText()));
        try {
            DaoFactory.getDaoFactory().getHorariosDao().insert(horario);
        } catch (HorariosDaoException ex) {
            Logger.getLogger(frmHorarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dpHorarios = null;
        
        return "agregar";
    }

    public String lnkCerrarSesion_action() {
        // TODO: Replace with your code
        return "cerrarSesion";
    }

    public String cmdVolver_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        return "volver";
    }

    public StaticText getTxtEtiqueta() {
        return txtEtiqueta;
    }

    public void setTxtEtiqueta(StaticText txtEtiqueta) {
        this.txtEtiqueta = txtEtiqueta;
    }

    public DropDown getDdDias() {
        return ddDias;
    }

    public void setDdDias(DropDown ddDias) {
        this.ddDias = ddDias;
    }

    public TextField getTxtDesde() {
        return txtDesde;
    }

    public void setTxtDesde(TextField txtDesde) {
        this.txtDesde = txtDesde;
    }

    public TextField getTxtHasta() {
        return txtHasta;
    }

    public void setTxtHasta(TextField txtHasta) {
        this.txtHasta = txtHasta;
    }
    public ObjectArrayDataProvider getDpHorarios() {
        if (this.dpHorarios==null) {
            HorariosDao hdao = DaoFactory.getDaoFactory().getHorariosDao();
            hdao.setOrderByColumn(hdao.COLUMN_DIA);
            
            this.dpHorarios = new ObjectArrayDataProvider();
            try {
                this.dpHorarios.setArray((Object[]) hdao.findWhereIdprofesionalEquals((Integer)hdnId.getText()));
            } catch (HorariosDaoException ex) {
                Logger.getLogger(SessionBean1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return dpHorarios;
    }

    public void setDpHorarios(ObjectArrayDataProvider oadp) {
        this.dpHorarios = oadp;
    }

    public TableRowGroup getRowGroup() {
        return rowGroup;
    }

    public void setRowGroup(TableRowGroup rowGroup) {
        this.rowGroup = rowGroup;
    }

    public HiddenField getHdnId() {
        return hdnId;
    }

    public void setHdnId(HiddenField hdnId) {
        this.hdnId = hdnId;
    }

    public String imageHyperlink1_action() {
        Integer id = Integer.valueOf(this.dpHorarios.getValue("idhorario", getRowGroup().getRowKey()).toString());
        
        HorariosDao hdao =   DaoFactory.getDaoFactory().getHorariosDao();
        try {
            hdao.delete(new HorariosPK(id));
        } catch (HorariosDaoException ex) {
            Logger.getLogger(frmServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        this.dpHorarios = null;
        return "null";
    }
}

