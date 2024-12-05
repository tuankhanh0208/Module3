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
  <link rel="stylesheet" href="../../css/login.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
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
    <p>Nếu bạn có tài khoản, <a href="${pageContext.request.contextPath}/style/page/login/login.jsp">Đăng nhập tại đây</a></p>
  </form>

  <!-- Reset Password Form -->
  <form id="reset-password-form" style="display:none;" action="${pageContext.request.contextPath}/forgotPassword" method="post">
    <h2>Đặt lại mật khẩu</h2>
    <p class="text-danger">${mess}</p>
    <input type="password" class="form-control" placeholder="Mật khẩu mới" name="newPassword">
    <input type="password" class="form-control" placeholder="Xác nhận mật khẩu mới" name="confirmPassword">
    <input type="hidden" name="user" value="${user}">
    <button type="submit" class="btn btn-primary">Đặt lại mật khẩu</button>
    <p>Quay lại trang đăng nhập? <a href="${pageContext.request.contextPath}/style/page/login/login.jsp">Đăng nhập tại đây</a></p>
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
