<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 03-Dec-24
  Time: 7:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/style/css/home.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Bundle JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<style>
    .card{
        float: left;
        margin-top: 0;
    }
    .card-body{
        margin-left: 400px;
    }
    .order{
        background-color: #1b4332 !important;
        margin-top: 30px;
    }
    .add-order{
        background-color: #ffffff !important; ;
        border: 1px solid #20a86d !important;
        margin-top: 25px;
        color: #1caf71 !important;
    }
   .order a:hover{
       background-color: #ae7d1a;
   }
   .add-order a:hover{
       background-color: #ae7d1a;
   }
    .quantity-control {
        display: flex;
        align-items: center;
        border: 1px solid #ddd;
        border-radius: 5px;
        overflow: hidden;
        width: 120px;
    }


    .btnn:hover {
        background-color: #e0e0e0;
    }

    .btnn.plus {
        background-color: #4caf50;
        color: white;
    }

    .btnn.plus:hover {
        background-color: #45a049;
    }

    .btnn.minus {
        color: red;
    }
    .btnn {
        background-color: #f5f5f5;
        border: none;
        width: 30px;
        height: 40px;
        font-size: 18px;
        cursor: pointer;
        display: flex;
        align-items: center;
        justify-content: center;
        user-select: none;
        transition: background-color 0.2s ease;
    }
    .input-quantity {
        width: 60px;
        text-align: center;
        border: none;
        font-size: 16px;
        outline: none;
    }

</style>
<body>
<jsp:include page="../home/header.jsp"/>
<jsp:include page="../home/navigation.jsp"/>
<div class="main">
    <div class="side-bar bg-light p-3 rounded shadow">
        <p class="title">Danh mục sản phẩm</p>
        <div class="row">
            <div class="col-4">
                <c:forEach var="item" items="${listCC}">
                    <div class="list-group" id="list-tab" role="tablist">
                        <a class="list-group-item list-group-item-action ${tag == item.cid ? "active":""}"
                           id="list-home-list"
                           href="${pageContext.request.contextPath}/products?path=category&cid=${item.cid}"
                           role="tab" aria-controls="list-home">${item.name}</a>
                    </div>
                </c:forEach>
            </div>
        </div>
        <jsp:include page="../home/searchSlide.jsp"/>
    </div>
    <div class="mid">
<%--        <div class="mid-bot-add btn btn-primary" style="text-decoration: none;margin-top: 15px;">--%>
<%--            <a href="http://localhost:8080/products?path=add" style="text-decoration: none;color: #fff">Thêm sản--%>
<%--                phẩm</a>--%>
<%--        </div>--%>
            <form action="${pageContext.request.contextPath}/products?path=detail" method="get">
            <div class="card" style="width: 18rem; margin-top: 20px">
                <img src="${products.image}" class="card-img-top" alt="..." width="100px"/>
            </div>
                <div class="card-body">
                    <input type="text" hidden="hidden" name="id" value="${products.id}">
                    <h4 class="card-title">${products.title}</h4>
                    <h3 class="card-title">$ ${products.price}</h3>
                    <p class="card-text">${products.description}</p>
                    <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
                </div>
                <div class="quantity">
                    <div class="quantity-control">
                        <button class="btnn minus">-</button>
                        <input type="number" class="input-quantity" value="1" min="1" />
                        <button class="btnn plus">+</button>
                    </div>
                </div>
                <div class="card-order" style="display: flex; flex-direction: column">
                    <a href="http://localhost:8080/products?path=buy&id=${products.id}"
                       class="btn btn-primary order ">Mua ngay</a>
                    <a href="http://localhost:8080/products?path=addOrder&id=${products.id}"
                       class="btn btn-primary add-order">Thêm vào giỏ hàng</a>
                </div>

            </form>
    </div>
</div>
<script>
    const minusButtons = document.querySelectorAll('.minus');
    const plusButtons = document.querySelectorAll('.plus');

    // Xử lý giảm số lượng
    minusButtons.forEach((btn) => {
        btn.addEventListener('click', (event) => {
            event.preventDefault(); // Ngăn hành vi mặc định
            const input = event.target.parentNode.querySelector('.input-quantity');
            const value = parseInt(input.value, 10);
            if (value > 1) {
                input.value = value - 1;
            }
        });
    });

    // Xử lý tăng số lượng
    plusButtons.forEach((btn) => {
        btn.addEventListener('click', (event) => {
            event.preventDefault(); // Ngăn hành vi mặc định
            const input = event.target.parentNode.querySelector('.input-quantity');
            const value = parseInt(input.value, 10);
            input.value = value + 1;
        });
    });

</script>
</body>
</html>
