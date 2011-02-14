
<%@ page import="org.opendtacho.domain.DtDriver" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit DtDriver</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">DtDriver List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New DtDriver</g:link></span>
        </div>
        <div class="body">
            <h1>Edit DtDriver</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${dtDriverInstance}">
            <div class="errors">
                <g:renderErrors bean="${dtDriverInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${dtDriverInstance?.id}" />
                <input type="hidden" name="version" value="${dtDriverInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="person">Person:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtDriverInstance,field:'person','errors')}">
                                    <g:select optionKey="id" from="${org.opendtacho.domain.DtPerson.list()}" name="person.id" value="${dtDriverInstance?.person?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="company">Company:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtDriverInstance,field:'company','errors')}">
                                    <g:select optionKey="id" from="${org.opendtacho.domain.DtCompany.list()}" name="company.id" value="${dtDriverInstance?.company?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            %{--<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activityChanges">Activity Changes:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtDriverInstance,field:'activityChanges','errors')}">
                                    
<ul>
<g:each var="a" in="${dtDriverInstance?.activityChanges?}">
    <li><g:link controller="dtActivityChange" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="dtActivityChange" params="['dtDriver.id':dtDriverInstance?.id]" action="create">Add DtActivityChange</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="card2drivers">Card2drivers:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtDriverInstance,field:'card2drivers','errors')}">
                                    
<ul>
<g:each var="c" in="${dtDriverInstance?.card2drivers?}">
    <li><g:link controller="dtCard2driver" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="dtCard2driver" params="['dtDriver.id':dtDriverInstance?.id]" action="create">Add DtCard2driver</g:link>

                                </td>
                            </tr> --}%
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cardHolderBirthDate">Card Holder Birth Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtDriverInstance,field:'cardHolderBirthDate','errors')}">
                                    <g:datePicker name="cardHolderBirthDate" value="${dtDriverInstance?.cardHolderBirthDate}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cardHolderName_holderFirstNames">Card Holder Nameholder First Names:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtDriverInstance,field:'cardHolderName_holderFirstNames','errors')}">
                                    <input type="text" id="cardHolderName_holderFirstNames" name="cardHolderName_holderFirstNames" value="${fieldValue(bean:dtDriverInstance,field:'cardHolderName_holderFirstNames')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cardHolderName_holderSurname">Card Holder Nameholder Surname:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtDriverInstance,field:'cardHolderName_holderSurname','errors')}">
                                    <input type="text" id="cardHolderName_holderSurname" name="cardHolderName_holderSurname" value="${fieldValue(bean:dtDriverInstance,field:'cardHolderName_holderSurname')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cardHolderPreferredLanguage">Card Holder Preferred Language:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtDriverInstance,field:'cardHolderPreferredLanguage','errors')}">
                                    <input type="text" id="cardHolderPreferredLanguage" name="cardHolderPreferredLanguage" value="${fieldValue(bean:dtDriverInstance,field:'cardHolderPreferredLanguage')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="drivingLicenceIssuingAuthority">Driving Licence Issuing Authority:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtDriverInstance,field:'drivingLicenceIssuingAuthority','errors')}">
                                    <input type="text" id="drivingLicenceIssuingAuthority" name="drivingLicenceIssuingAuthority" value="${fieldValue(bean:dtDriverInstance,field:'drivingLicenceIssuingAuthority')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="drivingLicenceIssuingNation">Driving Licence Issuing Nation:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtDriverInstance,field:'drivingLicenceIssuingNation','errors')}">
                                    <input type="text" id="drivingLicenceIssuingNation" name="drivingLicenceIssuingNation" value="${fieldValue(bean:dtDriverInstance,field:'drivingLicenceIssuingNation')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="drivingLicenceNumber">Driving Licence Number:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtDriverInstance,field:'drivingLicenceNumber','errors')}">
                                    <input type="text" id="drivingLicenceNumber" name="drivingLicenceNumber" value="${fieldValue(bean:dtDriverInstance,field:'drivingLicenceNumber')}"/>
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
