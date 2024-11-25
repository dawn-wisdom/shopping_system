<%@ page language="java" pageEncoding="UTF-8"%>

    <link rel="stylesheet" type="text/css" href="css/head.css">
    <link rel="stylesheet" type="text/css" href="css/error.css">
    <script>
        document.addEventListener('DOMContentLoaded', () => {
            const avatar = document.getElementById('avatar');
            const dropdownMenu = document.getElementById('dropdownMenu');

            // 点击头像显示/隐藏菜单
            avatar.addEventListener('click', () => {
                const isVisible = dropdownMenu.style.display === 'block';
                dropdownMenu.style.display = isVisible ? 'none' : 'block';
            });

            // 点击页面其他区域关闭菜单
            document.addEventListener('click', (event) => {
                if (!avatar.contains(event.target) && !dropdownMenu.contains(event.target)) {
                    dropdownMenu.style.display = 'none';
                }
            });
        });

    </script>

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const modal = document.getElementById("errorModal");
            // 模拟后端登录状态，可以通过后端 JSP 动态输出
            const isLoggedIn = <%= session.getAttribute("customer") != null ? "true" : "false" %>;
            console.log(isLoggedIn)
            // 获取所有需要登录的按钮
            const actionButtons = document.querySelectorAll(".action-button");
            let cnt = 1
            actionButtons.forEach(button => {
                console.log(cnt)
                button.addEventListener("click", function (event) {
                    // 检查是否需要登录
                    if (button.dataset.requiresLogin === "true" && !isLoggedIn) {
                        event.preventDefault(); // 阻止默认行为
                        modal.style.display = "block"; // 显示模态框
                    }
                });
                cnt=cnt+1
            });
        });
    </script>

    <div class="header">
        <div class="header-left-section">
            <a href="ShowAllProducts" class="header-link">
            <img class="logo" src="img/logo.png">
            </a>
        </div>
        <div class="header-middle-section">
            <form action="ShowAllProductsServlet" method="get">
                <input class="search-bar" type="text" name="keyword" placeholder="查找商品">
                <button class="search-button" type="submit">
                    <img class="search-icon" src="img/search-icon.png">
                </button>
            </form>
        </div>

        <div class="header-right-section">

            <a class="cart-link header-link action-button" href="cart.jsp"  data-requires-login="true">
              <img class="cart-icon" src="img/cart-icon.png">
              <div class="cart-quantity js-cart-quantity"></div>
            </a>

            <div class="user-menu">
                <img src="img/customer.png" alt="暂时无法显示" class="avatar" id="avatar">
                <div class="dropdown-menu" id="dropdownMenu">
                    <a href="login.jsp">登录</a>
                    <a href="register.jsp">注册</a>
                    <a href="Logout">注销</a>
                    <a class="orders-link header-link action-button" href="OrderManage?request_type=0" data-requires-login="true">历史订单</a>
                </div>
            </div>
        </div>
    </div>

     <div id="errorModal" class="modal" style="display: none;">
          <div class="modal-content">
              <span class="close">&times;</span>
              <p>您还没有登录</p>
              <a href="${pageContext.request.contextPath}/login.jsp">
                  <button class="login-button">去登录</button>
              </a>
          </div>
      </div>
