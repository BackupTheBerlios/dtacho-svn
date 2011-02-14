
<%@ page import="org.opendtacho.domain.DtCompany" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>DtCompany List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New DtCompany</g:link></span>
        </div>
        <div class="body">
            <h1>DtCompany List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="companyName" title="Company Name" />
                        
                   	        <g:sortableColumn property="companyAddress" title="Company Address" />
                        
                   	        <g:sortableColumn property="name1" title="Name1" />
                        
                   	        <g:sortableColumn property="name2" title="Name2" />
                        
                   	        <g:sortableColumn property="name3" title="Name3" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${dtCompanyInstanceList}" status="i" var="dtCompanyInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${dtCompanyInstance.id}">${fieldValue(bean:dtCompanyInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:dtCompanyInstance, field:'companyName')}</td>
                        
                            <td>${fieldValue(bean:dtCompanyInstance, field:'companyAddress')}</td>
                        
                            <td>${fieldValue(bean:dtCompanyInstance, field:'name1')}</td>
                        
                            <td>${fieldValue(bean:dtCompanyInstance, field:'name2')}</td>
                        
                            <td>${fieldValue(bean:dtCompanyInstance, field:'name3')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${dtCompanyInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
