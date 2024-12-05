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
    <link rel="stylesheet" href="../../css/home.css">
</head>
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
        <div class="mid-bot-add btn btn-primary" style="text-decoration: none;margin-top: 15px;">
            <a href="http://localhost:8080/products?path=add" style="text-decoration: none;color: #fff">Thêm sản
                phẩm</a>
        </div>
        <div class="row" style="margin-top: 20px">
            <c:forEach items="${list}" varStatus="loop" var="item">
                <div class="card" style="width: 18rem; margin-top: 20px">
                    <img src="${item.image}" class="card-img-top" alt="..."/>
                    <div class="card-body">
                            <%--                                <h5 class="card-title">${item.name}</h5>--%>
                        <h4 class="card-title">${item.title}</h4>
                        <h3 class="card-title">$ ${item.price}</h3>
                        <p class="card-text">${item.description}</p>
                        <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
                    </div>
                    <div style="display: flex; justify-content: space-between;">
                        <a href="http://localhost:8080/products?path=edit&id=${item.id}"
                           class="btn btn-primary edit">Sửa</a>
                        <label for="delete-${loop.index}" class="btn btn-primary red">Xóa</label>
                        <input type="checkbox" id="delete-${loop.index}" class="confirm-checkbox"/>
                        <div class="confirm-modal">
                            <div class="modal-content">
                                <p>Bạn có chắc chắn muốn xóa?</p>
                                <a href="http://localhost:8080/products?path=delete&id=${item.id}"
                                   class="btn btn-danger">Xác nhận</a>
                                <label for="delete-${loop.index}" class="btn btn-secondary">Hủy</label>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
