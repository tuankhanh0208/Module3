<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 18-Nov-24
  Time: 12:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
<form method="post" action="http://localhost:8080/products?path=add">
<%--  <input type="number" name ="id" placeholder="id"><br>--%>
  <input type="text" name="name" placeholder="Name"><br>
  <input type="number" name="price" placeholder="price"><br>
  <input type="text" name="img" placeholder="Image"><br>
  <button>Submit</button>
</form>
</body>
</html>
