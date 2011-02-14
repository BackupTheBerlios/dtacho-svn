
<%@ page import="org.opendtacho.domain.DtDriver" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>DtDriver List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New DtDriver</g:link></span>
        </div>
        <div class="body">
            <h1>DtDriver List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
				<th>Action</th>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Person</th>
                   	    
                   	        <th>Company</th>
                   	    
                   	        <g:sortableColumn property="cardHolderBirthDate" title="Card Holder Birth Date" />
                        
                   	        <g:sortableColumn property="cardHolderName_holderFirstNames" title="Card Holder Nameholder First Names" />
                        
                   	        <g:sortableColumn property="cardHolderName_holderSurname" title="Card Holder Nameholder Surname" />
                        
                   	        <g:sortableColumn property="cardHolderPreferredLanguage" title="Card Holder Preferred Language" />
                        
                   	        <g:sortableColumn property="drivingLicenceIssuingAuthority" title="Driving Licence Issuing Authority" />
                        
                   	        <g:sortableColumn property="drivingLicenceIssuingNation" title="Driving Licence Issuing Nation" />
                        
                   	        <g:sortableColumn property="drivingLicenceNumber" title="Driving Licence Number" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${dtDriverInstanceList}" status="i" var="dtDriverInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>
                                <g:link action="show" id="${dtDriverInstance?.id}">view</g:link> |
                                <g:link action="edit" id="${dtDriverInstance?.id}">edit</g:link> |
                                <g:link action="del" id="${dtDriverInstance?.id}" onclick="return confirm('Are you sure?');">del</g:link>
                            </td>

                        
                            <td><g:link action="show" id="${dtDriverInstance.id}">${dtDriverInstance.id?.encodeAsHTML()}</g:link></td>
                        
                            <td>${dtDriverInstance.person?.encodeAsHTML()}</td>
                        
                            <td>${dtDriverInstance.company?.encodeAsHTML()}</td>
                        
                            <td>${dtDriverInstance.cardHolderBirthDate?.encodeAsHTML()}</td>
                        
                            <td>${dtDriverInstance.cardHolderName_holderFirstNames?.encodeAsHTML()}</td>
                        
                            <td>${dtDriverInstance.cardHolderName_holderSurname?.encodeAsHTML()}</td>
                        
                            <td>${dtDriverInstance.cardHolderPreferredLanguage?.encodeAsHTML()}</td>
                        
                            <td>${dtDriverInstance.drivingLicenceIssuingAuthority?.encodeAsHTML()}</td>
                        
                            <td>${dtDriverInstance.drivingLicenceIssuingNation?.encodeAsHTML()}</td>
                        
                            <td>${dtDriverInstance.drivingLicenceNumber?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${DtDriver.count()}" />
            </div>
        </div>
    </body>
</html>

