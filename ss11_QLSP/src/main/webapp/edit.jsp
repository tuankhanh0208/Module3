<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 26-Nov-24
  Time: 9:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8080/products?path=edit" method="post">
  <ul>
    <li>
      <p>Nhập id:</p>
      <input type="text" name="id" placeholder="Nhập vào id" readonly value="${product.id}">
    </li>
    <li>
      <p>Nhập tên:</p>
      <input type="text" name="name" placeholder="Nhập vào tên" value="${product.name}">
    </li>
    <li>
      <p>Nhập tên:</p>
      <input type="text" name="price" placeholder="Nhập vào giá" value="${product.price}">
    </li>
    <li>
      <p>Nhập tên:</p>
      <input type="text" name="description" placeholder="Nhập vào mo ta" value=" ${product.description}">
    </li>
    <li>
      <p>Nhập tên:</p>
      <input type="text" name="author" placeholder="Nhập vào tên nha san xuat" value="${product.author}">
    </li>
    <li>
      <button name="submit">Sửa</button>
    </li>

  </ul>
</form>
</body>
</html>
