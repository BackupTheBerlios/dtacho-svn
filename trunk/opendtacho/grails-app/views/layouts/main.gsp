<html>
  <head>
    <title><g:layoutTitle default="Hagos opendtacho" /></title>
    <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
    <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
    <g:layoutHead />
    <g:javascript library="application" />

  </head>
  <body>
    <div id="spinner" class="spinner" style="display:none;">
      <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
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
      <img src="${createLinkTo(dir:'images',file:'bannerWebsite.jpg')}" alt="banner website" />
    </div>
    <g:layoutBody/>
    <div id="footer">
      <g:render template="/common/footer"/>
    </div>
  </body>
</html>