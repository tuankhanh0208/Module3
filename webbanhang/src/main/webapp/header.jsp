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
</head>
<body>
<div class="header">
    <div class="logo">
        <a href=""><img src="./style/img/logo.webp" alt=""/></a>
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
                <a href="login.jsp">Đăng nhập </a>
                <span class="divider">/</span>
                <a href="signup.jsp">Đăng Ký</a>
            </c:if>

        </div>
        <div class="cart">
            <a href=""><i class="fa-solid fa-cart-shopping"></i></a>
        </div>
    </div>
</div>
</body>
</html>
