<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Document   : frmOtorgarTurnos
    Created on : 07/11/2008, 23:52:29
    Author     : Augusto
-->
<jsp:root version="2.1" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <webuijsf:page id="page1">
            <webuijsf:html id="html1">
                <webuijsf:head id="head1">
                    <webuijsf:link id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body id="body1" style="-rave-layout: grid">
                    <webuijsf:form id="formulario" virtualFormsConfig="">
                        <table align="center" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td colspan="2">
                                    <img alt="Digiturnos" border="0" src="resources/header.jpg"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" bgcolor="#416EED" colspan="2">
                                    <a class="linkSubbanner" href="#">Inicio</a> |
                                    <a class="linkSubbanner" href="#">Contáctenos</a>
                                    <br/>
                                </td>
                            </tr>
                            <tr align="left" valign="top">
                                <td align="left" width="170px">
                                    <div id="menu">
                                        <h:outputText rendered="#{SessionBean1.paciente}">
                                            <div id="menuCliente">
                                                <h1>Menu</h1>
                                                <ul class="linksmenu">
                                                    <li>
                                                        <webuijsf:hyperlink id="linkReservaTurno" text="Reservar Turno" url="/faces/frmReservarTurno.jsp"/>
                                                    </li>
                                                    <li>
                                                        <webuijsf:hyperlink id="linkVerTurnos" text="Ver turnos" url="/faces/frmVerTurnos.jsp"/>
                                                    </li>
                                                    <li>
                                                        <webuijsf:hyperlink id="linkVerHistorico" text="Ver Historico" url="/faces/frmVerTurnos.jsp"/>
                                                    </li>
                                                </ul>
                                            </div>
                                        </h:outputText>
                                        <h:outputText rendered="#{SessionBean1.profesional}">
                                            <div id="menuProfesional">
                                                <h1>Menu</h1>
                                                <ul class="linksmenu">
                                                    <li>
                                                        <webuijsf:hyperlink id="lnkVerAgenda" text="Ver agenda de día" url="/faces/frmVerAgenda.jsp"/>
                                                    </li>
                                                </ul>
                                            </div>
                                        </h:outputText>
                                        <h:outputText rendered="#{SessionBean1.empleado}">
                                            <div id="menuEmpleado">
                                                <h1>Menu</h1>
                                                <ul class="linksmenu">
                                                    <li>
                                                        <webuijsf:hyperlink id="lnkOtorgarTurnos" text="Otorgar Turnos" url="/faces/frmOtorgarTurnos.jsp"/>
                                                    </li>
                                                    <li>
                                                        <webuijsf:hyperlink id="lnkPacientes" text="Administrar Pacientes" url="/faces/frmPacientes.jsp"/>
                                                    </li>
                                                    <li>
                                                        <webuijsf:hyperlink id="lnkServicios" text="Administrar Servicios" url="/faces/frmServicios.jsp"/>
                                                    </li>
                                                    <li>
                                                        <webuijsf:hyperlink id="lnkEspecialidades" text="Administrar Especialidades" url="/faces/frmEspecialidades.jsp"/>
                                                    </li>
                                                    <li>
                                                        <webuijsf:hyperlink id="lnkProfesionales" text="Administrar Profesionales" url="/faces/frmProfesionales.jsp"/>
                                                    </li>
                                                    <li>
                                                        <webuijsf:hyperlink id="lnkOtorgarLicencias" text="Otorgar Licencias" url="/faces/frmOtorgarLicencias.jsp"/>
                                                    </li>
                                                </ul>
                                            </div>
                                        </h:outputText>
                                        <h:outputText rendered="#{SessionBean1.admin}">
                                            <div id="menuAdmin">
                                                <h1>Menu</h1>
                                                <ul class="linksmenu">
                                                    <li>
                                                        <webuijsf:hyperlink id="lnkUsuarios" text="Administar Usuarios" url="/faces/frmUsuarios.jsp"/>
                                                    </li>
                                                </ul>
                                            </div>
                                        </h:outputText>
                                        <div id="menuSalir">
                                            <ul class="linksmenu">
                                                <li>
                                                    <webuijsf:hyperlink actionExpression="#{frmPrincipal.lnkCerrarSesion_action}" id="lnkCerrarSesion" text="Cerrar Sesión                                                     "/>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </td>
                                <td valign="top">
                                    <table>
                                        <tr>
                                            <td>
                                                Paciente
                                            </td>
                                            <td colspan="5">
                                                <webuijsf:dropDown binding="#{frmOtorgarTurnos.ddPaciente}" id="ddPaciente" items="#{frmOtorgarTurnos.pacientes}"/>
                                            </td>
                                            <td>
                                                <webuijsf:button actionExpression="#{frmOtorgarTurnos.cmdConfirmarPaciente_action}"
                                                    binding="#{frmOtorgarTurnos.cmdConfirmarPaciente}" id="cmdConfirmarPaciente" style="width: 119px" text="Confirmar Paciente"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Servicio
                                            </td>
                                            <td>
                                                <webuijsf:dropDown binding="#{frmOtorgarTurnos.ddServicio}" disabled="true" id="ddServicio"
                                                    items="#{frmOtorgarTurnos.servicios}"
                                                    onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'ddServicio');" valueChangeListenerExpression="#{frmOtorgarTurnos.ddServicio_processValueChange}"/>
                                            </td>
                                            <td>
                                                Especialidad
                                            </td>
                                            <td>
                                                <webuijsf:dropDown binding="#{frmOtorgarTurnos.ddEspecialidad}" disabled="true" id="ddEspecialidad"
                                                    items="#{frmOtorgarTurnos.especialidades}"
                                                    onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'ddEspecialidad');" valueChangeListenerExpression="#{frmOtorgarTurnos.ddEspecialidad_processValueChange}"/>
                                            </td>
                                            <td>
                                                Profesional
                                            </td>
                                            <td>
                                                <webuijsf:dropDown binding="#{frmOtorgarTurnos.ddProfesional}" disabled="true" id="ddProfesional"
                                                    items="#{frmOtorgarTurnos.profesionales}"
                                                    onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'ddProfesional');" valueChangeListenerExpression="#{frmOtorgarTurnos.ddProfesional_processValueChange}"/>
                                            </td>
                                            <td>
                                                <webuijsf:button actionExpression="#{frmOtorgarTurnos.cmdConfirmarProfesional_action}"
                                                    binding="#{frmOtorgarTurnos.cmdConfirmarProfesional}" disabled="true" id="cmdConfirmarProfesional"
                                                    style="width: 119px" text="Confirmar Profesional"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Horarios
                                            </td>
                                            <td>
                                                <webuijsf:dropDown binding="#{frmOtorgarTurnos.ddHorario}" disabled="true" id="ddHorarios"
                                                    items="#{frmOtorgarTurnos.horarios}"
                                                    onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'ddHorarios');" valueChangeListenerExpression="#{frmOtorgarTurnos.ddHorarios_processValueChange}"/>
                                            </td>
                                            <td>
                                                Fechas
                                            </td>
                                            <td>
                                                <webuijsf:dropDown binding="#{frmOtorgarTurnos.ddFecha}" disabled="true" id="ddFechas"
                                                    items="#{frmOtorgarTurnos.fechas}"
                                                    onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'ddFechas');" valueChangeListenerExpression="#{frmOtorgarTurnos.ddFechas_processValueChange}"/>
                                            </td>
                                            <td>
                                                Turno
                                            </td>
                                            <td>
                                                <webuijsf:dropDown binding="#{frmOtorgarTurnos.ddTurno}" disabled="true" id="ddTurnos"
                                                    items="#{frmOtorgarTurnos.turnos}" onChange=""/>
                                            </td>
                                            <td>
                                                <webuijsf:button binding="#{frmOtorgarTurnos.cmdConfirmarTurno}" disabled="true" id="cmdConfirmarTurno"
                                                    style="width: 119px" text="Confirmar Turno"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right" colspan="7">
                                                <webuijsf:button id="cmdCancelar" style="height: 24px; width: 120px" text="Cancelar"/>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
