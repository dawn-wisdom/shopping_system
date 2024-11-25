<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>日志管理</title>
        <link rel="stylesheet" type="text/css" href="css/purchaseLog.css">
    </head>

     <body>
         <div class="container">
             <h1 class="mt-4 mb-4">用户购买日志</h1>

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
                         <div class="log-item-content">${log.customerId}</div>
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