<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Document   : frmProfesional
    Created on : 08/11/2008, 00:20:49
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
                    <webuijsf:form id="formulario">
                        <table align="center" border="0" cellpadding="0" cellspacing="0" style="left: 0px; top: -96px; position: absolute">
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
                                                    <webuijsf:hyperlink actionExpression="#{frmProfesional.lnkCerrarSesion_action}" id="lnkCerrarSesion" text="Cerrar Sesión"/>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <table>
                                        <tr>
                                            <td>
                                                <webuijsf:label id="label1" text="DNI"/>
                                            </td>
                                            <td>
                                                <webuijsf:textField binding="#{frmProfesional.txtDNI}" id="txtDNI"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <webuijsf:label id="label5" text="Nombre"/>
                                            </td>
                                            <td>
                                                <webuijsf:textField binding="#{frmProfesional.txtNombre}" id="txtNombre"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <webuijsf:label id="label6" text="Fecha de Nacimiento"/>
                                            </td>
                                            <td>
                                                <webuijsf:calendar binding="#{frmProfesional.calFecha}" id="calFecha"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <webuijsf:label id="label7" text="Domicilio"/>
                                            </td>
                                            <td>
                                                <webuijsf:textField binding="#{frmProfesional.txtDomicilio}" id="txtDomicilio"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <webuijsf:label id="label8" text="Telefono fijo"/>
                                            </td>
                                            <td>
                                                <webuijsf:textField binding="#{frmProfesional.txtTelefono}" id="textField4"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <webuijsf:label id="label4" text="Celular"/>
                                            </td>
                                            <td>
                                                <webuijsf:textField binding="#{frmProfesional.txtCelular}" id="txtCelular"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <webuijsf:label id="label3" text="email"/>
                                            </td>
                                            <td>
                                                <webuijsf:textField binding="#{frmProfesional.txtEmail}" id="txtEmail"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <webuijsf:label id="label2" text="Especialidad"/>
                                            </td>
                                            <td>
                                                <webuijsf:dropDown binding="#{frmProfesional.ddEspecialidad}" id="ddEspecialidad"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <webuijsf:button actionExpression="#{frmProfesional.cmdCancelar_action}" id="cmdCancelar"
                                                    style="height: 24px; width: 71px" text="Cancelar"/>
                                            </td>
                                            <td>
                                                <webuijsf:button actionExpression="#{frmProfesional.cmdAceptar_action}" id="cmdAceptar"
                                                    style="height: 24px; width: 71px" text="Aceptar"/>
                                                <webuijsf:hiddenField id="hdnId1" binding="#{frmProfesional.hdnId}" />
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
