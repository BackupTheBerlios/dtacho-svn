
<%@ page import="org.opendtacho.domain.DtDriver" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show DtDriver</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">DtDriver List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New DtDriver</g:link></span>
        </div>
        <div class="body">
            <h1>Show DtDriver</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtDriverInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Person:</td>
                            
                            <td valign="top" class="value"><g:link controller="dtPerson" action="show" id="${dtDriverInstance?.person?.id}">${dtDriverInstance?.person?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Company:</td>
                            
                            <td valign="top" class="value"><g:link controller="dtCompany" action="show" id="${dtDriverInstance?.company?.id}">${dtDriverInstance?.company?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        %{--<tr class="prop">
                            <td valign="top" class="name">Activity Changes:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="a" in="${dtDriverInstance.activityChanges}">
                                    <li><g:link controller="dtActivityChange" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Card2drivers:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="c" in="${dtDriverInstance.card2drivers}">
                                    <li><g:link controller="dtCard2driver" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>--}%
                    
                        <tr class="prop">
                            <td valign="top" class="name">Card Holder Birth Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtDriverInstance, field:'cardHolderBirthDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Card Holder Nameholder First Names:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtDriverInstance, field:'cardHolderName_holderFirstNames')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Card Holder Nameholder Surname:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtDriverInstance, field:'cardHolderName_holderSurname')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Card Holder Preferred Language:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtDriverInstance, field:'cardHolderPreferredLanguage')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Driving Licence Issuing Authority:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtDriverInstance, field:'drivingLicenceIssuingAuthority')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Driving Licence Issuing Nation:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtDriverInstance, field:'drivingLicenceIssuingNation')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Driving Licence Number:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:dtDriverInstance, field:'drivingLicenceNumber')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${dtDriverInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
