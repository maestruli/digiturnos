<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Document   : frmPrincipal
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
                <webuijsf:body id="body1" imageURL="/resources/fondo.jpg" style="-rave-layout: grid">
                    <table align="center" background="resources/bannerbg.gif" border="0" cellpadding="0" cellspacing="0" height="74" style="background-repeat: no-repeat;" width="604">
                        <tr>
                            <td colspan="2">
                                <img alt="df" border="0" src="resources/header.jpg" vspace="1"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="right" background="resources/subbanner.jpg" width="100%">
                                <a class="linkSubbanner" href="#">Inicio</a>|
                                <a class="linkSubbanner" href="#">Contáctenos</a>
                                <br/>
                            </td>
                        </tr>
                        <tr align="left" valign="top">
                            <td width="170px" align="left">
                                <div id="menu">
                                    <h:outputText rendered="#{SessionBean1.paciente}">
                                    <div id="menuCliente">
                                        <h1>Menu</h1>
                                        <ul class="linksmenu">
                                            <li><a href="index.php">Reservar Turno</a></li>
                                            <li><a href="planes.php">Ver turnos</a></li>
                                            <li><a href="nosotros.php">Ver Historico</a></li>
                                        </ul>
                                    </div>
                                    </h:outputText>
                                    <h:outputText rendered="#{SessionBean1.medico}">
                                    <div id="menuMedico">
                                        <h1>Menu</h1>
                                        <ul class="linksmenu">
                                            <li><a href="index.php">Ver agenda de día</a></li>
                                        </ul>
                                    </div>
                                    </h:outputText>
                                    <h:outputText rendered="#{SessionBean1.empleado}">
                                    <div id="menuEmpleado">
                                        <h1>Menu</h1>
                                        <ul class="linksmenu">
                                            <li><a href="index.php">Otorgar Turnos</a></li>
                                            <li><a href="planes.php">Administrar Pacientes</a></li>
                                            <li><a href="nosotros.php">Administrar Servicios</a></li>
                                            <li><a href="nosotros.php">Administrar Especialidades</a></li>
                                            <li><a href="nosotros.php">Administrar Médicos</a></li>
                                            <li><a href="nosotros.php">Administrar Bandas Horarias</a></li>
                                            <li><a href="nosotros.php">Otorgar Licencias</a></li>
                                        </ul>
                                    </div>
                                    </h:outputText>
                                </div>			
                            </td>
                            <td valign="top">
                                <webuijsf:form id="form1">
                                    Aca van las cosas
                                </webuijsf:form>
                            </td>
                        </tr>
                    </table>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
