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
    <title>DDD File Upload Page</title>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLinkTo()}">Home</a></span>
</div>
<div class="body" >
    <h1>Choose DDD file</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <form action="upload" enctype="multipart/form-data" method="post" >
        <input type="file" name="binFile" size="50" value=""/>
        <br/>
        <input type="submit" value="Upload!"/>
    </form>

</div>
</body>
</html>