<%--
  Created by IntelliJ IDEA.
  User: lha
  Date: Oct 8, 2009
  Time: 8:52:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>REPORT</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
        </div>
        <div class="body">
            <h1>Report</h1>

            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <g:form action="results" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="company"><strong>Company</strong></label>
                                </td>
                                <td>
                                    <g:select name="company" optionKey="companyName" optionValue="companyName" from="${DtCompany.list()}"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="subsidiary"><strong>Subsidiary</strong></label>
                                </td>
                                <td>
                                    <g:select name="city" optionKey="city" optionValue="city" from="${DtSubsidiary.list()}"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="driver"><strong>Driver</strong></label>
                                </td>
                                <td>
                                    <g:select name="driver" optionKey="cardHolderName_holderSurname" optionValue="cardHolderName_holderSurname" from="${DtDriver.list()}"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="date"><strong>Date</strong></label>
                                </td>
                                <td valign="top" class="value">
                                    from
                                    <g:datePicker name="minDate" precision="day"/>
                                    to
                                    <g:datePicker name="maxDate" precision="day"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="shortcuts"><strong>Shortcuts</strong></label>
                                </td>
                                <td valign="top" class="value">
                                    <g:radio name='shortcut' value="null"/><em>None</em><br/>
                                    <g:radio name='shortcut' value="lastWeek"/><em>Last Week</em><br/>
                                    <g:radio name='shortcut' value="thisMonth"/><em>This Month</em><br/>
                                    <g:radio name='shortcut' value="lastMonth"/><em>Last Month</em><br/>
                                    <g:radio name='shortcut' value="thisYear"/><em>This Year</em>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input type="submit" value="Report" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
