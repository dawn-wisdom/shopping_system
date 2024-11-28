<%@ page language="java" pageEncoding="UTF-8"%>

    <link rel="stylesheet" type="text/css" href="css/head_seller.css">
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
        document.addEventListener('DOMContentLoaded', () => {
            const avatar = document.getElementById('avatar2');
            const dropdownMenu = document.getElementById('dropdownMenu2');

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
            <a href="ShowProductInStore" class="header-link">
            <img class="logo" src="img/logo.png">
            </a>
        </div>


        <div class="header-right-section">

            <div class="user-menu">
                <img src="img/hamburger-menu.png" alt="暂时无法显示" class="avatar" id="avatar">
                <div class="dropdown-menu" id="dropdownMenu">
                    <a href="addProduct.jsp">添加商品</a>
                    <a href="ProductsSaled">销量统计</a>
                    <a href="PurchaseLog">用户管理</a>
                    <a class="orders-link header-link" href="OrderManage?request_type=1">订单管理</a>
                </div>
            </div>

            <div class="user-menu2">
                <img src="img/customer.png" alt="暂时无法显示" class="avatar" id="avatar2">
                <div class="dropdown-menu" id="dropdownMenu2">
                    <a href="login.jsp">切换账号</a>
                    <a href="Logout">注销</a>
                </div>
            </div>

        </div>
    </div>
