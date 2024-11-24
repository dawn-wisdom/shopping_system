<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>支付页面</title>
    <link rel="stylesheet" type="text/css" href="css/payment.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/qrcodejs/1.0.0/qrcode.min.js"></script>
    <script>
        // 显示模态框
        function showModal() {
            document.getElementById("payModal").style.display = "block";
            document.getElementById("overlay").style.display = "block";
        }
        // 支付完毕，关闭模态框
        function closeModal(orderId) {
           //更新订单状态
          location.href = "${pageContext.request.contextPath}/UpdateOrderStatus?orderId="
                  + orderId + "&newStatus=" + 1;
            document.getElementById("payModal").style.display = "none";
            document.getElementById("overlay").style.display = "none";
        }
        // 支付按钮点击事件
        function pay4order(orderId,price) {
            var qrCodeData = `alipayqr://platformapi/startapp?saId=10000007&qrcode=https%3A%2F%2Fqr.alipay.com%2Fyour-fixed-code&amount=${price}&orderId=${orderId}`;
            // 生成支付二维码
            new QRCode(document.getElementById("qrcode"), {
                text: qrCodeData,
                width: 200,
                height: 200
            });
            // 显示模态框
            showModal();
        };
    </script>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
    <!-- 获取订单号和总价 -->
    <c:set var="orderId" value="${param.orderId}" />
    <c:set var="totalCost" value="${param.total_cost}" />
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />

    <!-- 注入前端的 JavaScript 变量 -->
    <script>
        // 将转换后的变量注入到前端
        const orderId = ${orderId};
        const price = ${totalCost};
        const contextPath = "${contextPath}";
    </script>

    <div class="container">
        <div class="notice">请于15分钟内完成支付</div>
        <div class="orderId">订单号：${orderId}</div>
        <div class="total-cost">价格：<b>${totalCost}</div>
        <button id="payButton" onclick="pay4order(orderId, price)">支付</button>
    </div>
    <!-- 模态框和遮罩层 -->
    <div class="modal-overlay" id="overlay"></div>
    <div id="payModal">
        <h3>请扫码支付</h3>
        <div id="qrcode"></div>
        <button onclick="closeModal(orderId)">我已支付</button>
    </div>
</body>

</html>