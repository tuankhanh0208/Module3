<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 03-Dec-24
  Time: 12:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="style/css/login.css">
    <title>Title</title>
</head>
<body>
<div class="login-container">
  <!-- Forgot Password Form -->
  <form id="forgot-password-form" action="${pageContext.request.contextPath}/forgotPassword" method="get">
    <h2>Lấy lại mật khẩu</h2>
    <p class="text-danger">${mess}</p>
    <input type="text" class="form-control" placeholder="Username" name="user" required>
    <button type="button" class="btn btn-primary" onclick="showPasswordResetForm()">Tiếp tục</button>
    <p>Nếu bạn có tài khoản, <a href="login.jsp">Đăng nhập tại đây</a></p>
  </form>

  <!-- Reset Password Form -->
  <form id="reset-password-form" style="display:none;" action="${pageContext.request.contextPath}/forgotPassword" method="post">
    <h2>Đặt lại mật khẩu</h2>
    <p class="text-danger">${mess}</p>
    <input type="password" class="form-control" placeholder="Mật khẩu mới" name="newPassword">
    <input type="password" class="form-control" placeholder="Xác nhận mật khẩu mới" name="confirmPassword">
    <input type="hidden" name="user" value="${user}">
    <button type="submit" class="btn btn-primary">Đặt lại mật khẩu</button>
    <p>Quay lại trang đăng nhập? <a href="login.jsp">Đăng nhập tại đây</a></p>
  </form>
</div>
<script>
  function showPasswordResetForm() {
    document.getElementById('forgot-password-form').style.display = 'none';

    document.getElementById('reset-password-form').style.display = 'block';
  }
</script>
</body>
</html>
