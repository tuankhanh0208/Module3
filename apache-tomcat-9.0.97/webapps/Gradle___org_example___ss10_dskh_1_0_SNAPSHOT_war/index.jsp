<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form action="" method="get">
    <h1>Danh sách khách hàng</h1>
    <table border="1">
        <tr>
            <th>Tên</th>
            <th>Ngày Sinh</th>
            <th>Địa chỉ</th>
            <th>Ảnh</th>
        </tr>
        <c:forEach var="customer" items="${list}">
            <tr>
                <td>${customer[0]}</td>
                <td>${customer[1]}</td>
                <td>${customer[2]}</td>
                <td><img src="${customer[3]}" width="100px" height="100px"></td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>