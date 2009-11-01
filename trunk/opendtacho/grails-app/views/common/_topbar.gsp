<%--
  Created by IntelliJ IDEA.
  User: lha
  Date: Oct 11, 2009
  Time: 1:32:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

%{-- Style sheet for tooltip of grails-ui plugin--}%
<link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css',file:'grails_ui.css')}"/>

%{--used component decleartion, here is tooltip--}%
<gui:resources components="['toolTip']"/>

<div id="menu">
  <nobr>
    <gui:toolTip text="deutsch">
      <g:link controller="${params?.controller}" action="${params?.action}" params="[lang:'de']">
        <img src="${createLinkTo(dir:'images',file:'de.png')}" alt="de" />
      </g:link>
    </gui:toolTip> |
    <gui:toolTip text="english">
      <g:link controller="${params?.controller}" action="${params?.action}" params="[lang:'en']">
        <img src="${createLinkTo(dir:'images',file:'en.png')}" alt="en" />
      </g:link>
    </gui:toolTip> |
    <gui:toolTip text="fran&ccedil;ais">
      <g:link controller="${params?.controller}" action="${params?.action}" params="[lang:'fr']">
        <img src="${createLinkTo(dir:'images',file:'fr.png')}" alt="fr" />
      </g:link>
    </gui:toolTip>
  </nobr>
</div>