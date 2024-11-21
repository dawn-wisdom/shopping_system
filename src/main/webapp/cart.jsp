<%@ page language="java" import="java.util.*, entity.Order" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import= "entity.Product" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<html>
<head>
    <title>购物车</title>
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

            // 将选中的商品 ID 转为字符串存入隐藏字段
            document.getElementById("selectedItemIds").value = selectedItems.join(",");
        }
    </script>
</head>
<body>
    <h1>购物车</h1>
    <form action="Select2BuyServlet" method="post" id="orderForm">
        <c:if test="${not empty cart}">
                <table border="1">
                    <tr>
                        <th></th>
                        <th>序号</th>
                        <th>类别</th>
                        <th>商品名字</th>
                        <th>售价</th>
                        <th>图片</th>
                        <th>店名</th>
                        <th>数量</th>
                    </tr>
                    <c:forEach items="${cart}" var="entry" varStatus="vs">
                        <tr>

                            <td><input type="checkbox" name="selectedItems" value="${entry.key.productId}"></td>
                            <td width="10%">${vs.count}</td>
                             <td>${entry.key.category}</td>
                             <td>${entry.key.productName}</td>
                             <td>$${entry.key.productPrice}</td>
                             <td><img src="${entry.key.productImage}" alt="${entry.key.productName}" width="50"></td>
                             <td>${entry.key.store}</td>
                             <td>
                                <!-- 减少商品数量 -->
                                <input type="button" value='-' style="width:20px"
                                       onclick="changeProductNum('${entry.value-1}','${entry.key.pnum}','${entry.key.productId}')">
                                 <!-- 商品数量显示 -->
                                <input name="text" type="text" value="${entry.value}" style="width:40px;text-align:center" />
                                <!-- 增加商品数量 -->
                                <input type="button" value='+' style="width:20px"
                                       onclick="changeProductNum('${entry.value+1}','${entry.key.pnum}','${entry.key.productId}')">
                             </td>
                        </tr>
                    </c:forEach>
                </table>
        </c:if>
        <!-- 隐藏的输入字段，用于存储选中商品的 ID 列表 -->
        <input type="hidden" id="selectedItemIds" name="selectedItemIds" value="">
         <!--结账 -->
         <input type="submit" value="提交订单" onclick="collectSelectedItems()" />
    </form>
    <c:if test="${empty cart}">
        <p>购物车为空。</p>
    </c:if>

    <!--继续购物 -->
    <a href="${pageContext.request.contextPath}/ShowAllProducts">继续购物
    </a>


</body>
</html>