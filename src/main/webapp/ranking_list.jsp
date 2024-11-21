
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>销售榜单</title>
</head>
<body>
<div class="container">
    <h1 class="mt-4 mb-4">商品销售榜单</h1>
    <table class="table table-bordered table-hover">

            <tr>
                <th>商品编号</th>
                <th>商品名称</th>
                <th>商品图片</th>
                <th>销量</th>
            </tr>

            <c:forEach var="entry" items="${leaderboard}">
                <tr>
                    <td>${entry.key.productId}</td>
                    <td>${entry.key.productName}</td>
                    <td><img src="${entry.key.productImage}" alt="${entry.key.productName}" style="width:100px;height:100px;"></td>
                    <td>${entry.value}</td>
                </tr>
            </c:forEach>

    </table>

    <!-- 分页控件 -->
    <div>
        <c:if test="${currentPage > 1}">
            <a href="ProductsSaled?page=${currentPage - 1}">上一页</a>
        </c:if>

        <span>页 ${currentPage} / ${totalPages}</span>

        <c:if test="${currentPage < totalPages}">
            <a href="ProductsSaled?page=${currentPage + 1}">下一页</a>
        </c:if>
    </div>
</div>
</body>
</html>
