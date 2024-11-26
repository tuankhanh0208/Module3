<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 23-Nov-24
  Time: 9:39 AM
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
    .search-bar {
        display: flex;
        align-items: center;
    }

    .input-container {
        position: relative;
        width: 15%;
    }
    .input-container i {
        position: absolute;
        top: 50%;
        left: 10px;
        transform: translateY(-50%);
        color: #999;
        z-index: 1;
        pointer-events: none;
    }
    .input-container i {
        position: absolute;
        top: 50%;
        left: 10px;
        transform: translateY(-50%);
        color: #999;
        z-index: 1;
        pointer-events: none;
    }
    .input-container input {
        width: 100%;
        border-radius: 39%;
        padding: 10px 10px 10px 30px;
        box-sizing: border-box;
    }

    .search-bar button {
        margin-left: auto;
    }
    .btn{
        float: right;
    }
</style>
<body>
<div class="container-fluid px-0 mx-1">
    <div class="search-bar ">
        <div class="input-container">
            <i class="fa-solid fa-magnifying-glass"></i>
            <input type="text" class="form-control" placeholder="search">
        </div>
        <button class="btn btn-primary btn-create">Tạo mới</button>
    </div>
    <div class="table-container" style="margin-top: 15px;">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>STT</th>
                <th>Mã phòng trọ</th>
                <th>Tên người thuê trọ</th>
                <th>Số điện thoại</th>
                <th>Ngày bắt đầu thuê</th>
                <th>Hình thức thanh toán</th>
                <th>Ghi chú</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="room" varStatus="loop" >
            <tr>
                <td>${loop.index}</td>
                <td>${room.idRoom}</td>
                <td>${room.username}</td>
                <td>${room.phone}</td>
                <td>${room.dateStart}</td>
                <td>${room.paymentMethod}</td>
                <td>${room.note}</td>
                <td><input type="checkbox"></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <button class="btn btn-danger btn-delete">Xóa</button>
</div>
</body>
</html>
