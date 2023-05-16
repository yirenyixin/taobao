<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yirenyixin
  Date: 2022/6/23
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>已收货</title>
</head>
<body>
<c:forEach items="${all}" var="ProductState" >
    <table class="table" border="1" cellspacing="0" cellpadding="0">
    <tr>
        <th>商品名</th>
        <th>状态</th>
        <th>城市</th>
        <th>详细地址</th>
        <th>电话</th>
        <th>收件人</th>
        <th>数量</th>
        <th>价格</th>
    </tr>
        <tr>
            <td>${ProductState.productname}</td>
            <td>${ProductState.state}</td>
            <td>${ProductState.city}</td>
            <td>${ProductState.addr}</td>
            <td>${ProductState.phone}</td>
            <td>${ProductState.addressee}</td>
            <td>${ProductState.num}</td>
            <td>${ProductState.cost}</td>
    </tr>
    </table>
</c:forEach>
</body>
</html>
