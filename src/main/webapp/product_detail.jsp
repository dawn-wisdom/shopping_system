<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import= "entity.Product" %>
<html>
<head>
    <title>商品详情页</title>
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
                window.location.href = "submitOrder.jsp?productId=" + productId + "&quantity=" + quantity;
            }
        }
    </script>
</head>
<body>
	<%
    // 获取产品列表
    Product product = (Product) request.getAttribute("selected_product");
	%>
    <h1><%= product.getProductName() %></h1>
        <img src="${product.getProductImage()}" alt="${product.getProductName()}" />
        类别:<%= product.getCategory()%></br>
        <%= product.getDescription()%></br>
        价格:$<%= product.getProductPrice()%></br>
        店家：<%= product.getStore()%></br>
        库存：<%= product.getPnum()%></br>
        <a href="${pageContext.request.contextPath}/AddToCartServlet?productId=${product.getProductId()}">加入购物车</a>
        <td><button onclick="showPurchaseDialog(${product.getProductId()})">立即购买</button></td>
</body>
</html>