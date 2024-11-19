<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<%--<a href="hello-servlet">Hello Servlet</a>--%>
</body>
<strong>Simple Calculator</strong>
<form action="/calculate" method="post">
  <ul>
    <li>
      <p>Calculator</p>
    </li>
    <li>
      <p>First operand:</p>
      <input type="number" name="a" id="number a" placeholder="Nhap so a">
    </li>
    <li>
      <p>Operand:</p>
     <select name="operator">
       <option value="+">Cộng</option>
       <option value="-">Trừ</option>
       <option value="*">Nhân</option>
       <option value="/">Chia</option>
     </select>
    </li>
    <li>
      <p>Second operand:</p>
      <input type="number" name="b" id="number b" placeholder="Nhap so b">
    </li>
    <li>
      <button name="submit">Submit</button>
      <h2 >${result}</h2>
      <h2>${message}</h2>
    </li>
  </ul>
</form>
</html>