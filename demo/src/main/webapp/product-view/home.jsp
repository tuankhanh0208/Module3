<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Home page</h1>
<form action="${pageContext.request.contextPath}/products" method="get" class="form-inline mb-4">
<%--    <input type="hidden" name="path" value="search">--%>
    <input type="text" name="keyword" class="form-control mr-2" placeholder="Search by name">
    <button type="submit" class="btn btn-primary">Search</button>
</form>

<table border="1">
    <tr>
        <th>#</th>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Image</th>
        <th colspan="2">Action</th>
    </tr>
    <!-- Sửa lại thành "products" thay vì "list" -->
    <c:forEach var="item" items="${list}" varStatus="loop">
        <tr>
            <td>${loop.index}</td>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td><img src="${item.img}" width="300px" height="200px"></td>
            <td><a href="http://localhost:8080/products?path=edit&id=${item.id}"><button>Sửa</button></a></td>
            <td><a href="http://localhost:8080/products?path=delete&id=${item.id}"><button>Xóa</button></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
