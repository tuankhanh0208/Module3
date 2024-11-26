<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 20-Nov-24
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Them User</title>
</head>
<body>
<form action="http://localhost:8080/users?path=add" method="post">
  <ul>
    <li>
      <h1>Nhap ten</h1>
      <input name="name" type="text" placeholder="Nhap ten">
    </li>
    <li>
      <h1>Nhap email</h1>
      <input name="email" type="email" placeholder="Nhap email">
    </li>
    <li>
      <h1>Nhap country</h1>
      <input type="text" name="country" placeholder="Nhap country">
    </li>
    <li>
      <button name="submit">Submit</button>
    </li>
  </ul>
</form>
</body>
</html>
