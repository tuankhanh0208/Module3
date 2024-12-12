<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
        justify-content: space-between;
        align-items: center;
        width: 99%;
    }

    .input-container {
        position: relative;
        width: 300px;
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
        margin-right: 10px;
    }
</style>
<body>
<div class="container-fluid px-0 mx-1">
    <div class="search-bar ">
    <form action="${pageContext.request.contextPath}/rooms" method="get">
        <div class="input-container">
            <i class="fa-solid fa-magnifying-glass"></i>
            <input hidden type="search" name="path" value="search">
            <input type="text" class="form-control" placeholder="search" name="keyword">
            <button hidden type="submit"></button>
        </div>
    </form>
<%--       <a href="${pageContext.request.contextPath}/rooms?path=add" style=""> <button class="btn btn-primary btn-create">Tạo mới</button></a>--%>
        <a href="#" data-bs-toggle="modal" data-bs-target="#addRoomModal">
            <button class="btn btn-primary btn-create">Tạo mới</button>
        </a>
    </div>

        <form method="post" action="${pageContext.request.contextPath}/rooms">
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
                <th colspan="2">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="room" varStatus="loop" >
            <tr>
                <td>${loop.index +1}</td>
                <td>${room.idRoom}</td>
                <td>${room.username}</td>
                <td>${room.phone}</td>
                <td>

                    <fmt:formatDate pattern="dd/MM/yyyy"
                                    value = "${room.dateStart}" />

                </td>
                <td>
                    <c:if test="${room.paymentMethod == 1}">Theo tháng</c:if>
                    <c:if test="${room.paymentMethod == 2}">Theo quý</c:if>
                    <c:if test="${room.paymentMethod == 3}">Theo năm</c:if>
                </td>
                <td>${room.note}</td>
                <td>
                    <input type="checkbox" name="selectedRooms" value="${room.idRoom}">
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/rooms?path=edit&idRoom=${room.idRoom}">Edit</a>
                </td>

            </tr>
            </c:forEach>
            </tbody>
        </table>

     <button class="btn btn-danger btn-delete" style="align-items: center"  type="submit" formaction="${pageContext.request.contextPath}/rooms?path=confirm"  >Xóa</button>
    </div>
    </form>

<%--    <jsp:include page="add.jsp"/>--%>
    <div class="modal fade" id="addRoomModal" tabindex="-1" aria-labelledby="addRoomModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addRoomModalLabel">Thêm Phòng Trọ</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="addRoomForm" action="${pageContext.request.contextPath}/rooms" method="post">
                        <input type="hidden" name="path" value="add">
                        <div class="mb-3">
                            <label for="roomId" class="form-label">Mã Phòng</label>
                            <input type="text" class="form-control" id="roomId" name="idRoom" required>
                        </div>
                        <div class="mb-3">
                            <label for="username" class="form-label">Tên Người Thuê</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="mb-3">
                            <label for="phone" class="form-label">Số Điện Thoại</label>
                            <input type="tel" class="form-control" id="phone" name="phone" required>
                        </div>
                        <div class="mb-3">
                            <label for="dateStart" class="form-label">Ngày Bắt Đầu Thuê</label>
                            <input type="date" class="form-control" id="dateStart" name="dateStart" required>
                        </div>
                        <div class="mb-3">
                            <label for="paymentMethod" class="form-label">Hình Thức Thanh Toán</label>
                            <select class="form-select" id="paymentMethod" name="paymentMethod" required>
                                <option value="1">Theo tháng</option>
                                <option value="2">Theo quý</option>
                                <option value="3">Theo năm</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="note" class="form-label">Ghi Chú</label>
                            <textarea class="form-control" id="note" name="note"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Lưu</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
