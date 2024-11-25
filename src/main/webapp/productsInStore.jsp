<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import= "entity.Product" %>
<%@ include file="head_seller.jsp" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>产品列表</title>
    <link rel="stylesheet" type="text/css" href="css/productsInStore.css">
</head>
<body>
    <a href="${pageContext.request.contextPath}/addProduct.jsp">添加商品</a>
    <a href="${pageContext.request.contextPath}/OrderManage?request_type=1">订单管理</a>
    <a href="${pageContext.request.contextPath}/ProductsSaled">销售统计</a>
    <a href="${pageContext.request.contextPath}/PurchaseLog">客户管理</a>
    <div class="products-container">
        <c:forEach var="product" items="${productsInStore}">
            <div class="product-item">
                <div class="product-id">商品编号：${product.productId}</div>
                <div class="product-image">
                    <img src="${product.productImage}" alt="Product Image">
                </div>
                <div class="container1">

                    <div class="product-name">商品名称：${product.productName}</div>
                    <div class="product-price">价格：${product.productPrice}</div>
                    <div class="product-stock">库存：${product.pnum}</div>
                </div>

                <div class="product-actions">
                    <a href="${pageContext.request.contextPath}/DeleteProductServlet?productId=${product.productId}" class="delete-btn">下架商品</a>
                    <a href="${pageContext.request.contextPath}/GetOriginProduct?productId=${product.productId}" class="edit-btn">点击编辑</a>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
