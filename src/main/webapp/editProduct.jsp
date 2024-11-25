<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="head_seller.jsp" %>
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/add_product.css">
</HEAD>
<body>
    <div class="container">
        <h1>编辑商品信息</h1>
        <form action="EditProductServlet" method="post" enctype="multipart/form-data" class="form-container">
            <input type="hidden" name="productId" value="${product.productId}" />
            <div class="form-item">
                <label for="productName">商品名称：</label>
                <input type="text" id="productName" name="productName" value="${product.productName}" class="input-text" required/>
            </div>
            <div class="form-item">
                <label for="productPrice">商品价格：</label>
                <input type="text" id="productPrice" name="productPrice" value="${product.productPrice}" class="input-text" required/>
            </div>
            <div class="form-item">
                <label for="pnum">商品数量：</label>
                <input type="text" id="pnum" name="pnum" value="${product.pnum}" class="input-text" required/>
            </div>
            <div class="form-item">
                <label for="category">商品类别：</label>
                <select name="category" id="category" value="${product.category}" class="select">
                    <option value="" selected="selected">--选择商品类别--</option>
                    <option value="books">书籍</option>
                    <option value="clothing">服装</option>
                    <option value="daily life">生活</option>
                    <option value="other">其他</option>
                </select>
            </div>
            <div class="form-item">
                <label for="image">更换商品图片：</label>
                <input type="file" id="image" name="image" value="${product.productImage}" class="input-file" />
            </div>
            <div class="form-item">
                <label for="description">商品描述：</label>
                <textarea id="description" name="description" class="textarea">${product.description}</textarea>
            </div>
            <div class="form-actions">
                <input type="submit" class="button-ok" value="确定">
                <input type="reset" class="button-cancel" value="重置">
                <input type="button" class="button-back" onclick="history.go(-1)" value="返回">
            </div>
        </form>
    </div>

	</form>
</body>
</HTML>