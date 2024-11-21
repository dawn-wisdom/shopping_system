<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>订单详情页</title>
</head>
<body>
<table width="100%" border="0" cellspacing="0">
    <tr>
        <td>
            <p>订单编号:${order.orderId}</p>
        </td>
    </tr>
    <tr>
        <td>
            <table cellspacing="1" class="carttable">
                <tr>
                    <td width="10%">序号</td>
                    <td width="40%">商品名称</td>
                    <td width="10%">价格</td>
                    <td width="10%">数量</td>
                    <td width="10%">小计</td>
                </tr>
            </table>
            <c:forEach items="${order.orderItems}" var="item" varStatus="vs">
                <table width="100%" border="0" cellspacing="0">
                    <tr>
                        <td width="10%">${vs.count }</td>
                        <td width="40%">${item.product.productName}</td>
                        <td width="10%">${item.product.productPrice }</td>
                        <td width="10%">${item.quantity }</td>
                        <td width="10%">${item.quantity*item.product.productPrice }</td>
                    </tr>
                </table>
            </c:forEach>
            <table cellspacing="1" class="carttable">
                <tr>
                    <td style="text-align:right; padding-right:40px;"><font
                        style="color:#FF0000">合计：&nbsp;&nbsp;${order.total_cost}</font>
                    </td>
                </tr>
            </table>
            <p>
                收货地址：${order.receive_address }&nbsp;&nbsp;&nbsp;&nbsp;<br />
                收货人：&nbsp;&nbsp;&nbsp;&nbsp;${order.receive_name }&nbsp;&nbsp;&nbsp;&nbsp;<br />
                联系方式：${order.receive_phone }&nbsp;&nbsp;&nbsp;&nbsp;
            </p>
            <hr>
        </td>
    </tr>
</table>
</body>