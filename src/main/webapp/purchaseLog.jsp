<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="head_seller.jsp" %>
<html>
    <head>
        <title>日志管理</title>
        <link rel="stylesheet" type="text/css" href="css/purchaseLog.css">
        <script>
        function searchOrder() {
            var userId = document.getElementById("search-user-id").value.trim();  // 获取用户输入的ID
            if (userId) {
                var logs = document.querySelectorAll('.purchase-log-item');  // 获取所有订单项
                var found = false;

                // 遍历所有订单项，检查每个订单项的用户ID是否与搜索的ID匹配
                logs.forEach(function(log) {
                    var orderUserId = log.querySelector('.log-customer-id').innerText;
                    if (orderUserId === userId) {
                        log.scrollIntoView({ behavior: 'smooth', block: 'center' });  // 滚动到匹配的订单
                        log.classList.add('highlight');  // 给匹配的订单添加highlight类
                        found = true;
                    } else {
                        log.classList.remove('highlight');  // 清除之前可能高亮的订单
                    }
                });

            }
        }
        </script>


    </head>

     <body>
         <div class="container">
            <div class="title-container">
                 <h1 class="mt-4 mb-4">用户购买日志</h1>
                <!-- 搜索框和按钮 -->
                <div class="search-container">
                    <input type="text" id="search-user-id" placeholder="请输入用户ID" />
                    <button onclick="searchOrder()"><img class="search-icon" src="img/search-icon.png"></button>
                </div>
            </div>
             <div class="purchase-log">
                 <div class="purchase-log-header">
                     <div class="header-item">订单号</div>
                     <div class="header-item">用户 ID</div>
                     <div class="header-item">订单时间</div>
                     <div class="header-item">商品信息</div>
                     <div class="header-item">商品图片</div>
                     <div class="header-item">总价</div>
                     <div class="header-item">订单状态</div>
                 </div>

                 <c:forEach var="log" items="${purchaseLogs}">
                     <div class="purchase-log-item">
                         <div class="log-item-content">${log.orderId}</div>
                         <div class="log-item-content log-customer-id">${log.customerId}</div>
                         <div class="log-item-content">${log.order_time}</div>
                         <div class="log-item-content">
                             <ul>
                                 <c:forEach var="item" items="${log.orderItems}">
                                     <li>商品名: ${item.product.productName}, 数量: ${item.quantity}</li>
                                 </c:forEach>
                             </ul>
                         </div>
                         <div class="log-item-content">
                             <img src="${log.orderItems[0].product.productImage}" alt="商品图片" class="product-image">

                         </div>
                         <div class="log-item-content">${log.total_cost}</div>
                         <div class="log-item-content">
                             <c:choose>
                                 <c:when test="${log.status == 0}">未支付</c:when>
                                 <c:when test="${log.status == 1}">已支付</c:when>
                                 <c:when test="${log.status == 2}">已发货</c:when>
                                 <c:when test="${log.status == 3}">已送达</c:when>
                                 <c:otherwise>未知状态</c:otherwise>
                             </c:choose>
                         </div>
                     </div>
                 </c:forEach>
             </div>

             <!-- 下载日志按钮 -->
             <div class="export-log">
                 <a href="${pageContext.request.contextPath}/ExportPurchaseLog" class="btn">导出购买日志</a>
             </div>
         </div>
     </body>

</html>