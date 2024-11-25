
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>销售榜单</title>
    <link rel="stylesheet" type="text/css" href="css/ranking_list.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-4 mb-4">商品销售榜单</h1>

        <div class="leaderboard">
            <div class="leaderboard-header">
                <div class="header-item">商品编号</div>
                <div class="header-item">商品名称</div>
                <div class="header-item">商品图片</div>
                <div class="header-item">销量</div>
            </div>

            <c:forEach var="entry" items="${leaderboard}">
                <div class="leaderboard-item">
                    <div class="leaderboard-item-content">${entry.key.productId}</div>
                    <div class="leaderboard-item-content">${entry.key.productName}</div>
                    <div class="leaderboard-item-content">
                        <img src="${entry.key.productImage}" alt="${entry.key.productName}" class="product-image"/>
                    </div>
                    <div class="leaderboard-item-content">${entry.value}</div>
                </div>
            </c:forEach>
        </div>

        <!-- 分页控件 -->
        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="ProductsSaled?page=${currentPage - 1}" class="page-link">上一页</a>
            </c:if>

            <span class="current-page">页 ${currentPage} / ${totalPages}</span>

            <c:if test="${currentPage < totalPages}">
                <a href="ProductsSaled?page=${currentPage + 1}" class="page-link">下一页</a>
            </c:if>
        </div>
    </div>

</body>
</html>
