package controller;

import entity.Product;
import service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ProductService productService=new ProductService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 处理 GET 请求
        //通过id获取product
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = productService.findProductById(productId);
        HttpSession session = request.getSession();

        //获取购物车
        Map<Product,Integer> cart=(Map<Product, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<Product,Integer>();
            session.setAttribute("cart", cart);
        }
        Integer quantity = cart.get(product);
        if (quantity == null) {
            // 如果商品不在购物车中，添加商品并设定数量为1
            cart.put(product, 1);
        } else {
            // 如果商品已经在购物车中，数量增加1
            cart.put(product, quantity + 1);
        }
        System.out.println("成功加入到购物车");
        session.setAttribute("cart",cart);
        Cookie cookie=new Cookie("JSESSIONID",session.getId());
        cookie.setMaxAge(30*24*60);//购物车的信息存储一个月
        // 获取来源页面的地址
        String referer = request.getHeader("Referer");

        // 如果 referer 存在，则重定向回来源页面
        if (referer != null) {
            response.sendRedirect(referer);
        } else {
            // 如果没有 referer，则跳转到默认页面
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 处理 POST 请求
        doGet(request,response);
    }
}

