<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 19-Nov-24
  Time: 7:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/edit.css">
    <title>Edit Product</title>
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
<form method="post" action="http://localhost:8080/admin?path=edit">
<div class="container" >
    <ul>
        <li>
            <h1>Edit Product</h1>
        </li>
        <li>
            <p>Id:</p>
            <input type="text" name="id" placeholder="ID" value="${product.id}" readonly>
        </li>
        <li>
            <p>Name:</p>
            <input type="text" name="name" placeholder="Enter your name" value="${product.name}">
        </li>
        <li>
            <p>Description:</p>
            <input type="text" name="description" placeholder="Enter your description" value="${product.description}">
        </li>
        <li>
            <p>Title:</p>
            <input type="text" name="title" placeholder="Enter your title" value="${product.title}">
        </li>
        <li>
            <p>Price:</p>
            <input type="number" name="price" placeholder="Enter your price" value="${product.price}">
        </li>
        <li>
            <p>Image:</p>
            <input type="text" name="image" placeholder="Enter your image" value="${product.image}">
<%--            <input type="file" name="image" accept="image/" value="${product.image}">--%>
        </li>
        <li>
            <p>Category</p>
            <select class="form-select " name="categoryMethod" aria-label="Default select example">
                <option value="1" ${product.idCategory == 1 ? 'selected' : ''}>Men</option>
                <option value="2" ${product.idCategory == 2 ? 'selected' : ''}>Women</option>
                <option value="3" ${product.idCategory == 3 ? 'selected' : ''}>Kids</option>
                <option value="4" ${product.idCategory == 4 ? 'selected' : ''}>Shoes</option>
                <option value="5" ${product.idCategory == 5 ? 'selected' : ''}>Accessories</option>
            </select>
        </li>
        <li>
            <button>Submit</button>
        </li>
    </ul>
</div>
</form>
</body>
</html>
