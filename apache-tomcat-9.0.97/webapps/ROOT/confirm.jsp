<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 24-Nov-24
  Time: 1:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Xác nhận xóa</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .confirmation-container {
      text-align: center;
      padding: 20px;
    }
  </style>
</head>
<body>
<div class="container confirmation-container">
  <p>Bạn có muốn xóa thông tin phòng trọ
    <c:forEach var="roomId" items="${selectedRoomIds}">
      <c:out value="${roomId}"/><c:if test="${not empty roomId}">, </c:if>
    </c:forEach>
    hay không?
  </p>

  <form method="post" action="${pageContext.request.contextPath}/rooms">
    <input type="hidden" name="path" value="delete">  <!-- Specify delete path -->
    <c:forEach var="roomId" items="${selectedRoomIds}">
      <input type="hidden" name="selectedRooms" value="${roomId}">  <!-- Pass room ID for deletion -->
    </c:forEach>
    <button type="submit" name="choice" value="yes" class="btn btn-danger">Yes</button>
    <a href="${pageContext.request.contextPath}/rooms" class="btn btn-secondary">No</a>
  </form>
</div>

</body>
</html>
