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
    <script type="text/javascript">
      //Yester calculation function
      function yesterday(a){
        a.setDate(a.getDate()-1);
        return a;
      }
      //First day of this week calculation function
      function positionMonday(a){
        //as long as a isn't a monday
        while(a.getDay()!=1){
          a = yesterday(a);
        }
        return a;
      }

      function defaultUpdate(){
        var today = new Date();
        var firstDayOfThisWeek = positionMonday(today);
        //alert(firstDayOfThisWeek.getDay()+"_"+firstDayOfThisWeek.getDate()+"."+firstDayOfThisWeek.getMonth());

        var lastDayOfLastWeek = yesterday(firstDayOfThisWeek);
        //alert(lastDayOfLastWeek.getDay()+"_"+lastDayOfLastWeek.getDate()+"."+lastDayOfLastWeek.getMonth());
        document.getElementById("maxDate_year").value = lastDayOfLastWeek.getFullYear();
        document.getElementById("maxDate_month").value = lastDayOfLastWeek.getMonth()+1;
        document.getElementById("maxDate_day").value = lastDayOfLastWeek.getDate();

        var firstDayOfLastWeek = new Date();
        for(var x=1;x<=6;x++){
          firstDayOfLastWeek = yesterday(lastDayOfLastWeek);
        }
        //alert(firstDayOfLastWeek.getDay()+"_"+firstDayOfLastWeek.getDate()+"."+firstDayOfLastWeek.getMonth());
        document.getElementById("minDate_year").value = firstDayOfLastWeek.getFullYear();
        document.getElementById("minDate_month").value = firstDayOfLastWeek.getMonth()+1;
        document.getElementById("minDate_day").value = firstDayOfLastWeek.getDate();
      }
      function thisMonthUpdate(){
        var today = new Date();

        document.getElementById("minDate_year").value = today.getFullYear();
        document.getElementById("minDate_month").value = today.getMonth()+1;
        document.getElementById("minDate_day").value = 1;

        document.getElementById("maxDate_year").value = today.getFullYear();
        document.getElementById("maxDate_month").value = today.getMonth()+1;
        document.getElementById("maxDate_day").value = today.getDate();
      }
      function lastMonthUpdate(){
        var today = new Date();
        document.getElementById("minDate_year").value = today.getFullYear();
        document.getElementById("minDate_month").value = today.getMonth();
        document.getElementById("minDate_day").value = 1;

        var firstDayOfThisMonth = new Date();
        firstDayOfThisMonth.setDate(1);
        //alert(firstDayOfThisMonth.getDay()+"_"+firstDayOfThisMonth.getDate()+"."+firstDayOfThisMonth.getMonth());
        var lastDayOfLastMonth = yesterday(firstDayOfThisMonth);
        //alert(lastDayOfLastMonth.getDay()+"_"+lastDayOfLastMonth.getDate()+"."+lastDayOfLastMonth.getMonth());

        document.getElementById("maxDate_year").value = lastDayOfLastMonth.getFullYear();
        document.getElementById("maxDate_month").value = lastDayOfLastMonth.getMonth()+1;
        document.getElementById("maxDate_day").value = lastDayOfLastMonth.getDate();
      }
      function thisYearUpdate(){
        var today = new Date();

        document.getElementById("minDate_year").value = today.getFullYear();
        document.getElementById("minDate_month").value = 1;
        document.getElementById("minDate_day").value = 1;

        document.getElementById("maxDate_year").value = today.getFullYear();
        document.getElementById("maxDate_month").value = today.getMonth()+1;
        document.getElementById("maxDate_day").value = today.getDate();
      }

      function selectAll(){
        var drivers = document.getElementsByName('drivers');
        for(var x=0;x<drivers.length;x++) drivers[x].checked=true;
      }
      function unselectAll(){
        var drivers = document.getElementsByName('drivers');
        for(var y=0;y<drivers.length;y++) drivers[y].checked=false;
      }

    </script>
  </head>
  <body>
    <div class="body">
      <h1><g:message code="report.title"/></h1>

      %{--matched company and subsidiary to the logged in user--}%
      <g:set var ="company" value="${currentCompany}"/>
      <g:set var ="subsidiary" value="${currentSubsidiary}"/> 
      
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
                  <input type="button" value="${message(code:'report.driver.selectAll')}" onclick="selectAll();"/>
                  <input type="button" value="${message(code:'report.driver.unselectAll')}" onclick="unselectAll();"/>
                  <br/>

                  <g:each in="${DtDriver.list()}">
                    <input type="checkbox" id="drivers" name="drivers" value="${it.id}"/>
                    ${it.cardHolderName_holderSurname}, ${it.cardHolderName_holderFirstNames}
                    <br/>
                  </g:each>

                </td>
              </tr>
              <tr class="prop">
                <td valign="top" class="name">
                  <strong><g:message code="report.date"/></strong>
                </td>
                <td valign="top" class="value">
                  <g:message code="report.date.from"/>
                  <g:datePicker id="minDate" name="minDate" precision="day"/>
                  <g:message code="report.date.to"/>
                  <g:datePicker id="maxDate" name="maxDate" precision="day"/>
                </td>
              </tr>
              <tr class="prop">
                <td valign="top" class="name">
                  <strong><g:message code="report.shortcut"/></strong>
                </td>
                <td valign="top" class="value">
                  <g:radio name='shortcut' value="default" onclick="defaultUpdate();" checked="checked"/><em><g:message code="report.shortcut.default"/></em><br/>
                  <g:radio name='shortcut' value="thisMonth" onclick="thisMonthUpdate();"/><em><g:message code="report.shortcut.thisMonth"/></em><br/>
                  <g:radio name='shortcut' value="lastMonth" onclick="lastMonthUpdate();"/><em><g:message code="report.shortcut.lastMonth"/></em><br/>
                  <g:radio name='shortcut' value="thisYear" onclick="thisYearUpdate();"/><em><g:message code="report.shortcut.thisYear"/></em>
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
