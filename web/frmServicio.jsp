<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Document   : frmServicio
    Created on : 07/11/2008, 17:37:40
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
                    <webuijsf:form id="form1">
                        <h:panelGrid cellpadding="5" cellspacing="2" columns="2" id="gridPanel1" style="left: 336px; top: 288px; position: absolute; align: center">
                            <webuijsf:textField columns="30" id="textField1" label="Servicio" labelLevel="1"/>
                            <webuijsf:imageHyperlink id="imageHyperlink1" imageURL="/resources/add24.png"/>
                        </h:panelGrid>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
