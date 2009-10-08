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

        <div id="header">
            <h1>Hagos opendtacho</h1>
        </div>

        <g:layoutBody/>

        <div id="footer">
            <g:render template="/common/footer"/>
        </div>
    </body>
</html>