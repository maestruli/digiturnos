/*
 * frmPaciente.java
 *
 * Created on 02/11/2008, 17:08:29
 */
package digiturnos;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.webui.jsf.component.Calendar;
import com.sun.webui.jsf.component.DropDown;
import com.sun.webui.jsf.component.HiddenField;
import com.sun.webui.jsf.component.TextArea;
import com.sun.webui.jsf.component.TextField;
import com.sun.webui.jsf.model.SingleSelectOptionsList;
import digiturnos.dao.dao.PacientesDao;
import digiturnos.dao.dto.Pacientes;
import digiturnos.dao.dto.PacientesPK;
import digiturnos.dao.exception.PacientesDaoException;
import digiturnos.dao.factory.DaoFactory;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class frmPaciente extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        ddSexoDefaultOptions.setOptions(new com.sun.webui.jsf.model.Option[]{new com.sun.webui.jsf.model.Option("M", "M"), new com.sun.webui.jsf.model.Option("F", "F")});
    }
    private HiddenField hdnId = new HiddenField();

    public HiddenField getHdnId() {
        return hdnId;
    }

    public void setHdnId(HiddenField hf) {
        this.hdnId = hf;
    }
    private TextField txtFichaMedica = new TextField();

    public TextField getTxtFichaMedica() {
        return txtFichaMedica;
    }

    public void setTxtFichaMedica(TextField tf) {
        this.txtFichaMedica = tf;
    }
    private TextField txtDNI = new TextField();

    public TextField getTxtDNI() {
        return txtDNI;
    }

    public void setTxtDNI(TextField tf) {
        this.txtDNI = tf;
    }
    private TextField txtNombre = new TextField();

    public TextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(TextField tf) {
        this.txtNombre = tf;
    }
    private TextField txtDomicilio = new TextField();

    public TextField getTxtDomicilio() {
        return txtDomicilio;
    }

    public void setTxtDomicilio(TextField tf) {
        this.txtDomicilio = tf;
    }
    private TextField txtTelefono = new TextField();

    public TextField getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(TextField tf) {
        this.txtTelefono = tf;
    }
    private TextField txtCelular = new TextField();

    public TextField getTxtCelular() {
        return txtCelular;
    }

    public void setTxtCelular(TextField tf) {
        this.txtCelular = tf;
    }
    private TextField txtEmail = new TextField();

    public TextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(TextField tf) {
        this.txtEmail = tf;
    }
    private SingleSelectOptionsList ddSexoDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdSexoDefaultOptions() {
        return ddSexoDefaultOptions;
    }

    public void setDdSexoDefaultOptions(SingleSelectOptionsList ssol) {
        this.ddSexoDefaultOptions = ssol;
    }
    private DropDown ddSexo = new DropDown();

    public DropDown getDdSexo() {
        return ddSexo;
    }

    public void setDdSexo(DropDown dd) {
        this.ddSexo = dd;
    }
    private TextArea txtObservaciones = new TextArea();

    public TextArea getTxtObservaciones() {
        return txtObservaciones;
    }

    public void setTxtObservaciones(TextArea ta) {
        this.txtObservaciones = ta;
    }
    private Calendar cldFechaNacimiento = new Calendar();

    public Calendar getCldFechaNacimiento() {
        return cldFechaNacimiento;
    }

    public void setCldFechaNacimiento(Calendar c) {
        this.cldFechaNacimiento = c;
    }

    // </editor-fold>
    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public frmPaciente() {
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
            log("frmPaciente Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

    // </editor-fold>
    // Perform application initialization that must complete
    // *after* managed components are initialized
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
        Integer id = new Integer(getRequestBean1().getIdPaciente());
        hdnId.setText(id);
        if (id != 0) {

            PacientesDao pdao = DaoFactory.getDaoFactory().getPacientesDao();
            try {
                Pacientes p = pdao.findByPrimaryKey(new Integer(id));
                txtFichaMedica.setText(p.getIdpaciente());
                txtDNI.setText(p.getDni());
                txtNombre.setText(p.getNombre());
                ddSexo.setSelected(p.getSexo());
                cldFechaNacimiento.setText(new SimpleDateFormat("dd/MM/yyyy").format(p.getFechanacimiento()));
                txtDomicilio.setText(p.getDomicilio());
                txtTelefono.setText(p.getTelefono());
                txtCelular.setText(p.getCelular());
                txtEmail.setText(p.getEmail());
                txtObservaciones.setText(p.getObservaciones());
            } catch (PacientesDaoException ex) {
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

    public String lnkCerrarSesion_action() {
        HttpSession sesion = (HttpSession) getExternalContext().getSession(true);
        sesion.invalidate();
        return "cerrarSesion";
    }

    public String cmdCancelar_action() {
        return "Cancelar";
    }

    public String cmdAceptar_action() {
        Integer id = (Integer) hdnId.getText();
        //Integer fichaCargada = Integer.valueOf(txtFichaMedica.getText().toString()).intValue();

        PacientesDao pdao = DaoFactory.getDaoFactory().getPacientesDao();
        Pacientes paciente = new Pacientes();

        paciente.setIdpaciente(new Integer((String)txtFichaMedica.getText().toString()));
        paciente.setDni(new Integer((String)txtDNI.getText().toString()));
        paciente.setNombre(txtNombre.getText().toString());
        paciente.setSexo(ddSexo.getSelected().toString());
        paciente.setFechanacimiento( Date.valueOf( new SimpleDateFormat("yyyy-MM-dd").format(cldFechaNacimiento.getText()) ) );
        paciente.setDomicilio(txtDomicilio.getText().toString());
        paciente.setTelefono(txtTelefono.getText().toString());
        paciente.setCelular(txtCelular.getText().toString());
        paciente.setEmail(txtEmail.getText().toString());
        paciente.setObservaciones(txtObservaciones.getText().toString());
        try {
            if (id.intValue() != 0) {
                //paciente.setIdpaciente(id);
                pdao.update(new PacientesPK(id), paciente);
            } else {
                pdao.insert(paciente, paciente.getIdpaciente());
            }
        } catch (PacientesDaoException ex) {
            Logger.getLogger(frmServicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Aceptar";
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
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
    }
}