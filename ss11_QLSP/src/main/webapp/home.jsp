<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 26-Nov-24
  Time: 8:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/products" method="get" class="form-inline mb-4">
  <input type="hidden" name="path" value="search">
  <input type="text" name="keyword" class="form-control mr-2" placeholder="Search by name">
  <button type="submit" class="btn btn-primary">Search</button>
  <p style="color: red">${mess}</p>
</form>
<a href="${pageContext.request.contextPath}/add.jsp">Thêm sản phẩm</a>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Tên sản phẩm</th>
    <th>Giá sản phẩm</th>
    <th>Mô tả sản phẩm</th>
    <th>Nhà sản xuất</th>
    <th colspan="3">Action</th>

  </tr>
  <c:forEach items="${list}" var="item">
    <tr>
      <td>${item.id}</td>
      <td>${item.name}</td>
      <td>${item.price}</td>
      <td>${item.description}</td>
      <td>${item.author}</td>
      <td>
        <a href="http://localhost:8080/products?path=edit&id=${item.id}"> <button>Sửa</button></a>
      </td>
      <td><a href="http://localhost:8080/products?path=remove&id=${item.id}">
        <button >Xóa</button>
      </a> </td>
      <td><a href="/products?path=detail&id=${item.id}"><button>Xem chi tiet</button></a></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
