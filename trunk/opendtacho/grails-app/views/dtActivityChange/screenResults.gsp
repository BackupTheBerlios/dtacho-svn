<%--
  Created by IntelliJ IDEA.
  User: lha
  Date: Oct 14, 2009
  Time: 12:19:56 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title><g:message code="results.title"/></title>
    <meta name="layout" content="main"/>
    <style type="text/css">
      .list table{
        border-collapse: separate;
        border-style: solid;
        table-layout: fixed;
      }
      .list th, .list td{
        border-left:none;
      }
    </style>
  </head>
  <body>
    <div class="nav">
      <span class="menuButton"><a class="home" href="${resource(dir:'')}"><g:message code="menu.home"/></a></span>
    </div>
    <div class="body">
      %{--temporary date variable for pdf report--}%
      <g:set var="a" value="${new Date()}"/>
      <p style="text-align:right;font-style:italic;">${a.getDate()}.${a.getMonth()+1}.${a.getYear()+1900}</p>
      <p style="text-align:right;font-style:italic;"><g:loggedInUserInfo field="userRealName"/></p>
      <h1 style="text-align: center;"><g:message code="results.header"/></h1>

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
              <td>${foundDriver.cardHolderName_holderSurname}, ${foundDriver.cardHolderName_holderFirstNames}</td>
              <td>${foundDriver.drivingLicenceNumber}</td>
              <td>${cardNumber}</td>
              <td>${cardExp}</td>
              <td>${from} - ${to}</td>
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
                <img src="${createLinkTo(dir:'images',file:'ruhe.png')}" alt="Ruhe" />
                <strong>RZ</strong>
              </td>
              <td>${RZ}</td>
              <td>${RZDur}</td>
            </tr>
            <tr class='even'>
              <td>
                <img src="${createLinkTo(dir:'images',file:'arbeit.png')}" alt="Arbeit" />
                <strong>AR</strong>
              </td>
              <td>${AR}</td>
              <td>${ARDur}</td>
            </tr>
            <tr class='odd'>
              <td>
                <img src="${createLinkTo(dir:'images',file:'lenken.png')}" alt="Lenken" />
                <strong>LZ</strong>
              </td>
              <td>${LZ}</td>
              <td>${LZDur}</td>
            </tr>
            <tr class='even'>
              <td>
                <strong>sum</strong>
              </td>
              <td>${sum}</td>
              <td>${sumDur}</td>
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
              <td><strong><g:message code="results.activities.comment"/></strong></td>
            </tr>
          </thead>
          <tbody>
            <g:each var="entry" in="${entries}" status="i">
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
                    <img src="${createLinkTo(dir:'images',file:'ruhe.png')}" alt="Ruhe" />
                  </g:if>
                  <g:elseif test="${entry.activity=='BS'}">
                    <img src="${createLinkTo(dir:'images',file:'bereitschaft.png')}" alt="Bereitschaft" />
                  </g:elseif>
                  <g:elseif test="${entry.activity=='AR'}">
                    <img src="${createLinkTo(dir:'images',file:'arbeit.png')}" alt="Arbeit" />
                  </g:elseif>
                  <g:elseif test="${entry.activity=='LZ'}">
                    <img src="${createLinkTo(dir:'images',file:'lenken.png')}" alt="Lenken" />
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
        <table style="border-collapse:collapse;border-top:none;border-bottom:none;">
            <tr>
              <td></td>
              <td><strong><g:message code="results.activities.driverSum"/></strong></td>
              <td>
                <img src="${createLinkTo(dir:'images',file:'ruhe.png')}" alt="Ruhe" />
                <strong>RZ</strong>
              </td>
              <td><strong><g:message code="results.activities.number"/></strong></td>
              <td><strong>${RZ}</strong></td>
              <td></td>
              <td></td>
              <td><strong>${RZDur}</strong></td>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td></td>
              <td></td>
              <td>
                <img src="${createLinkTo(dir:'images',file:'arbeit.png')}" alt="Ruhe" />
                <strong>AR</strong>
              </td>
              <td><strong><g:message code="results.activities.number"/></strong></td>
              <td><strong>${AR}</strong></td>
              <td></td>
              <td><strong>${ARDur}</strong></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td></td>
              <td></td>
              <td>
                <img src="${createLinkTo(dir:'images',file:'lenken.png')}" alt="Ruhe" />
                <strong>LZ</strong>
              </td>
              <td><strong><g:message code="results.activities.number"/></strong></td>
              <td><strong>${LZ}</strong></td>
              <td><strong>${LZDur}</strong></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
        </table>
        <table style="border-collapse:collapse;border-top-width:2px;border-top-style:solid;">
          <tr>
            <td></td>
            <td></td>
            <td><strong><g:message code="results.activities.sum"/></strong></td>
            <td><strong><g:message code="results.activities.number"/></strong></td>
            <td><strong>${sum}</strong></td>
            <td><strong>${sumDur}</strong></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
        </table>
      </div>
      <br/>

      %{--hidden form, send params to pdfResults to create pdf file--}%
      <g:pdfForm name="screenResultsForm" controller="dtActivityChange" action="pdfResults" method="post" filename="report_for_${a.getDate()}.${a.getMonth()+1}.${a.getYear()+1900}.pdf">
      <div class="invi">
        <input type="text" name="driver" value="${foundDriver.cardHolderName_holderSurname}"/>
        <g:datePicker name="minDate" value="${minDate}"/>
        <g:datePicker name="maxDate" value="${maxDate}"/>
        <input type="text" name="shortcut" value="null"/>
      </div>
      <button type="submit">
        <img src="${createLinkTo(dir:'images/skin',file:'pdfReport.png')}" alt="pdf" />
        <g:message code="report.button.pdf"/>
      </button>
    </g:pdfForm>
      
    </div>
  </body>
</html>