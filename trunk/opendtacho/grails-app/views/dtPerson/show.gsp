
<%@ page import="org.opendtacho.domain.DtPerson" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show DtPerson</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">DtPerson List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New DtPerson</g:link></span>
        </div>
        <div class="body">
            <h1>Show DtPerson</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Personnel Number:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'personnelNumber')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Title:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'title')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Salutation:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'salutation')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">First Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'firstName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Last Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'lastName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Private Zipcode:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'privateZipcode')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Private City:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'privateCity')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Private Street:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'privateStreet')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Private Housenumber:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'privateHousenumber')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Private Address Supplement:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'privateAddressSupplement')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Email:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'email')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Phone:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'phone')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Fax:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'fax')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Preferred Language:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'preferredLanguage')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Traffic Signal Value:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtPersonInstance, field:'trafficSignalValue')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Company:</td>
                            
                            <td valign="top" class="value"><g:link controller="dtCompany" action="show" id="${dtPersonInstance?.company?.id}">${dtPersonInstance?.company?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Subsidiary:</td>
                            
                            <td valign="top" class="value"><g:link controller="dtSubsidiary" action="show" id="${dtPersonInstance?.subsidiary?.id}">${dtPersonInstance?.subsidiary?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Department:</td>
                            
                            <td valign="top" class="value"><g:link controller="dtDepartment" action="show" id="${dtPersonInstance?.department?.id}">${dtPersonInstance?.department?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Users:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="u" in="${dtPersonInstance.users}">
                                    <li><g:link controller="dtUser" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Driver:</td>
                            
                            <td valign="top" class="value"><g:link controller="dtDriver" action="show" id="${dtPersonInstance?.driver?.id}">${dtPersonInstance?.driver?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${dtPersonInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
