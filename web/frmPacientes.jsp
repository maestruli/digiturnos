<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Document   : frmPacientes
    Created on : 07/11/2008, 23:53:34
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
                <webuijsf:body id="body1" imageURL="/resources/fondo.jpg" style="-rave-layout: grid">
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
                                                        <webuijsf:hyperlink id="lnkVerAgenda" text="Ver agenda de día"/>
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
                                                    <webuijsf:hyperlink actionExpression="#{frmPacientes.lnkCerrarSesion_action}" id="lnkCerrarSesion" text="Cerrar Sesión"/>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <table width="100%">
                                        <tr>
                                            <td align="center">
                                                <webuijsf:table augmentTitle="false" id="table1" paginateButton="true" paginationControls="true"
                                                    title="Pacientes" width="384">
                                                    <webuijsf:tableRowGroup binding="#{frmPacientes.rowGroup}" id="tableRowGroup1" rows="10"
                                                        sourceData="#{frmPacientes.dpPacientes}" sourceVar="currentRow">
                                                        <webuijsf:tableColumn align="center" headerText="Ficha Médica" id="tableColumn1" sort="idpaciente">
                                                            <webuijsf:staticText id="staticText1" text="#{currentRow.value['idpaciente']}"/>
                                                        </webuijsf:tableColumn>
                                                        <webuijsf:tableColumn headerText="Paciente" id="tableColumn2" sort="nombre">
                                                            <webuijsf:staticText id="staticText2" text="#{currentRow.value['nombre']}"/>
                                                        </webuijsf:tableColumn>
                                                        <webuijsf:tableColumn align="center" id="tableColumn4">
                                                            <webuijsf:imageHyperlink actionExpression="#{frmPacientes.imageHyperlink1_action}"
                                                                id="imageHyperlink1" imageURL="/resources/edit24.png" text=""/>
                                                        </webuijsf:tableColumn>
                                                        <webuijsf:tableColumn align="center" id="tableColumn5">
                                                            <webuijsf:imageHyperlink actionExpression="#{frmPacientes.imageHyperlink2_action}"
                                                                id="imageHyperlink2" imageURL="/resources/delete24.png" text=""/>
                                                        </webuijsf:tableColumn>
                                                    </webuijsf:tableRowGroup>
                                                </webuijsf:table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right">
                                                <webuijsf:button actionExpression="#{frmPacientes.button1_action}" id="button1" text="Nuevo"/>
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
