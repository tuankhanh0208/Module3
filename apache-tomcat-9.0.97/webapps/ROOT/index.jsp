<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Bundle JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>


    <link href="style/css/home.css" rel="stylesheet">
    <title>JSP - Hello World</title>
</head>
<body>
<div class="container-fluid px-0 mx-1 ">
    <jsp:include page="header.jsp"/>
    <jsp:include page="navigation.jsp"/>
    <div class="banner">
        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0"
                        class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                        aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                        aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="https://img3.thuthuatphanmem.vn/uploads/2019/10/14/banner-thoi-trang-dang-cap-hien-dai_113856116.png"
                         class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="https://img3.thuthuatphanmem.vn/uploads/2019/10/14/banner-thoi-trang-dang-cap-hien-dai_113856116.png"
                         class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="https://img3.thuthuatphanmem.vn/uploads/2019/10/14/banner-thoi-trang-dang-cap-hien-dai_113856116.png"
                         class="d-block w-100" alt="...">
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
                    data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
                    data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>
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
            <jsp:include page="searchSlide.jsp"/>
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
</div>

<%--        <script>--%>
<%--            function searchByName(param){--%>
<%--                var txtSearch = param.value;--%>
<%--                $.ajax({--%>
<%--                    url:"/products/search",--%>
<%--                    type:"get",--%>
<%--                    data:{--%>
<%--                        txt:txtSearch--%>
<%--                    },--%>
<%--                    success:function (data){--%>
<%--                        var  row = document.getElementById("content");--%>
<%--                        row.innerHTML = data;--%>
<%--                    },--%>
<%--                    error:function (xhr){--%>

<%--                    }--%>
<%--                });--%>
<%--            }--%>
<%--        </script>--%>
</body>
</html>