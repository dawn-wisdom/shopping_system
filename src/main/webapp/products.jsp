<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import= "entity.Product" %>
<html>
<head>
    <title>产品列表</title>
    <link rel="stylesheet" type="text/css" href="css/products.css">
</head>
<body>

<jsp:include page="head.jsp"/>
<div class="main">
    <div class="products-grid">
        <c:if test="${not empty products}">
            <c:forEach var="product" items="${products}">
                <div class="product-container" data-product-id="${product.productId}">
                    <!-- 产品图片 -->
                    <div class="product-image-container">
                        <img class="product-image" src="${product.productImage}" alt="${product.productName}">
                    </div>

                    <!-- 产品名称 -->
                    <div class="product-name limit-text-to-2-lines">
                        ${product.productName}
                    </div>
                    <!-- 产品价格 -->
                    <div class="product-price">
                        $${product.productPrice}
                    </div>

                    <!-- 产品数量选择 -->
                    <div class="product-quantity-pnum">
                        <label for="quantity-${product.productId}">选择数量:</label>
                        <select class="quantity" id="quantity-${product.productId}">
                            <c:forEach var="i" begin="1" end="10">
                                <option value="${i}">${i}</option>
                            </c:forEach>
                        </select>
                         <div class="product-pnum">
                                剩余: ${product.pnum}
                         </div>
                    </div>
                    <div class="product-spacer"></div>
                    <!-- 操作按钮 -->
                    <div class="product-operation-container">
                        <div class="add-to-cart">
                            <a href="${pageContext.request.contextPath}/AddToCartServlet?productId=${product.productId}">
                                <button type="button">加入购物车</button>
                            </a>
                        </div>
                        <div class="buy-immediately">
                            <a id="buy_immediate-${product.productId}" href="${pageContext.request.contextPath}/Select2BuyServlet?productId=${product.productId}&quantity=1">
                                <button type="button">立即购买</button>
                            </a>
                        </div>
                        <div class="add-to-cart">
                            <a href="${pageContext.request.contextPath}/ShowProductDetailServlet?productId=${product.productId}">
                                <button type="button">查看详情</button>
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
<script>
document.addEventListener("DOMContentLoaded", function () {
    const quantitySelectors = document.querySelectorAll(".quantity");

    quantitySelectors.forEach((selector) => {
        selector.addEventListener("change", function () {
            const selectedQuantity = this.value;
            const productContainer = this.closest(".product-container");

            // 验证 productContainer 是否存在
            if (!productContainer) {
                console.error("Error: product-container not found.");
                return;
            }

            let productId = productContainer.getAttribute("data-product-id");
            console.log("Product ID:", productId)
            console.log(`#buy_immediate-${productId}`)
            console.log('buy_immediate-'+productId)
            const buyImmediateLink = productContainer.querySelector('#buy_immediate-'+productId);

            // 验证 buyImmediateLink 是否存在
            if (!buyImmediateLink) {
                console.error(`Error: Buy immediate link not found for product ID ${productId}`);
                return;
            }

            // 更新链接的 href
            buyImmediateLink.href = 'Select2BuyServlet?productId='+productId+'&quantity='+selectedQuantity;
        });
    });
});
</script>


</body>
</html>