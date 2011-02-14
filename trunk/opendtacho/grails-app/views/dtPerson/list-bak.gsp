
<%@ page import="org.opendtacho.domain.DtPerson" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>DtPerson List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
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
				<th>Action</th>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="personnelNumber" title="Personnel Number" />
                        
                   	        <g:sortableColumn property="title" title="Title" />
                        
                   	        <g:sortableColumn property="salutation" title="Salutation" />
                        
                   	        <g:sortableColumn property="firstName" title="First Name" />
                        
                   	        <g:sortableColumn property="lastName" title="Last Name" />
                        
                   	        <g:sortableColumn property="privateZipcode" title="Private Zipcode" />
                        
                   	        <g:sortableColumn property="privateCity" title="Private City" />
                        
                   	        <g:sortableColumn property="privateStreet" title="Private Street" />
                        
                   	        <g:sortableColumn property="privateHousenumber" title="Private Housenumber" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${dtPersonInstanceList}" status="i" var="dtPersonInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>
                                <g:link action="show" id="${dtPersonInstance?.id}">view</g:link> |
                                <g:link action="edit" id="${dtPersonInstance?.id}">edit</g:link> |
                                <g:link action="del" id="${dtPersonInstance?.id}" onclick="return confirm('Are you sure?');">del</g:link>
                            </td>

                        
                            <td><g:link action="show" id="${dtPersonInstance.id}">${dtPersonInstance.id?.encodeAsHTML()}</g:link></td>
                        
                            <td>${dtPersonInstance.personnelNumber?.encodeAsHTML()}</td>
                        
                            <td>${dtPersonInstance.title?.encodeAsHTML()}</td>
                        
                            <td>${dtPersonInstance.salutation?.encodeAsHTML()}</td>
                        
                            <td>${dtPersonInstance.firstName?.encodeAsHTML()}</td>
                        
                            <td>${dtPersonInstance.lastName?.encodeAsHTML()}</td>
                        
                            <td>${dtPersonInstance.privateZipcode?.encodeAsHTML()}</td>
                        
                            <td>${dtPersonInstance.privateCity?.encodeAsHTML()}</td>
                        
                            <td>${dtPersonInstance.privateStreet?.encodeAsHTML()}</td>
                        
                            <td>${dtPersonInstance.privateHousenumber?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${DtPerson.count()}" />
            </div>
        </div>
    </body>
</html>

