<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>提交订单</title>
</head>

<body>
    <h1>提交订单</h1>

    <form action="CreateOrderServlet" method="post">
        <fieldset>
        <c:if test="${not empty product2Buy}">
                <table border="1">
                    <tr>
                        <th>序号</th>
                        <th>类别</th>
                        <th>商品名字</th>
                        <th>售价</th>
                        <th>图片</th>
                        <th>店名</th>
                        <th>数量</th>
                        <th>总价</th>
                    </tr>
                    <c:set value="0" var="totalPrice"/>
                    <c:forEach items="${product2Buy}" var="entry" varStatus="vs">
                        <tr>
                            <td width="10%">${vs.count}</td>
                             <td>${entry.key.category}</td>
                             <td>${entry.key.productName}</td>
                             <td>$${entry.key.productPrice}</td>
                             <td><img src="${entry.key.productImage}" alt="${entry.key.productName}" width="50"></td>
                             <td>${entry.key.store}</td>
                             <td>${entry.value}</td>
                             <td width="10%">${entry.key.productPrice * entry.value}</td>
                        </tr>
                        <c:set var="totalPrice" value="${totalPrice + entry.key.productPrice * entry.value}"/>
                    </c:forEach>
                </table>
        </fieldset>
        </c:if>
        <table cellspacing="1" class="carttable">
            <tr>
                <td style="text-align:right; padding-right:40px;">
                    <font style="color:#FF0000">合计：&nbsp;&nbsp;${totalPrice != null ? totalPrice : 0}元</font>
                    <input type="hidden" name="money" value="${totalPrice}">
                </td>
            </tr>
        </table>

        <fieldset>
            <legend>收货信息</legend>
            <label for="receiveAddress">收货地址</label>
            <input type="text" id="receiveAddress" name="receive_address" required><br>
            <label for="receiveName">收件人姓名</label>
            <input type="text" id="receiveName" name="receive_name" required><br>
            <label for="receivePhone">收件人号码</label>
            <input type="tel" id="receivePhone" name="receive_phone" required pattern="^\d{11}$" placeholder="请输入11位手机号"><br>
        </fieldset>

        <input type="submit" value="提交订单">
        <c:if test="${empty cart}">
            请选择商品
            返回购物页面
        </c:if>
    </form>
</body>
</html>
