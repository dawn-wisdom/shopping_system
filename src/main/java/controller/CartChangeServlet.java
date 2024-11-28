package controller;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.Product;
/**
 * 购物车内容变更
 * @author admin
 *
 */
public class CartChangeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.得到商品id
        int id = Integer.parseInt(request.getParameter("productId"));
        // 2.得到要修改的数量
        int count = Integer.parseInt(request.getParameter("quantity"));
        // 3.从session中获取购物车.
        HttpSession session = request.getSession();
        Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        Product p = new Product();
        p.setProductId(id);
        if (count != 0) {
            cart.put(p, count);
        } else {
            cart.remove(p);
        }
        response.sendRedirect(request.getContextPath() + "/cart.jsp");
    }
}
