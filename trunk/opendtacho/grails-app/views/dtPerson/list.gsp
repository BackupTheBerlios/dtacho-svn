
<%@ page import="org.opendtacho.domain.DtPerson" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>DtPerson List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New DtPerson</g:link></span>
        </div>
        <div class="body">
            <h1>DtPerson List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="personnelNumber" title="Personnel Number" />
                        
                   	        <g:sortableColumn property="title" title="Title" />
                        
                   	        <g:sortableColumn property="salutation" title="Salutation" />
                        
                   	        <g:sortableColumn property="firstName" title="First Name" />
                        
                   	        <g:sortableColumn property="lastName" title="Last Name" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${dtPersonInstanceList}" status="i" var="dtPersonInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${dtPersonInstance.id}">${fieldValue(bean:dtPersonInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:dtPersonInstance, field:'personnelNumber')}</td>
                        
                            <td>${fieldValue(bean:dtPersonInstance, field:'title')}</td>
                        
                            <td>${fieldValue(bean:dtPersonInstance, field:'salutation')}</td>
                        
                            <td>${fieldValue(bean:dtPersonInstance, field:'firstName')}</td>
                        
                            <td>${fieldValue(bean:dtPersonInstance, field:'lastName')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${dtPersonInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
