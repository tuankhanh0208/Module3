<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 03-Dec-24
  Time: 7:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

</head>
<style>
    .table-container p{
        font-size: 25px;
        font-weight: bolder;
        align-items: center;
    }
    .btn{
        float: right;
        margin-right: 10px;
    }
    #confirmationForm {
        display: none;
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 300px;
        height: 200px;
        display: flex;
        align-items: center;
        justify-content: center;
        border-radius: 10px;
        background-color: rgba(0, 0, 0, 0.5);
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }
    div.confirmation-container{
        color: #fff;
        width: 100%;
        text-align: center;
    }
    .alert-message {
        background-color: #f8d7da;
        color: #fff;
        border: 1px solid #f5c6cb;
        padding: 10px;
        margin-top: 10px;
        text-align: center;
        font-weight: bold;
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 300px;
        height: auto;
        display: flex;
        align-items: center;
        justify-content: center;
        border-radius: 10px;
        background-color: rgba(0, 0, 0, 0.5);
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }

</style>
<body>
<jsp:include page="header.jsp"/>
<div class="table-container">
    <p>Danh sách sẩn phẩm</p>
    <a href="${pageContext.request.contextPath}/admin?path=add" style=""> <button class="btn btn-primary btn-create">Tạo mới</button></a>
<form method="post" action="${pageContext.request.contextPath}/admin">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>STT</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Title</th>
            <th>Image</th>
            <th>Category</th>
            <th colspan="2">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="item" varStatus="loop">
            <tr>
                <td>${loop.index+1}</td>
                <td>${item.name}</td>
                <td>${item.description}</td>
                <td>$ ${item.price}</td>
                <td>${item.title}</td>
                <td><img src="${item.image}" width="200px" height="200px"></td>
                <td>
                    <c:if test="${item.idCategory == 1}">Men</c:if>
                    <c:if test="${item.idCategory == 2}">Women</c:if>
                    <c:if test="${item.idCategory == 3}">Kids</c:if>
                    <c:if test="${item.idCategory == 4}">Shoes</c:if>
                    <c:if test="${item.idCategory == 5}">Accessories</c:if>
                </td>
                <td><input type="checkbox" name="selectedProduct" value="${item.id}"></td>
                <td><a href="${pageContext.request.contextPath}/admin?path=edit&id=${item.id}">Edit</a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button class="btn btn-danger btn-delete" style="align-items: center" type="button" id="deleteButton">Xóa</button>

    <div class="container confirmation-container" id="confirmationForm" style="display: none;">
        <p>Bạn có muốn xóa thông tin sản phẩm
            <c:forEach var="productId" items="${selectedProductIds}">
                <c:out value="${productId}"/><c:if test="${not empty productId}">, </c:if>
            </c:forEach>
            hay không?
        </p>
        <form method="post" action="${pageContext.request.contextPath}/admin">
            <input type="hidden" name="path" value="delete">
            <c:forEach var="productId" items="${selectedProductIds}">
                <input type="hidden" name="selectedProduct" value="${productId}">
            </c:forEach>
            <button type="submit" name="choice" value="yes" class="btn btn-danger">Yes</button>
            <a href="${pageContext.request.contextPath}/admin" class="btn btn-secondary">No</a>
        </form>
    </div>
</form>
    <div id="errorMessage" class="alert-message" style="display: none;">
        Vui lòng chọn sản phẩm để xóa.
        <a href="${pageContext.request.contextPath}/admin" class="btn btn-secondary" style="background-color: #1c5b41">OK</a>
    </div>
</div>
<script>
    document.getElementById("deleteButton").addEventListener("click", function() {
        var selectedProducts = document.querySelectorAll('input[name="selectedProduct"]:checked');
        var errorMessage = document.getElementById("errorMessage");
        if (selectedProducts.length === 0) {
            errorMessage.style.display = "block";
        } else {
            errorMessage.style.display = "none";
            document.getElementById("confirmationForm").style.display = "block";
        }
    });

</script>
</body>
</html>
