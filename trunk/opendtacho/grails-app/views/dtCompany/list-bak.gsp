
<%@ page import="org.opendtacho.domain.DtCompany" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>DtCompany List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
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
				<th>Action</th>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="companyName" title="Company Name" />
                        
                   	        <g:sortableColumn property="companyAddress" title="Company Address" />
                        
                   	        <g:sortableColumn property="name1" title="Name1" />
                        
                   	        <g:sortableColumn property="name2" title="Name2" />
                        
                   	        <g:sortableColumn property="name3" title="Name3" />
                        
                   	        <g:sortableColumn property="zipcode" title="Zipcode" />
                        
                   	        <g:sortableColumn property="city" title="City" />
                        
                   	        <g:sortableColumn property="street" title="Street" />
                        
                   	        <g:sortableColumn property="housenumber" title="Housenumber" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${dtCompanyInstanceList}" status="i" var="dtCompanyInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>
                                <g:link action="show" id="${dtCompanyInstance?.id}">view</g:link> |
                                <g:link action="edit" id="${dtCompanyInstance?.id}">edit</g:link> |
                                <g:link action="del" id="${dtCompanyInstance?.id}" onclick="return confirm('Are you sure?');">del</g:link>
                            </td>

                        
                            <td><g:link action="show" id="${dtCompanyInstance.id}">${dtCompanyInstance.id?.encodeAsHTML()}</g:link></td>
                        
                            <td>${dtCompanyInstance.companyName?.encodeAsHTML()}</td>
                        
                            <td>${dtCompanyInstance.companyAddress?.encodeAsHTML()}</td>
                        
                            <td>${dtCompanyInstance.name1?.encodeAsHTML()}</td>
                        
                            <td>${dtCompanyInstance.name2?.encodeAsHTML()}</td>
                        
                            <td>${dtCompanyInstance.name3?.encodeAsHTML()}</td>
                        
                            <td>${dtCompanyInstance.zipcode?.encodeAsHTML()}</td>
                        
                            <td>${dtCompanyInstance.city?.encodeAsHTML()}</td>
                        
                            <td>${dtCompanyInstance.street?.encodeAsHTML()}</td>
                        
                            <td>${dtCompanyInstance.housenumber?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${DtCompany.count()}" />
            </div>
        </div>
    </body>
</html>

