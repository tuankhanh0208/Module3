<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 03-Dec-24
  Time: 9:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/home.css">
</head>
<style>
    .info .item-info{
        width: 70px;
        height: 70px;
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 5px 0;
        position: relative;
        color: #fff;
    }
    .info a{
        text-decoration: none;
        color: #fff;
        /*justify-content: end;*/

    }
    .info a:hover{
        color: #ae7d1a;
    }
    .quantiy-mess{
        font-size: 10px;
        width: 10px;
        height: 10px;
        background-color: red;
        padding: 10px;
        border-radius: 50%;
        display: flex;
        justify-content: center;
        align-items: center;
        color: #eee;
        position: absolute;
        top: 50px;
        right: 50px;
    }
</style>
<body>
<div class="header">
    <div class="logo">
        <a href=""><img src="/style/img/logo.webp" alt=""/></a>
    </div>
    <div class="search-bar">
        <select class="search-select">
            <option value="giay">Giày dép</option>
            <option value="phu-kien">Phụ kiện</option>
            <option value="quan-ao">Quần áo</option>
            <option value="so-mi-tay-dai">Sơ mi tay dài</option>
            <option value="so-mi-tay-ngan">Sơ mi tay ngắn</option>
            <option value="san-pham-khuyen-mai">Sản phẩm khuyến mãi</option>
            <option value="san-pham-hot-trend">Sản phẩm hot trend</option>
            <option value="san-pham-noi-bat">Sản phẩm nổi bật</option>
            <option value="quan-short-nam">Quần short nam</option>
            <option value="quan-au-nam">Quần âu nam</option>
            <option value="so-mi-nam">Sơ mi nam</option>
            <option value="be-gai">Bé gái</option>
            <option value="be-trai">Bé trai</option>
            <option value="thoi-trang-nu">Thời trang nữ</option>
            <option value="thoi-trang-nam">Thời trang nam</option>
            <option value="tat-ca" selected>Tất cả</option>
        </select>
        <form action="${pageContext.request.contextPath}/products" method="get"
              style="display: flex;  max-width:120px; margin: auto;">
            <input type="hidden" name="path" value="search">
            <input oninput="searchByName(this)" class="search-input" type="text" placeholder="Tìm sản phẩm bạn mong muốn" name="keyword">
            <button class="search-button" type="submit">
                <i class="fas fa-search"></i>
            </button>
        </form>
    </div>
    <div class="account">
        <div class="account-user">
            <c:if test="${sessionScope.acc != null}">
                <span style="color: #fff ; font-size: 13px">Hello ${sessionScope.acc.user}</span>
            </c:if>
            <i class="fa-solid fa-user"></i>
            <c:if test="${sessionScope.acc != null}">
                <a href="logout">Đăng xuất</a>
            </c:if>
            <c:if test="${sessionScope.acc == null}">
                <a href="/style/page/login/login.jsp">Đăng nhập </a>
                <span class="divider">/</span>
                <a href="/style/page/login/signup.jsp">Đăng Ký</a>
            </c:if>

        </div>
        <div class="info">
            <div class="item-info">
            <a href="/style/page/card/card.jsp"><i class="fa-solid fa-cart-shopping"></i></a>
            </div>
            <div class="quantiy-mess">
                ${sessionScope.totalQuantity}
            </div>
        </div>
    </div>
</div>
</body>
</html>
