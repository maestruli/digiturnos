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
                                    <table border="0" cellpadding="5" cellspacing="2" id="table1">
                                        <tr>
                                            <td align="left">
                                                <webuijsf:label id="label1" text="Ficha Médica"/>
                                            </td>
                                            <td>
                                                <webuijsf:textField binding="#{frmPaciente.txtFichaMedica}" id="txtFichaMedica"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                                <webuijsf:label id="label2" text="DNI"/>
                                            </td>
                                            <td>
                                                <webuijsf:textField binding="#{frmPaciente.txtDNI}" id="txtDNI"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                                <webuijsf:label id="label3" text="Nombre"/>
                                            </td>
                                            <td>
                                                <webuijsf:textField binding="#{frmPaciente.txtNombre}" id="txtNombre"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                                <webuijsf:label id="label4" text="Sexo"/>
                                            </td>
                                            <td>
                                                <webuijsf:dropDown binding="#{frmPaciente.ddSexo}" id="ddSexo" items="#{frmPaciente.ddSexoDefaultOptions.options}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                                <webuijsf:label id="label5" text="Fecha de Nacimiento"/>
                                            </td>
                                            <td>
                                                <webuijsf:calendar binding="#{frmPaciente.cldFechaNacimiento}" id="cldFechaNacimiento"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                                <webuijsf:label id="label6" text="Domicilio"/>
                                            </td>
                                            <td>
                                                <webuijsf:textField binding="#{frmPaciente.txtDomicilio}" id="txtDomicilio"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                                <webuijsf:label id="label7" text="Teléfono"/>
                                            </td>
                                            <td>
                                                <webuijsf:textField binding="#{frmPaciente.txtTelefono}" id="txtTelefono"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                                <webuijsf:label id="label8" text="Celular"/>
                                            </td>
                                            <td>
                                                <webuijsf:textField binding="#{frmPaciente.txtCelular}" id="txtCelular"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                                <webuijsf:label id="label9" text="E-mail"/>
                                            </td>
                                            <td>
                                                <webuijsf:textField binding="#{frmPaciente.txtEmail}" id="txtEmail"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                                <webuijsf:label id="label10" text="Observaciones"/>
                                            </td>
                                            <td>
                                                <webuijsf:textArea binding="#{frmPaciente.txtObservaciones}" id="txtObservaciones"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center" colspan="2">
                                                <webuijsf:button actionExpression="#{frmPaciente.cmdAceptar_action}" id="cmdAceptar"
                                                    style="height: 24px; width: 71px" text="Aceptar"/>
                                                <webuijsf:button actionExpression="#{frmPaciente.cmdCancelar_action}" id="cmdCancelar"
                                                    style="height: 24px; width: 71px" text="Cancelar"/>
                                            </td>
                                        </tr>
                                    </table>
                                    <webuijsf:hiddenField binding="#{frmPaciente.hdnId}" id="hdnId"/>
                                </td>
                            </tr>
                        </table>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
