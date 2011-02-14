
<%@ page import="org.opendtacho.domain.DtPerson" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit DtPerson</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">DtPerson List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New DtPerson</g:link></span>
        </div>
        <div class="body">
            <h1>Edit DtPerson</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${dtPersonInstance}">
            <div class="errors">
                <g:renderErrors bean="${dtPersonInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${dtPersonInstance?.id}" />
                <input type="hidden" name="version" value="${dtPersonInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="personnelNumber">Personnel Number:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'personnelNumber','errors')}">
                                    <input type="text" id="personnelNumber" name="personnelNumber" value="${fieldValue(bean:dtPersonInstance,field:'personnelNumber')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title">Title:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'title','errors')}">
                                    <input type="text" id="title" name="title" value="${fieldValue(bean:dtPersonInstance,field:'title')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="salutation">Salutation:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'salutation','errors')}">
                                    <input type="text" id="salutation" name="salutation" value="${fieldValue(bean:dtPersonInstance,field:'salutation')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstName">First Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'firstName','errors')}">
                                    <input type="text" id="firstName" name="firstName" value="${fieldValue(bean:dtPersonInstance,field:'firstName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastName">Last Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'lastName','errors')}">
                                    <input type="text" id="lastName" name="lastName" value="${fieldValue(bean:dtPersonInstance,field:'lastName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="privateZipcode">Private Zipcode:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'privateZipcode','errors')}">
                                    <input type="text" id="privateZipcode" name="privateZipcode" value="${fieldValue(bean:dtPersonInstance,field:'privateZipcode')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="privateCity">Private City:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'privateCity','errors')}">
                                    <input type="text" id="privateCity" name="privateCity" value="${fieldValue(bean:dtPersonInstance,field:'privateCity')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="privateStreet">Private Street:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'privateStreet','errors')}">
                                    <input type="text" id="privateStreet" name="privateStreet" value="${fieldValue(bean:dtPersonInstance,field:'privateStreet')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="privateHousenumber">Private Housenumber:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'privateHousenumber','errors')}">
                                    <input type="text" id="privateHousenumber" name="privateHousenumber" value="${fieldValue(bean:dtPersonInstance,field:'privateHousenumber')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="privateAddressSupplement">Private Address Supplement:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'privateAddressSupplement','errors')}">
                                    <input type="text" id="privateAddressSupplement" name="privateAddressSupplement" value="${fieldValue(bean:dtPersonInstance,field:'privateAddressSupplement')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email">Email:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'email','errors')}">
                                    <input type="text" id="email" name="email" value="${fieldValue(bean:dtPersonInstance,field:'email')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phone">Phone:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'phone','errors')}">
                                    <input type="text" id="phone" name="phone" value="${fieldValue(bean:dtPersonInstance,field:'phone')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fax">Fax:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'fax','errors')}">
                                    <input type="text" id="fax" name="fax" value="${fieldValue(bean:dtPersonInstance,field:'fax')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="preferredLanguage">Preferred Language:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'preferredLanguage','errors')}">
                                    <input type="text" id="preferredLanguage" name="preferredLanguage" value="${fieldValue(bean:dtPersonInstance,field:'preferredLanguage')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="trafficSignalValue">Traffic Signal Value:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'trafficSignalValue','errors')}">
                                    <input type="text" id="trafficSignalValue" name="trafficSignalValue" value="${fieldValue(bean:dtPersonInstance,field:'trafficSignalValue')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="company">Company:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'company','errors')}">
                                    <g:select optionKey="id" from="${org.opendtacho.domain.DtCompany.list()}" name="company.id" value="${dtPersonInstance?.company?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="subsidiary">Subsidiary:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'subsidiary','errors')}">
                                    <g:select optionKey="id" from="${org.opendtacho.domain.DtSubsidiary.list()}" name="subsidiary.id" value="${dtPersonInstance?.subsidiary?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="department">Department:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'department','errors')}">
                                    <g:select optionKey="id" from="${org.opendtacho.domain.DtDepartment.list()}" name="department.id" value="${dtPersonInstance?.department?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="users">Users:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'users','errors')}">
                                    
<ul>
<g:each var="u" in="${dtPersonInstance?.users?}">
    <li><g:link controller="dtUser" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="dtUser" params="['dtPerson.id':dtPersonInstance?.id]" action="create">Add DtUser</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="driver">Driver:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtPersonInstance,field:'driver','errors')}">
                                    <g:select optionKey="id" from="${org.opendtacho.domain.DtDriver.list()}" name="driver.id" value="${dtPersonInstance?.driver?.id}" noSelection="['null':'']"></g:select>
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
