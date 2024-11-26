<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 26-Nov-24
  Time: 8:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8080/products?path=add" method="post">
  <ul>
    <li>
      <p>Nhập id:</p>
      <input type="text" name="id" placeholder="Nhập vào id">
    </li>
    <li>
      <p>Nhập tên:</p>
      <input type="text" name="name" placeholder="Nhập vào tên">
    </li>
    <li>
      <p>Nhập tên:</p>
      <input type="text" name="price" placeholder="Nhập vào giá">
    </li>
    <li>
      <p>Nhập tên:</p>
      <input type="text" name="description" placeholder="Nhập vào mo ta">
    </li>
    <li>
      <p>Nhập tên:</p>
      <input type="text" name="author" placeholder="Nhập vào tên nha san xuat">
    </li>
    <li>
      <button name="submit">Thêm sản phẩm</button>
    </li>

  </ul>
</form>
</body>
</html>
