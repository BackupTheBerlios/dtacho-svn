
<%@ page import="org.opendtacho.domain.DtCompany" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create DtCompany</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">DtCompany List</g:link></span>
        </div>
        <div class="body">
            <h1>Create DtCompany</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${dtCompanyInstance}">
            <div class="errors">
                <g:renderErrors bean="${dtCompanyInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="companyName">Company Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtCompanyInstance,field:'companyName','errors')}">
                                    <input type="text" id="companyName" name="companyName" value="${fieldValue(bean:dtCompanyInstance,field:'companyName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="companyAddress">Company Address:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtCompanyInstance,field:'companyAddress','errors')}">
                                    <input type="text" id="companyAddress" name="companyAddress" value="${fieldValue(bean:dtCompanyInstance,field:'companyAddress')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name1">Name1:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtCompanyInstance,field:'name1','errors')}">
                                    <input type="text" id="name1" name="name1" value="${fieldValue(bean:dtCompanyInstance,field:'name1')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name2">Name2:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtCompanyInstance,field:'name2','errors')}">
                                    <input type="text" id="name2" name="name2" value="${fieldValue(bean:dtCompanyInstance,field:'name2')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name3">Name3:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtCompanyInstance,field:'name3','errors')}">
                                    <input type="text" id="name3" name="name3" value="${fieldValue(bean:dtCompanyInstance,field:'name3')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="zipcode">Zipcode:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtCompanyInstance,field:'zipcode','errors')}">
                                    <input type="text" id="zipcode" name="zipcode" value="${fieldValue(bean:dtCompanyInstance,field:'zipcode')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="city">City:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtCompanyInstance,field:'city','errors')}">
                                    <input type="text" id="city" name="city" value="${fieldValue(bean:dtCompanyInstance,field:'city')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="street">Street:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtCompanyInstance,field:'street','errors')}">
                                    <input type="text" id="street" name="street" value="${fieldValue(bean:dtCompanyInstance,field:'street')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="housenumber">Housenumber:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtCompanyInstance,field:'housenumber','errors')}">
                                    <input type="text" id="housenumber" name="housenumber" value="${fieldValue(bean:dtCompanyInstance,field:'housenumber')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="addressSupplement">Address Supplement:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:dtCompanyInstance,field:'addressSupplement','errors')}">
                                    <input type="text" id="addressSupplement" name="addressSupplement" value="${fieldValue(bean:dtCompanyInstance,field:'addressSupplement')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
