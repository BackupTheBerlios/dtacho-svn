<%--
  Created by IntelliJ IDEA.
  User: lha
  Date: Oct 8, 2009
  Time: 8:52:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.opendtacho.domain.DtCompany; org.opendtacho.domain.DtSubsidiary; org.opendtacho.domain.DtDriver" contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <title><g:message code="report.title"/></title>
  </head>
  <body>
    <div class="nav">
      <span class="menuButton"><a class="home" href="${resource(dir:'')}"><g:message code="menu.home"/></a></span>
    </div>
    <div class="body">
      <h1><g:message code="report.title"/></h1>
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      %{--temporary date variable for pdf report filename--}%
      <g:set var="a" value="${new Date()}"/>
      <g:pdfForm controller="dtActivityChange" action="pdfResults" method="post" filename="report_for_${a.getDate()}.${a.getMonth()+1}.${a.getYear()+1900}.pdf">
        <div class="dialog">
          <table>
            <tbody>
              <tr class="prop">
                <td valign="top" class="name">
                  <strong><g:message code="report.company"/></strong>
                </td>
                <td>
                  <g:select name="company" optionKey="companyName" optionValue="companyName" from="${DtCompany.list()}"/>
                </td>
              </tr>
              <tr class="prop">
                <td valign="top" class="name">
                  <strong><g:message code="report.subsidiary"/></strong>
                </td>
                <td>
                  <g:select name="city" optionKey="city" optionValue="city" from="${DtSubsidiary.list()}"/>
                </td>
              </tr>
              <tr class="prop">
                <td valign="top" class="name">
                  <strong><g:message code="report.driver"/></strong>
                </td>
                <td>
                  <g:select name="driver" optionKey="cardHolderName_holderSurname" optionValue="cardHolderName_holderSurname" from="${DtDriver.list()}"/>
                </td>
              </tr>
              <tr class="prop">
                <td valign="top" class="name">
                  <strong><g:message code="report.date"/></strong>
                </td>
                <td valign="top" class="value">
                  <g:message code="report.date.from"/>
                  <g:datePicker name="minDate" precision="day"/>
                  <g:message code="report.date.to"/>
                  <g:datePicker name="maxDate" precision="day"/>
                </td>
              </tr>
              <tr class="prop">
                <td valign="top" class="name">
                  <strong><g:message code="report.shortcut"/></strong>
                </td>
                <td valign="top" class="value">
                  <g:radio name='shortcut' value="null"/><em><g:message code="report.shortcut.none"/></em><br/>
                  <g:radio name='shortcut' value="lastWeek"/><em><g:message code="report.shortcut.lastWeek"/></em><br/>
                  <g:radio name='shortcut' value="thisMonth"/><em><g:message code="report.shortcut.thisMonth"/></em><br/>
                  <g:radio name='shortcut' value="lastMonth"/><em><g:message code="report.shortcut.lastMonth"/></em><br/>
                  <g:radio name='shortcut' value="thisYear"/><em><g:message code="report.shortcut.thisYear"/></em>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="buttons">
          <span class="button">
            <input class="pdf" type="image" alt="<g:message code="report.button.pdf"/>" />
          </span>
          <span class="button">
            <input class="screen" type="button" value="<g:message code="report.button.screen"/>" onClick="document.simplePdfForm.action='screenResults'; document.simplePdfForm.submit();"/>  
          </span>
        </div>
      </g:pdfForm>
    </div>
  </body>
</html>
