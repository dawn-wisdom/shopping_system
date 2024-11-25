<%@ page language="java" pageEncoding="UTF-8"%>



    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css">
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

    <div class="header">
        <div class="header-left-section">
            <a href="ShowAllProducts" class="header-link">
            <img class="logo" src="img/logo.png">
            </a>
        </div>
        <div class="header-middle-section">
            <input class="search-bar" type="text" placeholder="Search">
            <button class="search-button">
            <img class="search-icon" src="img/search-icon.png">
            </button>
        </div>

        <div class="header-right-section">


            <a class="cart-link header-link" href="cart.jsp">
              <img class="cart-icon" src="img/cart-icon.png">
              <div class="cart-quantity js-cart-quantity"></div>
            </a>

            <div class="user-menu">
                <img src="img/customer.png" alt="暂时无法显示" class="avatar" id="avatar">
                <div class="dropdown-menu" id="dropdownMenu">
                    <a href="login.jsp">登录</a>
                    <a href="register.jsp">注册</a>
                    <a href="Logout">注销</a>
                    <a class="orders-link header-link" href="OrderManage?request_type=0">查看订单</a>
                </div>
            </div>
        </div>
    </div>
