<%--
  Created by IntelliJ IDEA.
  User: lha
  Date: Oct 14, 2009
  Time: 12:19:56 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
%{-- Style sheet for tooltip of grails-ui plugin--}%
<link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css',file:'grails_ui.css')}"/>

%{--used component decleartion, here is tooltip--}%
<gui:resources components="['toolTip']"/>
<html>
  <head>
    <title><g:message code="results.title"/></title>
    <meta name="layout" content="main"/>
    <style type="text/css">
      .list table{
        border-collapse: separate;
        border-style: solid;
        table-layout: auto;
      }
      .list th, .list td{
        border-left:none;
      }
    </style>
    <script type="text/javascript">
      function takeThis(obj){
        document.getElementById("temp").innerHTML=obj.innerHTML;
        document.getElementById("temp").style.visibility="visible";
      }
    </script>
  </head>
  <body>
    <div class="body">
      <a name="top"></a>
      <g:set var="a" value="${new Date()}"/>

      %{--hidden form, send params to pdfResults to create pdf file--}%
      <g:pdfForm name="screenResultsForm" controller="dtActivityChange" action="pdfResults" method="post" filename="report_for_${a.getDate()}.${a.getMonth()+1}.${a.getYear()+1900}.pdf">
        <div class="invi">
          <g:each in="${drivers}" var="driverId">
            <input type="checkbox" name="drivers" value="${driverId}" checked/>
          </g:each>
          <g:datePicker name="minDate" value="${minDate}"/>
          <g:datePicker name="maxDate" value="${maxDate}"/>
        </div>
        <button type="submit">
          <img src="${resource(dir:'images/skin',file:'pdfReport.png')}" alt="pdf" />
          <g:message code="report.button.pdf"/>
        </button>
      </g:pdfForm>

      %{-- navigation in result page --}%
      <div style="text-align:right">
        <input type="button" value="Off" onclick="document.getElementById('temp').style.visibility='hidden';"/>
        <g:each in="${resultList}">
          <input type="button" value="${it.driver.lastName}" onclick="takeThis(document.getElementById('${it.driver.lastName}'));"
        </g:each>
      </div>
      %{--temporary date variable for pdf report--}%
      <p style="text-align:right;font-style:italic;">${a.getDate()}.${a.getMonth()+1}.${a.getYear()+1900} ${a.getHours()}:${a.getMinutes()}</p>
      <p style="text-align:right;font-style:italic;"><g:loggedInUserInfo field="userRealName"/></p>
      <h1 style="text-align: center;"><g:message code="results.header"/></h1>

      %{-- temporary div container for result--}%
      <div id="temp" style="visibility:hidden;"></div>
      <img src="${resource(dir:'images/skin',file:'top.png')}" alt="top" onclick="window.location.hash='top'"/>

      %{--each object in array resultList is a complete result--}%
      <g:each in="${resultList}" var="result">
        <div id="${result.driver.lastName}" style="visibility:hidden;">

          <h3><g:message code="results.infos"/></h3>
          <div class="list">
            <table>
              <thead>
                <tr>
                  <td><strong><g:message code="results.infos.company"/></strong></td>
                  <td><strong><g:message code="results.infos.subsidiary"/></strong></td>
                  <td><strong><g:message code="results.infos.driver"/></strong></td>
                  <td><strong><g:message code="results.infos.licence"/></strong></td>
                  <td><strong><g:message code="results.infos.cardNr"/></strong></td>
                  <td><strong><g:message code="results.infos.cardExp"/></strong></td>
                  <td><strong><g:message code="results.infos.period"/></strong></td>
                </tr>
              </thead>
              <tbody>
                <tr class="odd">
                  <td></td>
                  <td></td>
                  <td><strong>${result.driver.lastName}, ${result.driver.firstName}</strong></td>
                  <td>${result.driver.licenceNr}</td>
                  <td>${result.card.cardNr}</td>
                  <td>${result.card.cardExp}</td>
                  <td>${result.from} - ${result.to}</td>
                </tr>
              </tbody>
            </table>
          </div>
          <br/>

          <h3><g:message code="results.statistic"/></h3>
          <div class="list">
            <table>
              <thead>
                <tr>
                  <td> </td>
                  <td><strong><g:message code="results.statistic.number"/></strong></td>
                  <td><strong><g:message code="results.statistic.duration"/></strong></td>
                </tr>
              </thead>
              <tbody>
                <tr class='odd'>
                  <td>
                    <gui:toolTip text="Ruhe">
                      <img src="${resource(dir:'images',file:'ruhe.png')}" alt="Ruhe" />
                      <strong>RZ</strong>
                    </gui:toolTip>
                  </td>
                  <td>${result.statistic.RZ}</td>
                  <td>${result.statistic.RZDur}</td>
                </tr>
                <tr class='even'>
                  <td>
                    <gui:toolTip text="Arbeit">
                      <img src="${resource(dir:'images',file:'arbeit.png')}" alt="Arbeit" />
                      <strong>AR</strong>
                    </gui:toolTip>
                  </td>
                  <td>${result.statistic.AR}</td>
                  <td>${result.statistic.ARDur}</td>
                </tr>
                <tr class='odd'>
                  <td>
                    <gui:toolTip text="Lenken">
                      <img src="${resource(dir:'images',file:'lenken.png')}" alt="Lenken" />
                      <strong>LZ</strong>
                    </gui:toolTip>
                  </td>
                  <td>${result.statistic.LZ}</td>
                  <td>${result.statistic.LZDur}</td>
                </tr>
                <tr class='even'>
                  <td>
                    <strong>sum</strong>
                  </td>
                  <td>${result.statistic.sum}</td>
                  <td>${result.statistic.sumDur}</td>
                </tr>
              </tbody>
            </table>
          </div>
          <br/>

          <h3><g:message code="results.activities"/></h3>
          <div class="list">
            <table>
            <thead>
              <tr>
                <td><strong><g:message code="results.activities.date"/></strong></td>
                <td><strong><g:message code="results.activities.day"/></strong></td>
                <td><strong><g:message code="results.activities.type"/></strong></td>
                <td><strong><g:message code="results.activities.start"/></strong></td>
                <td><strong><g:message code="results.activities.end"/></strong></td>
                <td><strong>LZ</strong></td>
                <td><strong>AR</strong></td>
                <td><strong>RZ</strong></td>
                <td><strong>BS</strong></td>
                <td style="width:60%"><strong><g:message code="results.activities.comment"/></strong></td>
              </tr>
            </thead>
            <tbody>
              <g:each var="entry" in="${result.activities}" status="i">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                  <g:if test="${entry.time=='00:00'}">
                    <td><strong>${entry.activityRecordDate}</strong></td>
                    <g:if test="${entry.activityRecordDay==0}">
                      <td><strong><g:message code="results.activities.day.mo"/></strong></td>
                    </g:if>
                    <g:elseif test="${entry.activityRecordDay==1}">
                      <td><strong><g:message code="results.activities.day.tu"/></strong></td>
                    </g:elseif>
                    <g:elseif test="${entry.activityRecordDay==2}">
                      <td><strong><g:message code="results.activities.day.we"/></strong></td>
                    </g:elseif>
                    <g:elseif test="${entry.activityRecordDay==3}">
                      <td><strong><g:message code="results.activities.day.th"/></strong></td>
                    </g:elseif>
                    <g:elseif test="${entry.activityRecordDay==4}">
                      <td><strong><g:message code="results.activities.day.fr"/></strong></td>
                    </g:elseif>
                    <g:elseif test="${entry.activityRecordDay==5}">
                      <td><strong><g:message code="results.activities.day.sa"/></strong></td>
                    </g:elseif>
                    <g:elseif test="${entry.activityRecordDay==6}">
                      <td><strong><g:message code="results.activities.day.so"/></strong></td>
                    </g:elseif>
                  </g:if>
                  <g:else>
                    <td></td>
                    <td></td>
                  </g:else>

                  <td>
                    <g:if test="${entry.activity=='RZ'}">
                      <gui:toolTip text="Ruhe">
                        <img src="${resource(dir:'images',file:'ruhe.png')}" alt="Ruhe" />
                      </gui:toolTip>
                    </g:if>
                    <g:elseif test="${entry.activity=='BS'}">
                      <gui:toolTip text="Bereitschaft">
                        <img src="${resource(dir:'images',file:'bereitschaft.png')}" alt="Bereitschaft" />
                      </gui:toolTip>
                    </g:elseif>
                    <g:elseif test="${entry.activity=='AR'}">
                      <gui:toolTip text="Arbeit">
                        <img src="${resource(dir:'images',file:'arbeit.png')}" alt="Arbeit" />
                      </gui:toolTip>
                    </g:elseif>
                    <g:elseif test="${entry.activity=='LZ'}">
                      <gui:toolTip text="Lenken">
                        <img src="${resource(dir:'images',file:'lenken.png')}" alt="Lenken" />
                      </gui:toolTip>
                    </g:elseif>
                  </td>

                  <td>${entry.time}</td>

                  <td>${entry.next}</td>

                  <g:if test="${entry.activity=='LZ'}">
                    <td>${entry.duration}</td>
                  </g:if>
                  <g:else>
                    <td></td>
                  </g:else>

                  <g:if test="${entry.activity=='AR'}">
                    <td>${entry.duration}</td>
                  </g:if>
                  <g:else>
                    <td></td>
                  </g:else>

                  <g:if test="${entry.activity=='RZ'}">
                    <td>${entry.duration}</td>
                  </g:if>
                  <g:else>
                    <td></td>
                  </g:else>

                  <g:if test="${entry.activity=='BS'}">
                    <td>${entry.duration}</td>
                  </g:if>
                  <g:else>
                    <td></td>
                  </g:else>

                  <td></td>
                </tr>
              </g:each>
            </tbody>
          </table>
            %{--"Footer" for this activities list--}%
            <table style="border-collapse:collapse;border-top:none;border-bottom:none;table-layout:fixed;">
              <tr>
                <td><strong><g:message code="results.activities.driverSum"/></strong></td>
                <td>
                  <gui:toolTip text="Ruhe">
                    <img src="${resource(dir:'images',file:'ruhe.png')}" alt="Ruhe" />
                    <strong>RZ</strong>
                  </gui:toolTip>
                </td>
                <td><strong><g:message code="results.activities.number"/></strong></td>
                <td><strong>${result.statistic.RZ}</strong></td>
                <td><strong>${result.statistic.RZDur}</strong></td>
              </tr>
              <tr>
                <td></td>
                <td>
                  <gui:toolTip text="Arbeit">
                    <img src="${resource(dir:'images',file:'arbeit.png')}" alt="Ruhe" />
                    <strong>AR</strong>
                  </gui:toolTip>
                </td>
                <td><strong><g:message code="results.activities.number"/></strong></td>
                <td><strong>${result.statistic.AR}</strong></td>
                <td><strong>${result.statistic.ARDur}</strong></td>
              </tr>
              <tr>
                <td></td>
                <td>
                  <gui:toolTip text="Lenken">
                    <img src="${resource(dir:'images',file:'lenken.png')}" alt="Ruhe" />
                    <strong>LZ</strong>
                  </gui:toolTip>
                </td>
                <td><strong><g:message code="results.activities.number"/></strong></td>
                <td><strong>${result.statistic.LZ}</strong></td>
                <td><strong>${result.statistic.LZDur}</strong></td>
              </tr>
          </table>
            <table style="border-collapse:collapse;border-top-width:2px;border-top-style:solid;table-layout:fixed;">
            <tr bgcolor="#A9A9A9">
              <td><strong><g:message code="results.activities.sum"/></strong></td>
              <td><strong>All</strong></td>
              <td><strong><g:message code="results.activities.number"/></strong></td>
              <td><strong>${result.statistic.sum}</strong></td>
              <td><strong>${result.statistic.sumDur}</strong></td>
            </tr>
          </table>
          </div>
          <br/>

        </div>
      </g:each>



    </div>
  </body>
</html>