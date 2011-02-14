
<%@ page import="org.opendtacho.domain.DtDriver" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>DtDriver List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
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
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Person</th>
                   	    
                   	        <th>Company</th>
                   	    
                   	        <g:sortableColumn property="cardHolderBirthDate" title="Card Holder Birth Date" />
                        
                   	        <g:sortableColumn property="cardHolderName_holderFirstNames" title="Card Holder Nameholder First Names" />
                        
                   	        <g:sortableColumn property="cardHolderName_holderSurname" title="Card Holder Nameholder Surname" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${dtDriverInstanceList}" status="i" var="dtDriverInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${dtDriverInstance.id}">${fieldValue(bean:dtDriverInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:dtDriverInstance, field:'person')}</td>
                        
                            <td>${fieldValue(bean:dtDriverInstance, field:'company')}</td>
                        
                            <td>${fieldValue(bean:dtDriverInstance, field:'cardHolderBirthDate')}</td>
                        
                            <td>${fieldValue(bean:dtDriverInstance, field:'cardHolderName_holderFirstNames')}</td>
                        
                            <td>${fieldValue(bean:dtDriverInstance, field:'cardHolderName_holderSurname')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${dtDriverInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
