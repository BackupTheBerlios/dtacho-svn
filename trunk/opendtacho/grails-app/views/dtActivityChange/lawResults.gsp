<%--
  Created by IntelliJ IDEA.
  User: ilia
  Date: 12/24/10
  Time: 8:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.opendtacho.domain.DtActivityChange" contentType="text/html;charset=UTF-8" %>
%{-- Style sheet for tooltip of grails-ui plugin--}%
<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'grails_ui.css')}"/>

%{--used component decleartion, here is tooltip--}%

<gui:resources components="['toolTip']"/>
<html>
 <head>
    <title>lawResults</title>
    <meta name="layout" content="main"/>
    <style type="text/css">
      .list table {
        border-collapse: separate;
        border-style: solid;
        table-layout: auto;
      }
      .list th, .list td {
        border-left:none;
      }
    </style>

     <resource:tabView/>
     <resource:checkedTreeView/>
     <resource:accordion skin="default" />
  </head>
  <body>
<a href="${resource(dir:'/dtActivityChange/reportLaw')}">Back<br></a>
<g:if test="${driversList.size() == 0 | dates.size() == 0}">
    No driver selected
</g:if>
<g:else>
<div class align="list">

<richui:tabView id="tabView">
    <richui:tabLabels>
        <g:each in="${driversList}" var="driversList">
        <richui:tabLabel selected="true" title = "${driversList.cardHolderName_holderSurname?.encodeAsHTML()} " />
                              %{--  ${driversList.cardHolderName_holderFirstNames?.encodeAsHTML()}
                                 ${driversList.drivingLicenceNumber?.encodeAsHTML()}"/> --}%
        </g:each>
    </richui:tabLabels>

<richui:tabContents>

    <g:each in="${driversList}" var="driversList" status = "i" >
    <richui:tabContent>
    <g:if test="${dates.get(i).size() == 0}">
          No activities found for these dates
    </g:if>
    <g:else>
    <g:each in = "${laws}" var = "law" status = "g">
               <table>
                  <g:each in="${law.checkLaw(dates.get(i))}" var="checkLawList" status="j">
                    <tr class="${(j % 2) == 0 ? 'even' : 'odd'}">
                      <g:each in="${checkLawList}" var = "reportDay">
                          <td>${reportDay?.encodeAsHTML()}</td>
                      </g:each>
                      </tr>
                  </g:each>
                </table>
     </g:each>
     </g:else>
 </richui:tabContent>
     </g:each>

</richui:tabContents>
</richui:tabView>

 </div>
</g:else>
  </body>
</html>