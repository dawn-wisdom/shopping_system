package controller;

import entity.Product;
import service.ProductService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Select2BuyServlet")
public class Select2BuyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService=new ProductService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 处理 GET 请求
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 处理 POST 请求
        // 获取选中的商品 ID 列表
        String selectedItemIdsStr = request.getParameter("selectedItemIds");
        Map<Product,Integer> product2Buy=new HashMap<Product,Integer>();
        HttpSession session = request.getSession();
        Map<Product,Integer> cart=(Map<Product,Integer>)session.getAttribute("cart");
        session.setAttribute("product2Buy", product2Buy);
        if (selectedItemIdsStr != null && !selectedItemIdsStr.isEmpty()) {
            // 将选中的商品 ID 字符串分割为数组
            String[] selectedItemIds = selectedItemIdsStr.split(",");
            // 遍历选中的商品 ID
            for (String productIdStr : selectedItemIds) {
                try {
                    // 将 productIdStr 转换为 int 类型
                    int productId = Integer.parseInt(productIdStr);

                    // 遍历购物车中的商品，找到对应的商品
                    for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                        Product product = entry.getKey();

                        // 检查商品 ID 是否匹配
                        if (product.getProductId() == productId) {
                            // 将匹配的商品及其数量加入到 buy 中
                            product2Buy.put(product, entry.getValue());
                            // 从购物车中移除该商品
                            cart.remove(product);
                            break;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid product ID: " + productIdStr);
                }
            }

            /*
            for (Map.Entry<Product, Integer> entry : product2Buy.entrySet()) {
                System.out.println("Selected Product: " + entry.getKey().getProductName() + ", Quantity: " + entry.getValue());
            }*/
            // 创建订单
            response.sendRedirect(request.getContextPath() + "/submitOrder.jsp");
        }

        //直接点立即购买的话
        String productByDirectClick=request.getParameter("productId");
        String quantityByDirectClick=request.getParameter("quantity");
        if(productByDirectClick!=null)
        {
            int productId=Integer.parseInt(productByDirectClick);
            int quantity=Integer.parseInt(quantityByDirectClick);
            Product product=productService.findProductById(productId);
            product2Buy.put(product, quantity);
            response.sendRedirect(request.getContextPath() + "/submitOrder.jsp");
        }

    }
}