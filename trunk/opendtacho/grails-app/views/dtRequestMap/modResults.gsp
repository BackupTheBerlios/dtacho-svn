<%--
  Created by IntelliJ IDEA.
  User: hoanganhle
  Date: Sep 7, 2010
  Time: 7:57:58 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
%{-- Style sheet for tooltip of grails-ui plugin--}%
<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'grails_ui.css')}"/>
<html>
  <head>
    <title><g:message code="results.title"/></title>
    <meta name="layout" content="main"/>
    <style type="text/css">
      .list table{
        border-collapse: separate;
        border-style: solid;
        table-layout: auto;
      }
      .list th, .list td{
        border-left:none;
      }
    </style>
  </head>
  <body>

    <p style="color:red;">
      <g:message code="authen.status"/>
    </p>
    <br/>

    <a href="${resource(dir:'/dtRequestMap')}"><g:message code="authen.list"/></a>
  </body>
</html>