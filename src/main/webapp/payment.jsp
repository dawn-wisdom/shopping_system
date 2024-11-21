<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>支付页面</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/qrcodejs/1.0.0/qrcode.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        #payModal {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 300px;
            padding: 20px;
            background-color: white;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        #qrcode {
            margin: 10px auto;
        }
        .modal-overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
        }
        button {
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
    </style>
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
<body>
    <%
        // 获取订单号和总价
        String orderIdStr = request.getParameter("orderId");
        String totalCostStr = request.getParameter("total_cost");

        // 转换为整数和浮动数
        int orderId = (orderIdStr != null) ? Integer.parseInt(orderIdStr) : 0;
        double totalCost = (totalCostStr != null) ? Double.parseDouble(totalCostStr) : 0.0;

        // 获取应用的上下文路径
        String contextPath = request.getContextPath();
    %>
    <script>
        // 将转换后的变量注入到前端
        const orderId = <%= orderId %>;
        const price = <%= totalCost %>;
        const contextPath = "<%= contextPath %>";
    </script>

    <h1>支付页面</h1>
    <p>订单号：<b><%= orderId %></b></p>
    <p>价格：<b><%= totalCost %></b></p>
    <button id="payButton" onclick="pay4order(orderId, price)">支付</button>

    <!-- 模态框和遮罩层 -->
    <div class="modal-overlay" id="overlay"></div>
    <div id="payModal">
        <h3>请扫码支付</h3>
        <div id="qrcode"></div>
        <button onclick="closeModal(orderId)">我已支付</button>
    </div>
</body>
</html>
