<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 18-Nov-24
  Time: 8:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<center><h1><%= request.getAttribute("errorMessage") %></h1></center>
</body>
</html>
