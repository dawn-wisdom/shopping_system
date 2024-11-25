<%@ page language="java" import="java.util.*, entity.Order" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import= "entity.Product" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ include file="head.jsp"%>
<html>
<head>
    <title>购物车</title>
    <link rel="stylesheet" type="text/css" href="css/cart.css">
    <script>
    	function changeProductNum(count, totalCount, id) {
    		count = parseInt(count);
    		totalCount = parseInt(totalCount);
    		//如果数量为0，判断是否要删除商品
    		if (count == 0) {
    			var flag = window.confirm("确认删除商品吗?");

    			if (!flag) {
    				count = 1;
    			}
    		}
    		if (count > totalCount) {
    			alert("库存不足");
    			count = totalCount;
    		}
    		location.href = "${pageContext.request.contextPath}/CartChange?productId="
    				+ id + "&quantity=" + count;
    	}
    </script>

    <script>
        // 收集选中的商品 ID，并存入隐藏字段
        function collectSelectedItems() {
            var selectedItems = [];
            var selectedQuantity=[];
            // 获取所有的勾选框
            var checkboxes = document.getElementsByName("selectedItems");
            // 遍历并检查是否被选中
            for (var i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].checked) {
                    selectedItems.push(checkboxes[i].value);  // 将商品 ID 添加到数组中
                }
            }

            document.getElementById("selectedItemIds").value = selectedItems.join(",");
        }
    </script>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            // 获取复选框和统计显示的元素
            const checkboxes = document.querySelectorAll('.item-checkbox');
            const totalItemsElement = document.getElementById('totalItems');
            const totalPriceElement = document.getElementById('totalPrice');

            // 更新统计数据
            function updateStats() {
                let totalItems = 0;
                let totalPrice = 0.0;

                checkboxes.forEach(checkbox => {
                    if (checkbox.checked) {
                        const price = parseFloat(checkbox.getAttribute('data-price'));
                        const quantity = parseInt(checkbox.getAttribute('data-quantity'), 10);
                        totalItems += quantity;
                        totalPrice += price * quantity;
                    }
                });

                totalItemsElement.textContent = totalItems;
                totalPriceElement.textContent = totalPrice.toFixed(2);
            }

            // 为每个复选框绑定事件监听器
            checkboxes.forEach(checkbox => {
                checkbox.addEventListener('change', updateStats);
            });

            // 初始化统计数据
            updateStats();
        });
    </script>

</head>
<body>
    <form action="Select2BuyServlet" method="post" id="orderForm">

        <c:if test="${not empty cart}">
            <div class="cart-container">
                <c:forEach items="${cart}" var="entry" varStatus="vs">
                    <div class="product-container">
                       <div class="container1">
                            <div class="selectedBox">
                                <input type="checkbox" name="selectedItems" value="${entry.key.productId}"
                                 data-price="${entry.key.productPrice}" data-quantity="${entry.value}"  class="item-checkbox">
                            </div>
                            <div class="rank">
                                    ${vs.count}
                            </div>
                        </div>
                        <div class="product-image">
                            <img src="${entry.key.productImage}" alt="${entry.key.productName}">
                        </div>

                        <div class="container2">
                            <div class="product-name">
                                ${entry.key.productName}
                            </div>

                            <div class="product-price">
                                $${entry.key.productPrice}
                            </div>
                            <div class="product-quantity">
                                <!-- 商品减少按钮 -->
                                <input type="button" value="-"
                                       id="decrease-btn-${entry.key.productId}"
                                       class="decrease-btn"
                                       data-product-id="${entry.key.productId}"
                                       data-quantity="${entry.value}"
                                       onclick="changeProductNum(${entry.value - 1}, '${entry.key.pnum}', '${entry.key.productId}')">

                                <!-- 显示商品数量 -->
                                <input name="quantity" type="text"
                                       id="quantity-${entry.key.productId}"
                                       value="${entry.value}"
                                       style="width:40px;text-align:center">

                                <!-- 增加按钮 -->
                                <input type="button" value="+"
                                       id="increase-btn-${entry.key.productId}"
                                       class="increase-btn"
                                       onclick="changeProductNum(${entry.value + 1}, '${entry.key.pnum}', '${entry.key.productId}')">
                            </div>
                        </div>

                        <div class="container3">
                            <a href="${pageContext.request.contextPath}/CartChange?productId=${entry.key.productId}&quantity=0">移除</button></a>
                        </div>
                    </div>

                </c:forEach>
            </div>
                <div class="bill">
                    <!-- 账单部分 -->
                    <div class="bill">
                        <div class="products-kind">
                            总计: <span id="totalItems">0</span> 件
                        </div>
                        <div class="total_cost">
                            合计: <span id="totalPrice">0.00</span> 元
                        </div>
                    </div>
                   <!-- 隐藏的输入字段，用于存储选中商品的 ID 列表 -->
                    <input type="hidden" id="selectedItemIds" name="selectedItemIds" value="">
                     <!--结账 -->
                     <div class="submit-button">
                        <input type="submit" value="提交订单" onclick="collectSelectedItems()" />
                    </div>
                </div>
        </c:if>
    </form>
    <c:if test="${empty cart}">
        <div class="empty-notice">购物车为空</class>
    </c:if>

</body>
</html>