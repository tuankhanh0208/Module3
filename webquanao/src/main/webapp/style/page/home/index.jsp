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


    <link href="../../css/home.css" rel="stylesheet">
    <title>JSP - Hello World</title>
</head>
<body>
<div class="container-fluid px-0 mx-1 ">
    <jsp:include page="/style/page/home/header.jsp"/>
    <jsp:include page="/style/page/home/navigation.jsp"/>
    <jsp:include page="/style/page/home/banner.jsp"/>
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
            <jsp:include page="/style/page/home/searchSlide.jsp"/>
        </div>
        <div class="mid">
<%--            <div class="mid-bot-add btn btn-primary" style="text-decoration: none;margin-top: 15px;">--%>
<%--                <a href="http://localhost:8080/products?path=add" style="text-decoration: none;color: #fff">Thêm sản--%>
<%--                    phẩm</a>--%>
<%--            </div>--%>
            <div class="row" style="margin-top: 20px">
                <c:forEach items="${list}" varStatus="loop" var="item">
                    <div class="card" style="width: 18rem; margin-top: 20px">
                        <a href="${pageContext.request.contextPath}/products?path=detail&id=${item.id}">
                        <img src="${item.image}" class="card-img-top" alt="..."/>
                        </a>
                        <div class="card-body">
                                <%--                                <h5 class="card-title">${item.name}</h5>--%>
                            <h4 class="card-title">${item.title}</h4>
                            <h3 class="card-title">$ ${item.price}</h3>
                            <p class="card-text">${item.description}</p>
                            <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
                        </div>
                        <div style="display: flex; justify-content: space-between;">
                            <a href="http://localhost:8080/products?path=order&id=${item.id}"
                               class="btn btn-primary edit">Add to card</a>
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