<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 20-Nov-24
  Time: 4:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8080/users?path=edit" method="post">
    <ul>
        <li>
            <h1>ID</h1>
            <input name="id" type="text" readonly value="${users.id}">
        </li>
        <li>
            <h1>Nhap ten</h1>
            <input name="name" type="text" placeholder="Nhap ten" value="${users.name}">
        </li>
        <li>
            <h1>Nhap email</h1>
            <input name="email" type="email" placeholder="Nhap email" value="${users.email}">
        </li>
        <li>
            <h1>Nhap country</h1>
            <input type="text" name="country" placeholder="Nhap country" value="${users.country}">
        </li>
        <li>
            <button name="submit">Submit</button>
        </li>
    </ul>
</form>
</body>
</html>
