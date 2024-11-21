<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
</head>
<body>
	<form action="LoginServlet" method="post">
	<a href="register.jsp">
		<button type="button">没有账号？注册一个</button><br>
	</a>
        <label for="role">角色:</label>
        <select id="role" name="role" required>
            <option value="customer">顾客</option>
            <option value="seller">商家</option>
        </select><br>
		用户名： <input type="text" id="userId" name="userName" placeholder="输入用户名"><br>
		密码：  <input type="password" id="pwdId" name="pwdName" placeholder="输入密码"><br>
		<button type="submit">登录</button> <br>

	</form>

</body>

</html>