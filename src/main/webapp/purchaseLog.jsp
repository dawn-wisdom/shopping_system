<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>用户购买日志</h1>
<body>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>订单号</th>
                <th>用户 ID</th>
                <th>订单时间</th>
                <th>商品</th>

                <th>总价</th>
                <th>订单状态</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="log" items="${purchaseLogs}">
                <tr>
                    <td>${log.orderId}</td>
                    <td>${log.customerId}</td>
                    <td>${log.order_time}</td>
                    <td>
                        <ul>
                            <c:forEach var="item" items="${log.orderItems}">
                                <li>
                                    商品名: ${item.product.productName}, 数量: ${item.quantity}
                                </li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td>${log.total_cost}</td>
                    <td class="tableopentd02" id="status-${log.orderId}">
                        <c:choose>
                            <c:when test="${log.status == 0}">未支付</c:when>
                            <c:when test="${log.status == 1}">已支付</c:when>
                            <c:when test="${log.status == 2}">已发货</c:when>
                            <c:when test="${log.status == 3}">已送达</c:when>
                            <c:otherwise>未知状态</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    完整数据请下载
    <a href="${pageContext.request.contextPath}/ExportPurchaseLog" class="btn btn-primary">
        导出购买日志
    </a>
</body>