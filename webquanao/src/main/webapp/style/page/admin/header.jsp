<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 04-Dec-24
  Time: 6:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Bundle JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/admin/header.css">
</head>
<body>
<header>
    <div class="container">
        <div class="logo">
            <a href="${pageContext.request.contextPath}/admin" >Dashboard</a>
        </div>
        <div class="search">
            <form class="d-flex" role="search">
                <input type="hidden" name="path" value="search">
                <input  class="form-control me-2"  placeholder="Search" aria-label="Search" name="keyword">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
        <div class="account">
            <div class="title-account">
                <p>Hello admin</p>
            </div>
            <div class="image-account">
                <img src="https://th.bing.com/th/id/OIP.QhHv-wWailfgTVNVf22inAHaHa?w=206&h=205&c=7&r=0&o=5&dpr=1.3&pid=1.7" alt="abc">
            </div>
            <div class="account-login">
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
            </div>
        </div>
    </div>
</header>
</body>
</html>
