<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="head_seller.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>订单管理</title>
	<link rel="stylesheet" type="text/css" href="css/orderManage.css">
</head>
<body>
    <div class="main">
        <div class="page-title">
            全部订单
        </div>
        <div class="total-orders">
            总共 ${orders.size()} 个订单
        </div>
        <div class="orders-grid">
            <c:forEach items="${orders}" var="order">
                <div class="order-container">
                    <div class="order-header">
                        <div class="order-header-item">
                            <div class="order-header-label">订单建立时间:</div>
                            <div>${order.order_time}</div>
                        </div>
                        <div class="order-header-item">
                            <div class="order-header-label">用户:</div>
                            <div>${order.customerId}</div>
                        </div>
                        <div class="order-header-item">
                            <div class="order-header-label">总价：</div>
                            <div>$${order.total_cost}</div>
                        </div>
                        <div class="order-header-item">
                            <div class="order-header-label">订单编号:</div>
                            <div>${order.orderId}</div>
                        </div>
                        <div class="order-header-item">
                            <div class="order-header-label">订单状态:</div>
                            <div>
                                <c:choose>
                                    <c:when test="${order.status == 0}">未支付</c:when>
                                    <c:when test="${order.status == 1}">待发货</c:when>
                                    <c:when test="${order.status == 2}">待送达</c:when>
                                    <c:when test="${order.status == 3}">已送达</c:when>
                                    <c:otherwise>未知状态</c:otherwise>
                                </c:choose>
                            </div>
                            <c:if test="${order.status == 1}">
                                <a href="${pageContext.request.contextPath}/UpdateOrderStatus?orderId=${order.orderId}&newStatus=${order.status+1}">
                                    <button class="updateStatusButton" >
                                        货物已送出？
                                    </button>
                                </a>
                            </c:if>
                        </div>


                        <div class="order-header-item">
                            <div class="order-header-label">操作:</div>
                            <div class="operations">
                                <a href="${pageContext.request.contextPath}/DeleteOrder?orderId=${order.orderId}">
                                    <button type="button">删除</button>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="order-details-grid">
                        <c:forEach var="item" items="${order.orderItems}">
                            <div class="product-image-container">
                                <img src="${item.product.productImage}" alt="图片暂时无法显示">
                            </div>
                            <div class="product-details">
                                <div class="product-name">
                                    ${item.product.productName}
                                </div>
                                <div class="product-quantity">
                                    数量：${item.quantity}
                                </div>
                                <div class="product-cost">
                                    小计：${item.quantity * item.product.productPrice}
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</body>
</html>
