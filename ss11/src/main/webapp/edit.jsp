<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 20-Nov-24
  Time: 11:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
<title>Add</title>
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
