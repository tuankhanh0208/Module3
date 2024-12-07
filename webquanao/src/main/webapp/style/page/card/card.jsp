<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 04-Dec-24
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Bundle JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/style/page/home/header.jsp"/>
<jsp:include page="/style/page/home/navigation.jsp"/>
<div class="container">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>STT</th>
            <th>Image</th>
            <th>Sản phẩm</th>
            <th>Giá</th>
            <th>Số lượng</th>
            <th>Tổng</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="total" value="0"/>
        <c:if test="${not empty sessionScope.cart}">
            <c:forEach var="item" items="${sessionScope.cart.items}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td><img src="${item.product.image}" width="300px" height="200px"></td>
                    <td>${item.product.name}</td>
                    <td>$${item.price}</td>
                    <td>${item.quantity}</td>
                    <td>${item.price * item.quantity}</td>
                    <c:set var="total" value="${total + (item.price*item.quantity)}"/>
                    <td>
                        <a href="${pageContext.request.contextPath}/order?remove=${item.product.id}" class="btn btn-danger">Remove</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="4" ></td>
                <td>Tổng tiền</td>
                <td>${total}</td>
            </tr>
        </c:if>
        <c:if test="${empty sessionScope.cart}">
            <tr>
                <td colspan="6">Giỏ hàng trống</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <div class="payment">
        <a href="${pageContext.request.contextPath}/products?path=payment" ><button class="btn btn-primary">Thanh toán</button></a>
    </div>
</div>

</body>
</html>
