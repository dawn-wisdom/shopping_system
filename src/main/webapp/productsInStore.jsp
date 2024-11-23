<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import= "entity.Product" %>
<html>
<head>
    <title>产品列表</title>

</head>
<body>
<a href="login.jsp">登录</a>

<h1>本店商品</h1>
	<table>
        <tr>
            <th>商品编号</th>
            <th>商品名称</th>
            <th>图片</th>
            <th>价格</th>
            <th>库存</th>
            <th>修改信息</th>
            <th>删除商品</th>
        </tr>
        <c:forEach var="product" items="${productsInStore}">
            <tr>
                <td>${product.productId}</td>
                <td>${product.productName}</td>
                <td><img src="${product.productImage}" alt="Product Image" height=300px width=300px /></td>
                <td>${product.productPrice}</td>
                <td>${product.pnum}</td>
                <td><a href="${pageContext.request.contextPath}/DeleteProductServlet?productId=${product.productId}">下架商品</a></td>
                <td><a href="${pageContext.request.contextPath}/GetOriginProduct?productId=${product.productId}">点击编辑</td>
            </tr>
        </c:forEach>
    </table>

    <a href="${pageContext.request.contextPath}/addProduct.jsp">添加商品</a>
    <a href="${pageContext.request.contextPath}/OrderManage?request_type=1">订单管理</a>
    <a href="${pageContext.request.contextPath}/ProductsSaled">销售统计</a>
    <a href="${pageContext.request.contextPath}/PurchaseLog">客户管理</a>
</body>
</html>