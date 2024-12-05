<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 19-Nov-24
  Time: 3:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <title>Đăng Kí</title>
    <link rel="stylesheet" href="../../css/signup.css">
</head>
<body>
<div class="register-container">
    <h2>ĐĂNG KÝ</h2>
    <p>Đã có tài khoản, đăng nhập <a href="${pageContext.request.contextPath}/style/page/login/login.jsp">tại đây</a></p>
    <p class="text-danger">${mess}</p>
    <form action="${pageContext.request.contextPath}/signup" method="post">
<%--        <input type="text" class="form-control" placeholder="Họ">--%>
<%--        <input type="text" class="form-control" placeholder="Tên">--%>
        <input name="user" type="name" class="form-control" placeholder="Username">
<%--        <input type="text" class="form-control" placeholder="Số điện thoại">--%>
        <input name="pass" type="password" class="form-control" placeholder="Password">
    <input name="repass" type="password" id="user-repeatpass" class="form-control" placeholder="Repeat Password" >
        <button type="submit" class="btn btn-register">Đăng ký</button>
    </form>
    <div class="social-login">
        <p>Hoặc đăng nhập bằng</p>
        <button class="btn btn-facebook"><i class="fab fa-facebook-f"></i> Facebook</button>
        <button class="btn btn-google"><i class="fab fa-google"></i> Google</button>
    </div>
</div>
</body>
</html>
