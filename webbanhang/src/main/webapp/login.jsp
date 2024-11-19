<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 19-Nov-24
  Time: 3:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  <link rel="stylesheet" href="./style/css/login.css">
    <title>Login</title>
</head>
<body>
<div class="login-container">
  <h2>ĐĂNG NHẬP</h2>
  <p>Nếu bạn chưa có tài khoản, <a href="../login/signup.html">đăng ký tại đây</a></p>
  <form>
    <input type="bane" class="form-control" placeholder="Username">
    <input type="password" class="form-control" placeholder="Password">
    <button type="submit" class="btn btn-primary">Đăng nhập</button>
  </form>
  <a href="#">Quên mật khẩu</a>
  <p>Hoặc đăng nhập bằng</p>
  <button class="btn btn-social btn-facebook"><i class="fab fa-facebook-f"></i> Facebook</button>
  <button class="btn btn-social btn-google"><i class="fab fa-google"></i> Google</button>
</div>
</body>
</html>
