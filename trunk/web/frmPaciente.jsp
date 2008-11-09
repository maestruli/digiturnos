<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Document   : frmPaciente
    Created on : 02/11/2008, 17:08:28
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
                                                        <webuijsf:hyperlink id="lnkBandasHorarias" text="Administrar Bandas Horarias" url="/faces/frmBandasHorarias.jsp"/>
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
                                                    <webuijsf:hyperlink actionExpression="#{frmPaciente.lnkCerrarSesion_action}" id="lnkCerrarSesion" text="Cerrar Sesión"/>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </td>
                                <td align="center">
                                    <h:panelGrid cellpadding="5" cellspacing="2" id="gridPanel1">
                                        <webuijsf:hiddenField binding="#{frmPaciente.hdnId}" id="hdnId"/>
                                        <webuijsf:textField binding="#{frmPaciente.txtFichaMedica}" columns="15" id="txtFichaMedica" label="Ficha Médica"/>
                                        <webuijsf:textField binding="#{frmPaciente.txtDNI}" columns="15" id="txtDNI" label="DNI"/>
                                        <webuijsf:textField binding="#{frmPaciente.txtNombre}" columns="30" id="txtNombre" label="Nombre"/>
                                        <webuijsf:dropDown binding="#{frmPaciente.ddSexo}" id="ddSexo" items="#{frmPaciente.ddSexoDefaultOptions.options}" label="Sexo"/>
                                        <webuijsf:calendar binding="#{frmPaciente.cldFechaNacimiento}" columns="15" id="cldFechaNacimiento" label="Fecha de Nacimiento"/>
                                        <webuijsf:textField binding="#{frmPaciente.txtDomicilio}" columns="30" id="txtDomicilio" label="Domicilio"/>
                                        <webuijsf:textField binding="#{frmPaciente.txtTelefono}" id="txtTelefono" label="Teléfono"/>
                                        <webuijsf:textField binding="#{frmPaciente.txtCelular}" id="txtCelular" label="Celular"/>
                                        <webuijsf:textField binding="#{frmPaciente.txtEmail}" columns="30" id="txtEmail" label="E-mail"/>
                                        <webuijsf:textArea binding="#{frmPaciente.txtObservaciones}" columns="30" id="txtObservaciones" label="Observaciones"/>
                                    </h:panelGrid>
                                    <h:panelGrid cellpadding="5" cellspacing="2" columns="2" id="gridPanel2">
                                        <webuijsf:button actionExpression="#{frmPaciente.cmdAceptar_action}" id="cmdAceptar" text="Aceptar"/>
                                        <webuijsf:button actionExpression="#{frmPaciente.cmdCancelar_action}" id="cmdCancelar" text="Cancelar"/>
                                    </h:panelGrid>
                                </td>
                            </tr>
                        </table>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
