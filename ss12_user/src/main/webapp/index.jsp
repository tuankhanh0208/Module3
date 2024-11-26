<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "WEB quản lý User" %></h1>
<form action="${pageContext.request.contextPath}/users" method="get">
    <h2>Tìm kiếm gần đúng theo country</h2>
    <input name="path" hidden="" value="search">
    <input name="keyword" type="text" placeholder="Nhap country de tim">
    <button name="submit">Tim kiem</button>
</form>
<h1>Danh sach User</h1>
<a href="${pageContext.request.contextPath}/add.jsp">Them users</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>
                <a href="${pageContext.request.contextPath}/users?path=sort&sortOrder=${SortOrder == 'ASC' ? 'desc' : 'asc'}">
                    Name
                    <c:choose>
                        <c:when test="${SortOrder == 'ASC'}"><td>abc</td></c:when>
                        <c:when test="${SortOrder == 'DESC'}"><td>desc</td></c:when>
                    </c:choose>
                </a>
            </th>
            <th>Email</th>
            <th>Country</th>
            <th colspan="2">Action</th>
        </tr>
        <c:forEach var="item" items="${users}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.email}</td>
            <td>${item.country}</td>
            <td><a href="http://localhost:8080/users?path=edit&id=${item.id}"><button>Sua</button></a> </td>
            <td><a href="http://localhost:8080/users?path=remove&id=${item.id}"><button>Xoa</button></a> </td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>