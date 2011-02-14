
<%@ page import="org.opendtacho.domain.DtCompany" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show DtCompany</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">DtCompany List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New DtCompany</g:link></span>
        </div>
        <div class="body">
            <h1>Show DtCompany</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtCompanyInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Company Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtCompanyInstance, field:'companyName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Company Address:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtCompanyInstance, field:'companyAddress')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name1:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtCompanyInstance, field:'name1')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name2:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtCompanyInstance, field:'name2')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name3:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtCompanyInstance, field:'name3')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Zipcode:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtCompanyInstance, field:'zipcode')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">City:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtCompanyInstance, field:'city')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Street:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtCompanyInstance, field:'street')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Housenumber:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtCompanyInstance, field:'housenumber')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Address Supplement:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtCompanyInstance, field:'addressSupplement')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Drivers:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="d" in="${dtCompanyInstance.drivers}">
                                    <li><g:link controller="dtDriver" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Persons:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="p" in="${dtCompanyInstance.persons}">
                                    <li><g:link controller="dtPerson" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Subsidiaries:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="s" in="${dtCompanyInstance.subsidiaries}">
                                    <li><g:link controller="dtSubsidiary" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${dtCompanyInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
