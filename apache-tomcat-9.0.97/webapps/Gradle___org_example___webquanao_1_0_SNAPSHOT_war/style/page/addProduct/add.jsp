<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 26-Nov-24
  Time: 6:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm sản phẩm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/edit.css">
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>--%>
</head>
<body>
<div class="overlay"></div>
<div class="overlay"></div>
<form method="post" action="http://localhost:8080/products?path=add">
    <div class="container" >
        <ul>
            <li>
                <h1>Add Product</h1>
            </li>
            <li>
                <p>Id:</p>
                <label>
                    <input type="text" name="id" placeholder="ID" value="${product.id}" readonly>
                </label>
            </li>
            <li>
                <p>Name:</p>
                <label>
                    <input type="text" name="name" placeholder="Enter your name" required>
                    <p class="text-danger">${nameError}</p>
                </label>
            </li>
            <li>
                <p>Description:</p>
                <label>
                    <input type="text" name="description" placeholder="Enter your description" required>
                    <p class="text-danger">${descriptionError}</p>
                </label>
            </li>
            <li>
                <p>Title:</p>
                <label>
                    <input type="text" name="title" placeholder="Enter your title" required >
                    <p class="text-danger">${titleError}</p>
                </label>
            </li>
            <li>
                <p>Price:</p>
                <label>
                    <input type="number" name="price" placeholder="Enter your price" required>
                    <p class="text-danger">${priceError}</p>
                </label>
            </li>
            <li>
                <p>Image:</p>
                <label>
                    <input type="text" name="image" placeholder="Enter your image" required >
                    <p class="text-danger">${imageError}</p>
                </label>
                <%--            <input type="file" name="image" accept="image/" value="${product.image}">--%>
            </li>
            <li>
                <button>Submit</button>
            </li>
        </ul>
    </div>
</form>

</body>
</html>
