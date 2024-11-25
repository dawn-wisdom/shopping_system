<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>支付成功</title>
	<style>
	    /* CSS 文件 */
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* 使内容垂直居中 */
            background-color: #f9f9f9; /* 可选：设置背景颜色 */
            font-family: Arial, sans-serif;
        }

        .success_logo {
            display: flex;
            align-items: center;
            margin-right: 10px; /* 图片与文字之间的间距 */
        }

        .success_logo img {
            max-width: 50px; /* 设置图片大小 */
            max-height: 50px; /* 避免图片过大 */
        }

        .text {
            font-size: 20px;
            color: #333; /* 可选：设置文字颜色 */
        }

	</style>
</head>

<body>
    <div class="success_logo">
        <img src="img/checkmark.png">
    </div>
    <div class="text">下单成功，请您耐心等待</div>
</body>
</html>