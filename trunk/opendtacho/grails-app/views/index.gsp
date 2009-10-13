<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Hagos opendtacho</title>
		<meta name="layout" content="main" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    </head>
    <body>
        <div class="nav">
          <span class="menuButton"><a class="home" href="${resource(dir:'')}"><g:message code="menu.home"/></a></span>
          <span class="menuButton"><a class="activitiesList" href="${resource(dir:'/dtActivityChange/list')}"><g:message code="menu.activities"/></a></span>
          <span class="menuButton"><a class="driverList" href="${resource(dir:'/dtDriver/list')}"><g:message code="menu.driver"/></a></span>
          <span class="menuButton"><a class="xmlUpload" href="${resource(dir:'/xmlFileUpload/xmlUploadForm')}"><g:message code="menu.xml"/></a></span>
          <span class="menuButton"><a class="dddUpload" href="${resource(dir:'/dddFileUpload/dddUploadForm')}"><g:message code="menu.ddd"/></a></span>
          <span class="menuButton"><a class="report" href="${resource(dir:'/dtActivityChange/report')}"><g:message code="menu.report"/></a></span>
        </div>
    </body>
</html>