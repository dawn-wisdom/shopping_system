<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>订单管理</title>
	<script>
        function updateOrderStatus(button, currentStatus, orderId) {
            // 获取对应 id 的元素
            var statusElement = document.getElementById("status-" + orderId);
             // 获取该元素的文本内容
                var statusText = statusElement.innerText;
             console.log(statusText)
             if(statusText==="已支付")
             {
                var newStatus=2;
                statusElement.innerText="已发货"
                location.href = "${pageContext.request.contextPath}/UpdateOrderStatus?orderId="
                        + orderId + "&newStatus=" + newStatus;
             }
        }
    </script>
</head>
<body class="main">
	<!-- 使用了自定义标签 -->
	<p:user/>
	<jsp:include page="head.jsp" />
	<jsp:include page="menu.jsp" />
        <div>
            <table cellspacing="0" class="infocontent">
                <tr>
                    <td style="padding:20px"><p><b>本店订单</b></p>
                        <p>
                            共有<font style="color:#FF0000" >${orders.size()}</font>订单
                        </p>
                        <table width="100%" border="0" cellspacing="0" class="tableopen">
                            <tr>
                                <td bgcolor="#A3E6DF" class="tableopentd01">订单号</td>
                                <td bgcolor="#A3D7E6" class="tableopentd01">收件人</td>
                                <td bgcolor="#A3CCE6" class="tableopentd01">订单时间</td>
                                <td bgcolor="#A3B6E6" class="tableopentd01">状态</td>
                                <td bgcolor="#A3E2E6" class="tableopentd01">操作</td>
                            </tr>
                            <c:forEach items="${orders}" var="order">
                                <tr>
                                    <td class="tableopentd02">${order.orderId}</td>
                                    <td class="tableopentd02">${order.receive_name }</td>
                                    <td class="tableopentd02">${order.order_time}</td>
                                    <td class="tableopentd02" id="status-${order.orderId}">
                                        <c:choose>
                                            <c:when test="${order.status == 0}">未支付</c:when>
                                            <c:when test="${order.status == 1}">已支付</c:when>
                                            <c:when test="${order.status == 2}">已发货</c:when>
                                            <c:when test="${order.status == 3}">已送达</c:when>
                                            <c:otherwise>未知状态</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="tableopentd03">
                                        <a href="${pageContext.request.contextPath}/ShowOrderDetail?orderId=${order.orderId}">查看</a>&nbsp;&nbsp;

                                        <a href="${pageContext.request.contextPath}/DeleteOrder?orderId=${order.orderId}">删除</a>
                                        <c:if test="${order.status == 1}">
                                            <button class="updateStatusButton"
                                                    onclick="updateOrderStatus(this, ${order.status}, ${order.orderId})">
                                                确认发货
                                            </button>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
	<%@ include file="foot.jsp" %>
</body>
</html>
