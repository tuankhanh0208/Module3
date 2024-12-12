<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 23-Nov-24
  Time: 10:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<style>
    body{
        background-color: gray;
    }
    .container{
        max-width: 600px;
        background-color: #fff;
        border: 1px solid rgb(192, 183, 183);
        align-items: center;
        padding: 50px;
        border-radius: 10px;
    }
    #maPhongTro{
        color: #fff;
        background-color: rgb(77, 73, 73);
    }
</style>
<body>
<div class="container">
    <h4 class="text-center">Tạo thông tin thuê trọ</h4>
    <form method="post" action="${pageContext.request.contextPath}/rooms?path=add">
        <div class="mb-3">
            <label for="idRoom" class="form-label">Mã phòng trọ</label>
            <input type="text" class="form-control" id="idRoom"  readonly name="idRoom"  >
<%--            <p class="text-danger">${mess}</p>--%>
        </div>
        <div class="mb-3">
            <label for="username" class="form-label">Tên người thuê</label>
            <input type="text" class="form-control" id="username"  placeholder="Nhập tên người thuê" name="username"  >
            <p class="text-danger">${usernameError}</p>
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Số điện thoại</label>
            <input type="text" class="form-control" id="phone" placeholder="Nhập số điện thoại" name="phone" >
            <p class="text-danger">${phoneError}</p>
        </div>
        <div class="mb-3">
            <label for="dateStart" class="form-label">Ngày bắt đầu thuê</label>
            <div class="input-group">
                <input type="text" class="form-control" id="dateStart" name="dateStart" placeholder="Nhập ngày bắt đầu thuê" >
                <span class="input-group-text"><i class="fas fa-calendar-alt"></i></span>
            </div>
            <p class="text-danger">${dateError}</p>
        </div>
        <div class="mb-3">
            <label for="paymentMethod" class="form-label">Hình thức thanh toán</label>
            <select class="form-select" id="paymentMethod" name="paymentMethod"  >
                <option value="1">Theo Tháng</option>
                <option value="2">Theo Quý</option>
                <option value="3">Theo Năm</option>
            </select>
<%--        <p class="text-danger">${mess}</p>--%>
        </div>
        <div class="mb-3">
            <label for="note" class="form-label">Ghi chú</label>
            <textarea class="form-control" id="note" rows="3" name="note" placeholder="Nhập ghi chú của bạn" dirname="note" ></textarea>
            <p class="text-danger">${noteError}</p>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary" name="submit">Tạo mới</button>
            <a href="${pageContext.request.contextPath}/rooms"><button type="button" class="btn btn-secondary">Hủy</button></a>
        </div>
    </form>
</div>
</body>
</html>
