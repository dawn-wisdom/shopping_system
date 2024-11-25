<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import= "entity.Product" %>
<%@ include file="head.jsp"%>
<html>
<head>
    <title>商品详情页</title>
    <link rel="stylesheet" type="text/css" href="css/product_detail.css">
</head>
<body>
    <div class="product-container">
        <div class="product-image-container">
            <img src="${selected_product.productImage}" alt="${selected_product.productName}" />
        </div>
        <div class="product-details">
            <div class="product-name">
                ${selected_product.productName}
            </div>
            <div class="product-description">
                ${selected_product.description}
            </div>

            <div class="product-category">
                类别: ${selected_product.category}
            </div>

            <div class="product-price">
                价格: $${selected_product.productPrice}
            </div>

            <div class="product-quantity-pnum">
                <label for="quantity}">选择数量:</label>
                <select class="quantity" id="quantity">
                    <c:forEach var="i" begin="1" end="10">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
                 <div class="product-pnum">
                        库存: ${selected_product.pnum}
                 </div>
            </div>
            <div class="product-operation-container">
                <div class="add-to-cart">
                    <a href="${pageContext.request.contextPath}/AddToCartServlet?productId=${selected_product.productId}">
                        <button type="button">加入购物车</button>
                    </a>
                </div>
                <div class="buy-immediately">
                    <a id="buy_immediate" href="${pageContext.request.contextPath}/Select2BuyServlet?productId=${selected_product.productId}&quantity=1">
                        <button type="button">立即购买</button>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <script>
        document.getElementById("quantity").addEventListener("change", function () {
            const selectedQuantity = this.value;
            const buy_immediate_link = document.getElementById("buy_immediate");
            const productId = "${selected_product.productId}"; // 从 JSP 动态获取

            // 更新链接的 href
            buy_immediate_link.href = 'Select2BuyServlet?productId='+productId+'&quantity='+selectedQuantity;
        });
    </script>
</body>
</html>