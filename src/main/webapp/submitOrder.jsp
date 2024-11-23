<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>提交订单</title>
    <link rel="stylesheet" type="text/css" href="css/order.css">
</head>

<body>
    <div class="main">
        <h1>确认您的订单</h1>
        <form action="CreateOrderServlet" method="post">
            <c:if test="${not empty product2Buy}">
                <c:set value="0" var="totalPrice"/>
                <c:set value="0" var="totalProducts"/>
                <c:forEach items="${product2Buy}" var="entry" varStatus="vs">
                <div class="order-container">
                    <div class="products-container">
                        <div class="rank">
                                ${vs.count}
                        </div>
                        <div class="product-image">
                            <img src="${entry.key.productImage}" alt="${entry.key.productName}">
                        </div>

                       <div class="container1">
                            <div class="product-name">
                                ${entry.key.productName}
                            </div>
                            <div class="product-price">
                                $${entry.key.productPrice}
                            </div>
                            <div class="product-quantity">
                                ${entry.value}
                            </div>
                       </div>
                       <c:set var="totalPrice" value="${totalPrice + entry.key.productPrice * entry.value}"/>
                       <c:set var="totalProducts" value="${totalProducts + entry.value}"/>
                    </div>
                </div>
                </c:forEach>
            </c:if>
            <div class="total">
                <div>合计：${totalProducts != null ? totalProducts : 0}件</div>
                <div>合计：${totalPrice != null ? totalPrice : 0}元</div>
            </div>

            <div class="receiver-info">
                <legend>收货信息</legend>
                <label for="receiveAddress">收货地址</label>
                <input type="text" id="receiveAddress" name="receive_address" required><br>
                <label for="receiveName">收件人姓名</label>
                <input type="text" id="receiveName" name="receive_name" required><br>
                <label for="receivePhone">收件人号码</label>
                <input type="tel" id="receivePhone" name="receive_phone" required pattern="^\d{11}$" placeholder="请输入11位手机号"><br>
            </div>

            <div class="submit-option">
                <input type="submit" value="提交订单">
            </div>
        </form>
        <c:if test="${empty product2Buy}">
            <div class="empty-notice">
                没有选中商品，返回购物主页
            </div>
        </c:if>
    </div>
</body>
</html>
