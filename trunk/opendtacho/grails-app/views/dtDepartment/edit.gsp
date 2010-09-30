
<%@ page import="org.opendtacho.domain.DtDepartment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit DtDepartment</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">DtDepartment List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New DtDepartment</g:link></span>
        </div>
        <div class="body">
            <h1>Edit DtDepartment</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${dtDepartmentInstance}">
            <div class="errors">
                <g:renderErrors bean="${dtDepartmentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${dtDepartmentInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtDepartment,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:dtDepartmentInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtDepartment,field:'description','errors')}">
                                    <input type="text" id="description" name="description" value="${fieldValue(bean:dtDepartmentInstance,field:'description')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="members">Members:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtDepartment,field:'members','errors')}">
                                    <g:select name="members"
from="${org.opendtacho.domain.DtPerson.list()}"
size="5" multiple="yes" optionKey="id"
value="${dtDepartmentInstance?.members}" />

                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
