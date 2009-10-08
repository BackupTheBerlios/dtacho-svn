<%--
  Created by IntelliJ IDEA.
  User: lha
  Date: Oct 8, 2009
  Time: 8:53:11 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>RESULTS</title>
    <meta name="layout" content="main"/>
  </head>
  <body>

    <div class="nav">
      <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
      <span class="menuButton"><g:link controller="report" action="index">another report</g:link></span>
    </div>

    <div class="body">
      <h1>Activity Protocol Driver</h1>

      <h3>Driver information</h3>
      <div class="list">
          <table>
              <thead>
                  <tr>
                        <g:sortableColumn property="company" title="Company"/>
                        <g:sortableColumn property="subsidiary" title="Subsidiary"/>
                        <g:sortableColumn property="driver" title="Driver"/>
                        <g:sortableColumn property="licence nr." title="Licence nr."/>
                        <g:sortableColumn property="card nr." title="Card nr."/>
                        <g:sortableColumn property="card exp." title="Card exp."/>
                        <g:sortableColumn property="peripod" title="Period"/>
                  </tr>
              </thead>
              <tbody>
                  <tr>
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

      <h3>Driving statistic</h3>
      <div class="list">
        <table>
          <thead>
            <tr>
              <g:sortableColumn property="none" title=" " />
              <g:sortableColumn property="number" title="number" />
              <g:sortableColumn property="duration" title="duration" />
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

      <h3>Driving activities</h3>
      <div class="list">
            <table>
                <thead>
                    <tr>
                        <g:sortableColumn property="date" title="Date" />
                        <g:sortableColumn property="day" title="Day" />
                        <g:sortableColumn property="type" title="Type" />
                        <g:sortableColumn property="begin" title="Begin" />
                        <g:sortableColumn property="end" title="End" />
                        <g:sortableColumn property="duration" title="Duration" />
                        <g:sortableColumn property="comment" title="Comment" />
                    </tr>
                </thead>
                <tbody>
                    <g:each var="entry" in="${entries}" status="i">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <g:if test="${entry.time=='00:00'}">
                                <td><strong>${entry.activityRecordDate}</strong></td>
                                <td><strong>${entry.activityRecordDay}</strong></td>
                            </g:if>
                            <g:else>
                                <td></td>
                                <td></td>
                            </g:else>

                            <td>
                                  <g:if test="${entry.activity=='RZ'}">
                                      <img src="${createLinkTo(dir:'images',file:'ruhe.png')}" alt="Ruhe" />
                                      RZ
                                  </g:if>
                                  <g:elseif test="${entry.activity=='BS'}">
                                      <img src="${createLinkTo(dir:'images',file:'bereitschaft.png')}" alt="Bereitschaft" />
                                      BS
                                  </g:elseif>
                                  <g:elseif test="${entry.activity=='AR'}">
                                      <img src="${createLinkTo(dir:'images',file:'arbeit.png')}" alt="Arbeit" />
                                      AR
                                  </g:elseif>
                                  <g:elseif test="${entry.activity=='LZ'}">
                                      <img src="${createLinkTo(dir:'images',file:'lenken.png')}" alt="Lenken" />
                                      LZ
                                  </g:elseif>
                            </td>

                            <td>${entry.time}</td>

                            <td>${entry.next}</td>

                            <td>${entry.duration}</td>

                            <td></td>
                         </tr>
                    </g:each>
                </tbody>
            </table>
          </div>
    </div>

  </body>
</html>