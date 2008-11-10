/*
 * frmOtorgarTurnos.java
 *
 * Created on 07/11/2008, 23:52:29
 */
 
package digiturnos;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.webui.jsf.component.Button;
import com.sun.webui.jsf.component.DropDown;
import com.sun.webui.jsf.component.StaticText;
import com.sun.webui.jsf.model.Option;
import digiturnos.dao.dao.TurnosDao;
import digiturnos.dao.dto.Especialidades;
import digiturnos.dao.dto.Horarios;
import digiturnos.dao.dto.Pacientes;
import digiturnos.dao.dto.Profesionales;
import digiturnos.dao.dto.Servicios;
import digiturnos.dao.dto.Turnos;
import digiturnos.dao.exception.EspecialidadesDaoException;
import digiturnos.dao.exception.HorariosDaoException;
import digiturnos.dao.exception.PacientesDaoException;
import digiturnos.dao.exception.ProfesionalesDaoException;
import digiturnos.dao.exception.ServiciosDaoException;
import digiturnos.dao.factory.DaoFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.event.ValueChangeEvent;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @author Augusto
 */
public class frmOtorgarTurnos extends AbstractPageBean {
    private DropDown ddPaciente = new DropDown();
    private DropDown ddServicio = new DropDown();
    private DropDown ddEspecialidad = new DropDown();
    private DropDown ddProfesional = new DropDown();
    private DropDown ddHorario = new DropDown();
    private DropDown ddFecha = new DropDown();
    private DropDown ddTurno = new DropDown();
    private Button cmdConfirmarPaciente = new Button();
    private Button cmdConfirmarProfesional = new Button();
    private Button cmdConfirmarTurno = new Button();
    private StaticText txtMensaje = new StaticText();
    
