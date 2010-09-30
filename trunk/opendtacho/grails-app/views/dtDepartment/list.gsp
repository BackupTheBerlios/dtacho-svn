
<%@ page import="org.opendtacho.domain.DtDepartment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>DtDepartment List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New DtDepartment</g:link></span>
        </div>
        <div class="body">
            <h1>DtDepartment List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
				<th>Action</th>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${dtDepartmentInstanceList}" status="i" var="dtDepartmentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>
                                <g:link action="show" id="${dtDepartmentInstance?.id}">view</g:link> |
                                <g:link action="edit" id="${dtDepartmentInstance?.id}">edit</g:link> |
                                <g:link action="del" id="${dtDepartmentInstance?.id}" onclick="return confirm('Are you sure?');">del</g:link>
                            </td>

                        
                            <td><g:link action="show" id="${dtDepartmentInstance.id}">${dtDepartmentInstance.id?.encodeAsHTML()}</g:link></td>
                        
                            <td>${dtDepartmentInstance.name?.encodeAsHTML()}</td>
                        
                            <td>${dtDepartmentInstance.description?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${DtDepartment.count()}" />
            </div>
        </div>
    </body>
</html>

