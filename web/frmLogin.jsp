<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Document   : frmLogin
    Created on : 28/10/2008, 20:31:47
    Author     : Augusto
-->
<jsp:root version="2.1" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <webuijsf:page id="page1">
            <webuijsf:html id="html1">
                <webuijsf:head id="head1" title="Digiturnos">
                    <webuijsf:link id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body id="body1" imageURL="/resources/fondo.jpg" style="-rave-layout: grid">
                    <table align="center" background="resources/bannerbg.gif" border="0" cellpadding="0" cellspacing="0" height="74"
                        style="background-repeat: no-repeat;" width="604">
                        <tr>
                            <td colspan="2">
                                <img alt="df" border="0" src="resources/header.jpg" vspace="1"/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right" background="resources/subbanner.jpg" width="100%">
                                <a class="linkSubbanner" href="#">Inicio</a>|
                                <a class="linkSubbanner" href="#">Contáctenos</a>
                                <br/>
                            </td>
                        </tr>
                    </table>
                    <webuijsf:form id="formulario">
                        <table align="center" border="0" cellpadding="0" cellspacing="0" height="74" style="background-repeat: repeat;" width="767">
                            <tr>
                                <td>
                                    <table align="center" cellpadding="0" cellspacing="0" width="450">
                                        <tr>
                                            <td colspan="3">
                                                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                                    <tr>
                                                        <td>
                                                            <h2>Pantalla de Login</h2>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" nowrap="nowrap">
                                                <img align="absmiddle" src="resources/icon-info.gif"/> 
                                                Ingrese su nombre de usuario y password
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center" rowspan="3" valign="middle">
                                                <img hspace="6" src="resources/icon-loginpic.gif"/>
                                            </td>
                                            <td>
                                                <webuijsf:label for="txtDocumento" id="lblDocumento" text="Documento"/>
                                            </td>
                                            <td>
                                                <webuijsf:textField binding="#{frmLogin.txtUsuario}" id="txtDocumento"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <webuijsf:label id="lblPassword" text="Password"/>
                                            </td>
                                            <td>
                                                <webuijsf:passwordField binding="#{frmLogin.txtPassword}" id="txtPassword"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center" colspan="3" height="40" valign="middle">
                                                <webuijsf:button actionExpression="#{frmLogin.cmdLogin_action}" binding="#{frmLogin.cmdLogin}" id="cmdLogin" text="Login"/>
                                            </td>
                                        </tr>
                                    </table>
                                    <br/>
                                </td>
                            </tr>
                        </table>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
