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
    <link rel="stylesheet" href="../../css/edit.css">
    <title>Edit Product</title>
</head>
<body>
<div class="overlay"></div>
<form method="post" action="http://localhost:8080/products?path=edit">
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
            <button>Submit</button>
        </li>
    </ul>
</div>
</form>
</body>
</html>
