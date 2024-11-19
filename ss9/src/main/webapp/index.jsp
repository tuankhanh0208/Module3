<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<%--<a href="hello-servlet">Hello Servlet</a>--%>
<form action="/display-discount" method="post">
<input type="text" placeholder="Mô tả sản phẩm" name="description">
<input type="number" placeholder="Giá niêm yết của sản phẩm" name="price">
<input type="number" placeholder="Tỉ lệ phần trăm chiết khấu" name="percent">
<button name="submit">Calculate Discount</button>
</form>
</body>
</html>