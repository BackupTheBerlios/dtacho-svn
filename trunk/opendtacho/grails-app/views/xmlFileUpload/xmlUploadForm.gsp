<%--
  Created by IntelliJ IDEA.
  User: meberspaecher
  Date: 14.08.2008
  Time: 15:39:33
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title><g:message code="xml.title"/></title>
    <meta name="layout" content="main"/>
  </head>
  <body>
    <div class="body" >
      <h1>XML</h1>
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <form action="upload" enctype="multipart/form-data" method="post" >
        <div class="dialog">
          <table>
            <tbody>
              <tr class="prop">
                <td valign="top" class="name">
                  <strong><g:message code="xml.header"/></strong>
                </td>
                <td>
                  <input type="file" name="xmlFile" size="50" value=""/>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="buttons">
          <span class="button">
            <input class="upload" type="image" alt="<g:message code="xml.button.upload"/>"/>
          </span>
        </div>
      </form>
    </div>
  </body>
</html>