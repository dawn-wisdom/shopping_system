<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>
    <div class="container">
        <div class="slogan">
            <h1>注册账号，开启购物之旅</h1>
        </div>
            <div class="form-section">
                <form action="RegisterServlet" method="post">
                    <a href="login.jsp">
                        <button type="button">已有账号，去登录</button>
                    </a>
                    用户名： <input type="text" id="userId" name="userName" placeholder="输入用户名"><br>
                    密码：  <input type="password" id="pwdId1" name="pwdName1" placeholder="输入密码"><br>
                    确认密码 <input type="password" id="pwdId2" name="pwdName2" placeholder="确认密码"><br>
                    邮箱  <input type="text" id="email" name="emailName" placeholder="填写邮箱"><br>
                    联系电话 <input type="text" id="phone" name="phoneName" placeholder="填写号码"><br>
                    <button type="submit">注册</button>
                    <!-- 显示错误提示 -->
                    <c:if test="${not empty errorMessage}">
                        <div class="error-message">
                            ${errorMessage}
                        </div>
                    </c:if>
                </form>
    </div>
</body>
</html>