<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Document   : frmBandaHoraria
    Created on : 08/11/2008, 00:19:56
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
                        <table align="center" border="0" cellpadding="0" cellspacing="0" style="">
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
                                <td align="left" style="height: 253px" width="170px">
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
                                                    <webuijsf:hyperlink actionExpression="#{frmHorarios.lnkCerrarSesion_action}" id="lnkCerrarSesion" text="Cerrar Sesión"/>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </td>
                                <td align="center">
                                    <h:panelGrid cellpadding="5" cellspacing="2" id="gridPanel1">
                                        <webuijsf:staticText binding="#{frmHorarios.txtEtiqueta}" id="txtEtiqueta"/>
                                    </h:panelGrid>
                                    <h:panelGrid cellpadding="5" cellspacing="2" columns="5" id="gridPanel2">
                                        <webuijsf:dropDown binding="#{frmHorarios.ddDias}" id="ddDia" label="Dia"/>
                                        <webuijsf:textField binding="#{frmHorarios.txtDesde}" id="txtDesde" label="Desde"/>
                                        <webuijsf:textField binding="#{frmHorarios.txtHasta}" id="txtHasta" label="Hasta"/>
                                        <webuijsf:button actionExpression="#{frmHorarios.cmdAgregar_action}" id="cmdAgregar" text="Agregar"/>
                                        <webuijsf:button actionExpression="#{frmHorarios.cmdVolver_action}" id="cmdVolver" text="Volver"/>
                                    </h:panelGrid>
                                    <h:panelGrid cellpadding="5" cellspacing="2" id="gridPanel3">
                                        <webuijsf:table augmentTitle="false" id="table1" title="Horarios" width="400">
                                            <webuijsf:tableRowGroup binding="#{frmHorarios.rowGroup}" id="tableRowGroup1" rows="10"
                                                sourceData="#{frmHorarios.dpHorarios}" sourceVar="currentRow">
                                                <webuijsf:tableColumn headerText="ID" id="tableColumn1">
                                                    <webuijsf:staticText id="staticText2" text="#{currentRow.value['idhorario']}"/>
                                                </webuijsf:tableColumn>
                                                <webuijsf:tableColumn headerText="Dia" id="tableColumn4">
                                                    <webuijsf:staticText id="staticText1" text="#{currentRow.value['dia']}"/>
                                                </webuijsf:tableColumn>
                                                <webuijsf:tableColumn headerText="Desde" id="tableColumn5">
                                                    <webuijsf:staticText id="staticText5" text="#{currentRow.value['desde']}"/>
                                                </webuijsf:tableColumn>
                                                <webuijsf:tableColumn headerText="Hasta" id="tableColumn6">
                                                    <webuijsf:staticText id="staticText6" text="#{currentRow.value['hasta']}"/>
                                                </webuijsf:tableColumn>
                                                <webuijsf:tableColumn id="tableColumn7">
                                                    <webuijsf:imageHyperlink actionExpression="#{frmHorarios.imageHyperlink1_action}" id="imageHyperlink1"
                                                        imageURL="/resources/delete24.png" text=""/>
                                                </webuijsf:tableColumn>
                                            </webuijsf:tableRowGroup>
                                        </webuijsf:table>
                                    </h:panelGrid>
                                    <webuijsf:hiddenField binding="#{frmHorarios.hdnId}" id="hdnId"/>
                                </td>
                            </tr>
                        </table>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