    private Option[] servicios;
    private Option[] pacientes;
    private Option[] especialidades;
    private Option[] profesionales;
    private Option[] horarios;
    private Option[] fechas;
    private Option[] turnos;
    
    
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
    public frmOtorgarTurnos() {
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
            log("frmOtorgarTurnos Initialization Failure", e);
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
    
    public DropDown getDdPaciente() {
        return ddPaciente;
    }

    public void setDdPaciente(DropDown ddPaciente) {
        this.ddPaciente = ddPaciente;
    }

    public DropDown getDdHorario() {
        return ddHorario;
    }
    public DropDown getDdFecha() {
        return ddFecha;
    }
    public DropDown getDdTurno() {
        return ddTurno;
    }
    
    public DropDown getDdServicio() {
        return ddServicio;
    }

    public void setDdServicio(DropDown ddServicio) {
        this.ddServicio = ddServicio;
    }

    public DropDown getDdEspecialidad() {
        return ddEspecialidad;
    }

    public void setDdEspecialidad(DropDown ddEspecialidad) {
        this.ddEspecialidad = ddEspecialidad;
    }

    public DropDown getDdProfesional() {
        return ddProfesional;
    }

    public void setDdProfesional(DropDown ddProfesional) {
        this.ddProfesional = ddProfesional;
    }
    
    public void setServicios(Option[] servicios) {
        this.servicios = servicios;
    }
    
    public void setPacientes(Option[] pacientes) {
        this.pacientes = pacientes;
    }
    
    public void setEspecialidades(Option[] especialidades) {
        this.especialidades = especialidades;
    }
    
    public void setHorarios(Option[] horarios) {
        this.horarios = horarios;
    }
    
    public void setProfesionales(Option[] profesionales) {
        this.profesionales = profesionales;
    }
    
    public void setDdHorario(DropDown ddHorario) {
        this.ddHorario = ddHorario;
    }
    
    public void setDdFecha(DropDown ddFecha) {
        this.ddFecha = ddFecha;
    }
    
    public void setDdTurno(DropDown ddTurno) {
        this.ddTurno = ddTurno;
    }
    
    public void setFechas(Option[] fechas) {
        this.fechas = fechas;
    }
    
    public void setTurnos(Option[] turnos) {
        this.turnos = turnos;
    }
    
    public Option[] getPacientes() {
        
        if (this.pacientes==null) {
            try {
                Pacientes[] p = DaoFactory.getDaoFactory().getPacientesDao().findAll();
                this.pacientes = new Option[p.length];
                for (int i = 0; i < p.length; i++) {
                    this.pacientes[i] = new Option(p[i].getIdpaciente(), p[i].getNombre());
                }
            } catch (PacientesDaoException ex) {
                Logger.getLogger(frmOtorgarTurnos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pacientes;
    }
    
    public Option[] getServicios() {
        if (this.servicios==null) {
            try {
                Servicios[] s = DaoFactory.getDaoFactory().getServiciosDao().findAll();
                this.servicios = new Option[s.length];
                for (int i = 0; i < s.length; i++) {
                    this.servicios[i] = new Option(s[i].getIdservicio(), s[i].getServicio());
                }
            } catch (ServiciosDaoException ex) {
                Logger.getLogger(frmOtorgarTurnos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.especialidades = null;
        this.profesionales = null;
        return servicios;
        
    }

    public Option[] getEspecialidades() {
        
        if (this.especialidades==null) {
            
            Integer idServicio = (Integer) ddServicio.getValue();
                    
            try {
                Especialidades[] e = DaoFactory.getDaoFactory().getEspecialidadesDao().findByServicios(idServicio);
                this.especialidades = new Option[e.length];
                for (int i = 0; i < e.length; i++) {
                    this.especialidades[i] = new Option(e[i].getIdespecialidad(), e[i].getEspecialidad());
                }
            } catch (EspecialidadesDaoException ex) {
                Logger.getLogger(frmOtorgarTurnos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.profesionales = null;
        return especialidades;
    }

    public Option[] getProfesionales() {
        
       if (this.profesionales==null) {
           Integer idEspecialidad = (Integer) ddEspecialidad.getValue();
            
           try {
                Profesionales[] p = DaoFactory.getDaoFactory().getProfesionalesDao().findByEspecialidades(idEspecialidad);
                this.profesionales = new Option[p.length];
                for (int i = 0; i < p.length; i++) {
                    this.profesionales[i] = new Option(p[i].getIdprofesional(), "Dr. ".concat(p[i].getNombre()));
                }
            } catch (ProfesionalesDaoException ex) {
                Logger.getLogger(frmOtorgarTurnos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       this.horarios = null;
        return profesionales;
    }
    
    public Option[] getHorarios() {
       if (this.horarios==null) {
           Integer idProfesional = (Integer) ddProfesional.getValue();
            try {
                Horarios[] h = DaoFactory.getDaoFactory().getHorariosDao().findByProfesionales(idProfesional);
                this.horarios = new Option[h.length + 1];
                this.horarios[0] = new Option(new Integer(0), "Seleccione...");
                for (int i = 0; i < h.length; i++) {
                    String horario = h[i].getDia() + " de " + h[i].getDesde().toString() + " a " + h[i].getHasta().toString();
                    this.horarios[i+1] = new Option(h[i].getIdhorario(), horario);
                }
            } catch (HorariosDaoException ex) {
                Logger.getLogger(frmOtorgarTurnos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       this.fechas = null;
       return horarios;
    }

    public Option[] getFechas() {
        
        if (this.fechas==null) {
           Integer idHorario = (Integer) ddHorario.getValue();
            try {
                List c = new ArrayList();
                Horarios h = DaoFactory.getDaoFactory().getHorariosDao().findByPrimaryKey(idHorario);
                
                Calendar hoy = Calendar.getInstance();
                for (int i=0; i<31; i++) {
                    hoy.add(Calendar.DAY_OF_MONTH, 1);
                    
                    String dia = hoy.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()).toLowerCase().replace('á', 'a').replace('é', 'e');
                    if (dia.equals(h.getDia().toLowerCase())) {
                        c.add(new SimpleDateFormat("dd/MM/yyyy").format(hoy.getTime()));
                        
                    }
                }
                
                this.fechas = new Option[c.size()+1];
                this.fechas[0] = new Option(new Integer(0), "Seleccione...");
                for (int i = 0; i<c.size(); i++) {
                    this.fechas[i+1] = new Option(c.get(i) );
                }
            }
            catch (Exception e) {
            }
        }
        this.turnos = null;
        return fechas;
    }
    
    public Option[] getTurnos() {
        
        if (this.turnos==null) {
           String fecha = (String)ddFecha.getSelected();
           if ( fecha == null) return null;
           Integer idHorario = (Integer) ddHorario.getValue();
           String idProfesional = String.valueOf(((Integer) ddProfesional.getValue()).intValue());
           
           List c = new ArrayList();
           
            try {
                Horarios h = DaoFactory.getDaoFactory().getHorariosDao().findByPrimaryKey(idHorario);
                Calendar desde = Calendar.getInstance();
                Calendar hasta = Calendar.getInstance();
                desde.setTime(new Date(h.getDesde().getTime()));
                hasta.setTime(new Date(h.getHasta().getTime()));
                
                TurnosDao tdao = DaoFactory.getDaoFactory().getTurnosDao();
                while (desde.before(hasta)) {
                    String hora = new SimpleDateFormat("HH:mm").format(desde.getTime());
                    String where = "idprofesional = " + idProfesional + " AND fecha='" + fecha + "' AND hora='" + hora + "'";
                    if (tdao.countByWhere(where, null)==0 ) c.add(hora);
                    desde.add(Calendar.MINUTE, 15);
                }
                this.turnos = new Option[c.size()];
                for (int i = 0; i<c.size(); i++) {
                    this.turnos[i] = new Option(c.get(i) );
                }
                
            }
            catch (Exception e) {
            }
        }
        
        return turnos;
    }
    
    public void ddServicio_processValueChange(ValueChangeEvent event) {
        this.fijarSeleccion(ddServicio, event);
        //this.especialidades = null;
        //this.profesionales = null;
    }
    
    public void ddEspecialidad_processValueChange(ValueChangeEvent event) {
        this.fijarSeleccion(ddEspecialidad, event);
        //this.profesionales = null;
    }

    public void ddProfesional_processValueChange(ValueChangeEvent event) {
        this.fijarSeleccion(ddProfesional, event);
    }
    
    public void ddHorarios_processValueChange(ValueChangeEvent event) {
        this.fijarSeleccion(ddHorario, event);
    }

    public void ddFechas_processValueChange(ValueChangeEvent event) {
        this.fijarSeleccion(ddFecha, event);
    }
    
    private void fijarSeleccion(DropDown dd, ValueChangeEvent event) {
        Option[] s = (Option[]) dd.getItems();
        for(int i=0; i<s.length; i++) {
            if (((Integer)s[i].getValue()).toString().equals(event.getNewValue())){
                dd.setSelected(s[i].getValue());
                break;
            }
        }
    }
    
    private void borrarCombo(DropDown dd) {
        Option sinValor[] = new Option[1];
        sinValor[0] = new Option("---");
        dd.setItems(sinValor);
        dd.setSelected(sinValor[0]);
    }

    public String cmdConfirmarPaciente_action() {
        ddPaciente.setDisabled(true);
        cmdConfirmarPaciente.setDisabled(true);
        ddServicio.setDisabled(false);
        ddEspecialidad.setDisabled(false);
        ddProfesional.setDisabled(false);
        cmdConfirmarProfesional.setDisabled(false);
        return "null";
    }

    public Button getCmdConfirmarPaciente() {
        return cmdConfirmarPaciente;
    }

    public void setCmdConfirmarPaciente(Button cmdConfirmarPaciente) {
        this.cmdConfirmarPaciente = cmdConfirmarPaciente;
    }

    public Button getCmdConfirmarProfesional() {
        return cmdConfirmarProfesional;
    }

    public void setCmdConfirmarProfesional(Button cmdConfirmarProfesional) {
        this.cmdConfirmarProfesional = cmdConfirmarProfesional;
    }

    public Button getCmdConfirmarTurno() {
        return cmdConfirmarTurno;
    }

    public void setCmdConfirmarTurno(Button cmdConfirmarTurno) {
        this.cmdConfirmarTurno = cmdConfirmarTurno;
    }

    public String cmdConfirmarProfesional_action() {
        if (ddProfesional.getValue()!=null) {
            if (((Integer) ddProfesional.getValue()).intValue()!=0) {
                ddServicio.setDisabled(true);
                ddEspecialidad.setDisabled(true);
                ddProfesional.setDisabled(true);
                ddHorario.setDisabled(false);
                ddFecha.setDisabled(false);
                ddTurno.setDisabled(false);
                cmdConfirmarProfesional.setDisabled(true);
                cmdConfirmarTurno.setDisabled(false);
            }
        }
        return "null";
    }

    public String lnkCerrarSesion_action() {
        // TODO: Replace with your code
        return "cerrarSesion";
    }

    public String cmdConfirmarTurno_action() {
        try {
            Integer idProfesional = (Integer) ddProfesional.getValue();
            Integer idPaciente = new Integer((String)ddPaciente.getValue());
            String fecha = (String) ddFecha.getSelected();
            String hora = (String) ddTurno.getSelected();

            Turnos t = new Turnos();
            t.setIdpaciente(idPaciente);
            t.setIdprofesional(idProfesional);
            t.setFecha(new java.sql.Date(SimpleDateFormat.getDateInstance().parse(fecha).getTime()));
            t.setHora(new java.sql.Time(new SimpleDateFormat("HH:mm").parse(hora).getTime()));
            DaoFactory.getDaoFactory().getTurnosDao().insert(t);

            ddHorario.setDisabled(true);
            ddFecha.setDisabled(true);
            ddTurno.setDisabled(true);
            txtMensaje.setVisible(true);
            cmdConfirmarTurno.setDisabled(true);
            
        } catch (Exception ex) {
            Logger.getLogger(frmOtorgarTurnos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "null";
    }

    public String cmdCancelar_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        return "cancelar";
    }

    public StaticText getTxtMensaje() {
        return txtMensaje;
    }

    public void setTxtMensaje(StaticText txtMensaje) {
        this.txtMensaje = txtMensaje;
    }
}

