<html>
  <head>
    <title><g:layoutTitle default="Hagos opendtacho" /></title>
    <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
    <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />

    %{-- what grails-ui components will be used--}%
    <gui:resources components="['menu']"/>

    <g:layoutHead />
    <g:javascript library="application" />
  </head>
  %{--onload attribute just for report.gsp, cause i can't call this attribut DIRECT from this page--}% 
  <body class='yui-skin-sam' onload="defaultUpdate();">
    <div id="spinner" class="spinner" style="display:none;">
      <img src="${resource(dir:'images',file:'spinner.gif')}" alt="Spinner" />
    </div>
    <div id="topbar">
      <g:render template="/common/topbar"/>
      <span id="loginLink">
          <g:isLoggedIn>
            <g:loggedInUsername/> (<g:link controller='logout'>Logout</g:link>)
          </g:isLoggedIn>
          <g:isNotLoggedIn>
            <g:link controller="login">Login</g:link>
          </g:isNotLoggedIn>
      </span>

    </div>
    <div id="header">
      <img src="${resource(dir:'images',file:'bannerWebsite.jpg')}" alt="banner website" />
    </div>

    <gui:menubar id="guiMenubar" renderTo="innerMenu">

      <gui:submenu label="${message(code:'menu.admin')}">
        <gui:menuitem url="${resource(dir:'/dtUser')}"><g:message code="menu.admin.user"/> </gui:menuitem>
        <gui:menuitem url="${resource(dir:'/dtRole')}"><g:message code="menu.admin.role"/> </gui:menuitem>
        <gui:menuitem url="${resource(dir:'/dtRequestMap')}"><g:message code="menu.admin.map"/> </gui:menuitem>
        <gui:menuitem url="${resource(dir:'/dtRequestMap/mod')}"><g:message code="authen.title"/> </gui:menuitem>
      </gui:submenu>

      <gui:submenu label="${message(code:'menu.list')}">
        <gui:menuitem url="${resource(dir:'/dtActivityChange/list')}"><g:message code="menu.list.activities"/> </gui:menuitem>
        <gui:menuitem url="${resource(dir:'/dtDriver/list')}"><g:message code="menu.list.driver"/></gui:menuitem>
      </gui:submenu>

      <gui:submenu label="${message(code:'menu.import')}">
        <gui:menuitem url="${resource(dir:'/dddFileUpload/dddUploadForm')}"><g:message code="menu.import.ddd"/></gui:menuitem>
        <gui:menuitem url="${resource(dir:'/xmlFileUpload/xmlUploadForm')}"><g:message code="menu.import.xml"/></gui:menuitem>
      </gui:submenu>

      <gui:submenu label="${message(code:'menu.export')}"/>

      <gui:submenu label="${message(code:'menu.management')}"/>

      <gui:submenu label="${message(code:'menu.control')}"/>
      
      <gui:submenu label="${message(code:'menu.report')}">
        <gui:menuitem url="${resource(dir:'/dtActivityChange/report')}"><g:message code="menu.report.activities"/></gui:menuitem>
        <gui:menuitem url="${resource(dir:'/dtActivityChange/report')}"><g:message code="menu.report.driver"/></gui:menuitem>
      </gui:submenu>
    </gui:menubar>

    <g:layoutBody/>
    <div id="footer">
      <g:render template="/common/footer"/>
    </div>
  </body>
</html>