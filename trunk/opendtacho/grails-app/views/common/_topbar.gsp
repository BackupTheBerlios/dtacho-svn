<%--
  Created by IntelliJ IDEA.
  User: lha
  Date: Oct 11, 2009
  Time: 1:32:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

%{-- Style sheet for tooltip of grails-ui plugin--}%
<style type="text/css">
  .yui-tt {
    background-color:#FFEE69;
    border-color:#D4C237 #A6982B #A6982B;
    border-style:solid;
    border-width:1px;
    color:#000000;
    left:0;
    padding:2px 5px;
    position:relative;
    width: 100px;
    top:0;
    z-index:1;
  }
  .yui-cms-tt .yui-panel {
    text-align: left;
  }
  .yui-panel-container.yui-cms-tt.shadow .underlay {
    top: 2px;
    bottom: -2px;
    right: -2px;
    left: -2px;
    padding-left: 4px;
  }
  .yui-cms-tt .yui-panel, .yui-cms-tt .yui-panel .bd {
    color:#333;
    background-color:#FDFFB4;
    border-color:#FCC90D;
  }
  .yui-cms-tt .yui-panel .hd {
    visible: hidden;
    display: none;
  }
</style>

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