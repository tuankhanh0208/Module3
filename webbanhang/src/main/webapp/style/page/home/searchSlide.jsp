<%--
  Created by IntelliJ IDEA.
  User: tk020
  Date: 03-Dec-24
  Time: 9:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/13.1.4/nouislider.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/13.1.4/nouislider.min.css">
</head>
<style>
    body{
        margin-top: 30px;
    }
</style>
<body>
<div class="search-slide">
    <div id="priceSlider"></div>
    <div id="priceDisplay">Price Range: $100 - $500</div>

    <form action="${pageContext.request.contextPath}/products" method="get">
        <input type="hidden" name="path" value="searchSlide">
        <input type="hidden" id="minPrice" name="minPrice">
        <input type="hidden" id="maxPrice" name="maxPrice">
        <button class="btn btn-primary" type="submit">Search</button>
    </form>
</div>
<script>
    var slider = document.getElementById('priceSlider');
    var priceDisplay = document.getElementById('priceDisplay');

    noUiSlider.create(slider, {
        start: [100, 500],
        connect: true,
        range: {
            'min': 0,
            'max': 1000
        },
        tooltips: [true, true],
        format: {
            to: function (value) {
                if (value === null || value === undefined) {
                    return '$0';
                }
                return '\\$' + Math.round(value);
            },
            from: function (value) {
                return Number(value.replace('$', '')) || 0;
            }
        }
    });
    slider.noUiSlider.on('update', function (values) {

        priceDisplay.innerHTML = `Price Range: ${values[0]} - ${values[1]}`;

        document.getElementById('minPrice').value = values[0].replace('$', '');
        document.getElementById('maxPrice').value = values[1].replace('$', '');
    });
</script>
</body>
</html>
