<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>

    <div class="container1">
        <div class="slogan">
            <h1>欢迎来到乐购网,购你所想</h1>
        </div>

        <div class="container2">
            <!-- 左侧图片 -->
            <div class="image-section">
                <img src="img/shopping_system.png" alt="暂时无法查看">
            </div>
            <!-- 右侧表单 -->
            <div class="form-section">
                <form action="LoginServlet" method="post">
                    <a href="register.jsp">
                        <button type="button">没有账号？注册一个</button>
                    </a>
                    <label for="role">角色:</label>
                    <select id="role" name="role" required>
                        <option value="customer">顾客</option>
                        <option value="seller">商家</option>
                    </select>
                    <label for="userId">用户名：</label>
                    <input type="text" id="userId" name="userName" placeholder="输入用户名">
                    <label for="pwdId">密码：</label>
                    <input type="password" id="pwdId" name="pwdName" placeholder="输入密码">
                    <button type="submit">登录</button>
                    <!-- 显示错误提示 -->
                    <c:if test="${not empty errorMessage}">
                        <div class="error-message">
                            ${errorMessage}
                        </div>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
