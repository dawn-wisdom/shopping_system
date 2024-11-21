<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import= "entity.Product" %>
<html>
<head>
    <title>产品列表</title>
   <script>
        function showPurchaseDialog(productId) {
            // 获取输入框和按钮
            var quantity = prompt("请输入购买数量：");
            if (quantity != null && quantity > 0) {
                // 验证输入的数量是否合法
                if (isNaN(quantity) || quantity <= 0) {
                    alert("请输入有效的数量！");
                    return;
                }
                // 将选择的商品信息与数量传递给订单页面
                window.location.href = "Select2BuyServlet?productId=" + productId + "&quantity=" + quantity;
            }
        }
    </script>
</head>
<body>
<a href="login.jsp">登录</a>
<a href="register.jsp">注册</a>
<a href="${pageContext.request.contextPath}/Logout">注销</a>
<a href="cart.jsp?">查看购物车</a>
<a href="${pageContext.request.contextPath}/OrderManage?request_type=0">查询历史订单</a>
<h1>产品列表</h1>

<table>
    <tr>
        <th>Product ID</th>
        <th>Product Name</th>
        <th>Product Image</th>
        <th>Product Price</th>
        <th>店名</th>
        <th>库存</th>
        <th>加入购物车</th>
        <th>立即购买</th>
        <th>查看详情</th>
    </tr>

    <!-- 如果产品列表不为空，循环显示产品 -->
    <c:if test="${not empty products}">
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.productId}</td>
                <td>${product.productName}</td>
                <td>
                    <img src="${product.productImage}" alt="${product.productName}" width="100%" height="38"/>
                </td>
                <td>$${product.productPrice}</td>
                <td>${product.store}</td>
                <td>${product.pnum}</td>
                <td><a href="${pageContext.request.contextPath}/ShowProductDetailServlet?productId=${product.productId}">查看详情</a></td>
                <td><a href="${pageContext.request.contextPath}/AddToCartServlet?productId=${product.productId}">加入购物车</a></td>
                <td><button onclick="showPurchaseDialog(${product.productId})">立即购买</button></td>
            </tr>
        </c:forEach>
    </c:if>

    <!-- 如果产品列表为空，显示提示信息 -->
    <c:if test="${empty products}">
        <tr>
            <td colspan="9">No products found.</td>
        </tr>
    </c:if>
</table>

</body>
</html>