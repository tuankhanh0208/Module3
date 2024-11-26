<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 20-Nov-24
  Time: 12:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chi tiet san pham</title>
</head>
<body>
<form action="http://localhost:8080/products?path=detail"></form>
<h1>Chi Tiết Sản Phẩm</h1>
<p name="id"><strong>ID:</strong> ${product.id}</p>
<p><strong>Tên sản phẩm:</strong> ${product.name}</p>
<p><strong>Giá:</strong> ${product.price}</p>
<p><strong>Mô tả:</strong> ${product.description}</p>
<p><strong>Tác giả:</strong> ${product.author}</p>
<a href="/products">Quay lại</a>
</body>
</html>
