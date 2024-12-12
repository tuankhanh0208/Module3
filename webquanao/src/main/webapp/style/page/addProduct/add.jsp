<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<style>
    select.form-select{
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        margin-top: 5px;
    }
    div.submit {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 10px;
    }
    div.submit button {
        width: 48%;
        padding: 10px 20px;
        font-size: 16px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    div.submit .button-submit {
        background-color: #4CAF50;
        color: white;
    }

    div.submit a button {
        background-color: #f44336;
        color: white;
    }

    div.submit button:hover {
        opacity: 0.9;
    }
    div.submit .button-cancel {
        display: inline-block;
        text-align: center;
        width: 48%; /* Giống nút Submit */
        padding: 10px 20px;
        font-size: 16px;
        border: none;
        border-radius: 5px;
        background-color: #f44336; /* Màu nền đỏ */
        color: white;
        text-decoration: none; /* Loại bỏ gạch dưới */
        cursor: pointer;
    }
</style>
<body>
<div class="overlay"></div>
<div class="overlay"></div>
<form method="post" action="http://localhost:8080/admin?path=add">
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
                <p>Price:</p>
                <label>
                    <input type="number" name="price" placeholder="Enter your price" required>
                    <p class="text-danger">${priceError}</p>
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
                <p>Image:</p>
                <label>
                    <input type="text" name="image" placeholder="Enter your image" required >
                    <p class="text-danger">${imageError}</p>
                </label>
                <%--            <input type="file" name="image" accept="image/" value="${product.image}">--%>
            </li>
            <li>
                <p>Category</p>
                <select class="form-select " name="categoryMethod" id="categoryMethod" onchange="checkOtherCategory()">
                    <c:forEach items="${categoriesList}" var="category">
                        <option value="${category.cid}">${category.name}</option>
                    </c:forEach>
                    <option value="other">Khác</option>
                </select>

                <!-- Input ẩn để người dùng nhập khi chọn 'Khác' -->
                <input type="text" id="otherCategoryInput" name="otherCategory" placeholder="Nhập danh mục khác..." style="display: none;" />


            </li>
            <li>
                <div class="submit">
                    <button type="submit" class="button-submit">Submit</button>
                <a href="add.jsp" class="button-cancel">Hủy</a>
                </div>
            </li>
        </ul>
    </div>
</form>
<script>
    function checkOtherCategory() {
        const selectElement = document.getElementById('categoryMethod');
        const otherCategoryInput = document.getElementById('otherCategoryInput');

        if (selectElement.value === 'other') {
            otherCategoryInput.style.display = 'block';
        } else {
            otherCategoryInput.style.display = 'none';
        }
    }

</script>
</body>
</html>
