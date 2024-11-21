<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<script>
    function toggleStoreNameField() {
        const role = document.getElementById("role").value;
        const storeNameField = document.getElementById("storeNameField");
        if (role === "seller") {
            storeNameField.style.display = "block";
        } else {
            storeNameField.style.display = "none";
        }
    }
</script>
</head>
<body>
	<form action="RegisterServlet" method="post">
			<a href="login.jsp">
		<button type="button">已有账号，去登录</button><br>
	</a>
        <label for="role">角色:</label>
        <select id="role" name="role" required onchange="toggleStoreNameField()"> <!-- 添加 onchange 事件 -->
            <option value="customer">顾客</option>
            <option value="seller">商家</option>
        </select><br>
		用户名： <input type="text" id="userId" name="userName" placeholder="输入用户名"><br>
		密码：  <input type="password" id="pwdId1" name="pwdName1" placeholder="输入密码"><br>
		确认密码 <input type="password" id="pwdId2" name="pwdName2" placeholder="确认密码"><br>
		邮箱  <input type="text" id="email" name="emailName" placeholder="填写邮箱"><br>
		联系电话 <input type="text" id="phone" name="phoneName" placeholder="填写号码"><br>
        <div id="storeNameField" style="display:none;">
            <label for="storeName">为店铺取一个名字:</label>
            <input type="text" id="storeName" name="storeName" placeholder="店铺名字"><br>
        </div>
		<button type="submit">注册</button>

		
	</form>
</body>
</html>