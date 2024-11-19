<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 18-Nov-24
  Time: 1:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form Edit</title>
</head>
<body>
<form method="post" action="http://localhost:8080/products?path=edit">
  <input type="number" name ="id" placeholder="id " value="${product.id}" readonly><br>
  <input type="text" name="name" placeholder="Name" value="${product.name}"><br>
  <input type="number" name="price" placeholder="price"value="${product.price}"><br>
  <input type="text" name="img" placeholder="Image" value="${product.img}"><br>
  <button>Submit</button>
</form>
</body>
</html>
