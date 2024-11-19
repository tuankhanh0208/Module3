<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Hello World!</h1>
<br/>
<h1>Danh sách khách hàng</h1>
<table border="1">
    <tr>
        <th>Tên</th>
        <th>Ngày Sinh</th>
        <th>Địa chỉ</th>
        <th>Ảnh</th>
    </tr>
    <c:forEach var="customer" items="${listCustomer}">
        <tr>
            <td>${customer.name}</td>
            <td>${customer.dob}</td>
            <td>${customer.address}</td>
            <td><img src="${customer.image}" width="100px" height="100px"></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>